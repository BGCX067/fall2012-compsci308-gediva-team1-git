package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.responses.IDataSet;
import model.responses.StockDataSet;
import databases.StockTable;


public class StockModel extends AbstractModel {

    /**
     * contains a list of possible evaluation methods
     * we also support "no-action" calls with the empty string () as an argument
     * 
     * these must correspond to methods in this.RequestProcessor
     * a method must exist with name: "process" + string.removeWhitespace()
     */
    private static final List<String> REQUEST_TYPES =
            new ArrayList<String>(Arrays.asList(new String[] { "Moving Average" }));

    // Holds stock name, symbol, last closing price (formatted $xx.xx)
    private Map<String, String> stockInfo;
    private StockTable myDataTable;

    public StockModel () {
        stockInfo = new HashMap<String, String>();
        myDataTable = new StockTable();
    }

    /**
     * parses the data and performs some stock specific parsing, like extracting
     * the name and ticker symbol.
     */
    @Override
    public boolean load (BufferedReader s) {
        try {
            myDataTable.setColumnNames(s.readLine());
            String currentline = "";
            while ((currentline = s.readLine()) != null) {
                myDataTable.newRow(currentline);
            }

            return true;
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    /**
     * returns basic info about the stock– name, ticker symbol, etc
     */
    public Map<String, String> getStockInfo () {
        return Collections.unmodifiableMap(stockInfo);
    }

    @Override
    public String getIdentifier () {
        return new String(stockInfo.get("symbol"));
    }

    @Override
    public IDataSet<Double> process (String requestType) {
        /* 
         * do nothing if the requestType is empty or if the value is already
         * computed
         * 
         * else, call the request type
         */
        if (!"".equals(requestType) && !myDataTable.columnNames().contains(requestType)) {
            try {
                RequestProcessor.class.getMethod("process" + requestType.replaceAll(" ", ""))
                        .invoke(null, myDataTable);
            }
            catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return new StockDataSet(myDataTable);
    }

    @Override
    public List<String> getRequestTypes () {
        return REQUEST_TYPES;
    }

    private static class RequestProcessor {

        // used through reflection
        @SuppressWarnings("unused")
        static void processMovingAverage (StockTable st) {

            // need in order time to get moving avg
            st.sortbyColumn("Date"); // TODO: make this work????

            // ready/initialize calculations
            List<Double> list = st.columnValues("Close");
            List<Double> result = new ArrayList<Double>(list.size());
            double alpha = 0.9;
            result.set(0, list.get(0));

            for (int i = 1; i < list.size(); i++) {
                result.set(i, result.get(i - 1) * (1 - alpha) + list.get(i - 1) * alpha);
            }

            // fill in results
            st.addColumn("Moving Average");
            st.setColumnValues("Moving Average", result.listIterator());
        }

    }
}