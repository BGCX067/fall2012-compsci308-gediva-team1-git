package models;

import databases.StockTable;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import models.responses.IDataSet;
import models.responses.StockDataSet;

/**
 * This class holds all the info and describes how to
 * use/modify it for a current stock.
 */
public class StockModel extends AbstractModel {

    /**
     * Contains a list of possible evaluation methods.
     * We also support "no-action" calls with the empty string () as an argument
     *
     * These must correspond to methods in this.RequestProcessor
     * a method must exist with name: "process" + string.removeWhitespace().
     */
    private static final Set<String> REQUEST_TYPES =
            new HashSet<String>(Arrays.asList(new String[] {"Moving Average"}));
    private static final String SYMBOL = "Symbol";
    private static final String COMPANY_NAME = "Company Name";
    private static final String LAST_PRICE = "Last Price";
    private static final String DATE = "Date";
    private static final String CLOSE = "Close";

    // Holds stock name, symbol, last closing price (formatted $xx.xx)
    private Map<String, String> myStockInfo;
    private StockTable myDataTable;

    /**
     * Initializes a model specific to stock data.
     *
     * @param symbol the ticker symbol of the stock
     * @param companyName the name of the company
     */
    public StockModel (String symbol, String companyName) {
        super();
        myStockInfo = new HashMap<String, String>();

        myStockInfo.put(SYMBOL, symbol);
        myStockInfo.put(COMPANY_NAME, companyName);

        myDataTable = new StockTable();
    }

    /**
     * Updates the price of the current stock.
     */
    public void updateLastPrice() {
        myDataTable.sortbyColumn(DATE);
        List<Comparable> list = myDataTable.columnValues(CLOSE);
        myStockInfo.put(LAST_PRICE, "$" + String.format("%.2f",
                (Double) list.get(list.size() - 1)));
    }

    /**
     * Parses the data and performs some stock specific parsing, like extracting
     * the name and ticker symbol.
     *
     * @param s the source from which to load
     */
    @Override
    public boolean load (BufferedReader s) {
        try {
            myDataTable.setColumnNames(s.readLine());
            String currentline = "";
            while ((currentline = s.readLine()) != null) {
                myDataTable.newRow(currentline);
            }
            List<String> names = myDataTable.columnNames();
            System.out.println(myDataTable.columnNames().toString());
            updateLastPrice();
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns basic info about the stock– name, ticker symbol, etc.
     */
    public Map<String, String> getStockInfo () {
        return Collections.unmodifiableMap(myStockInfo);
    }

    /**
     * Sets the symbol and stock name.
     *
     * @param symbol the ticker symbol of the stock
     * @param name of the stock
     */
    public void setStockInfo(String symbol, String name) {
        myStockInfo.put(SYMBOL, symbol);
        myStockInfo.put(COMPANY_NAME, name);
    }

    @Override
    public String getIdentifier () {
        return new String(myStockInfo.get(SYMBOL));
    }

    @Override
    public IDataSet<Comparable> process (String requestType) {
        /*
         * do nothing if the requestType is empty or if the value is already
         * computed
         *
         * else, call the request type
         */
        if (!"".equals(requestType)
                && !myDataTable.columnNames().contains(requestType)) {
            try {
                RequestProcessor.processMovingAverage(myDataTable);
            }
            catch (SecurityException e) {
                e.printStackTrace();
            }

        }
        return new StockDataSet(myDataTable);
    }

    @Override
    public Set<String> getRequestTypes () {
        return REQUEST_TYPES;
    }

    /**
     * Describes how to process the current request for data.
     */
    private static class RequestProcessor {
        private static final double MOVING_AVERAGE_WEIGHT = 0.9;

        // used through reflection
        static void processMovingAverage (StockTable st) {

            // need in order time to get moving average
            st.sortbyColumn(DATE);

            // ready/initialize calculations
            List<Comparable> list = st.columnValues(CLOSE);
            List<Comparable> result = new ArrayList<Comparable>(list.size());
            result.set(0, list.get(0));

            for (int i = 1; i < list.size(); i++) {
                result.set(i, (Double) result.get(i - 1)
                        * (1 - MOVING_AVERAGE_WEIGHT)
                        + (Double) list.get(i - 1) * MOVING_AVERAGE_WEIGHT);
            }

            // fill in results
            st.addColumn("Moving Average");
            st.setColumnValues("Moving Average", result.listIterator());
        }

    }
}
