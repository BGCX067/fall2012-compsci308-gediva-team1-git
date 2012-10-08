package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import databases.StockTable;


public class Stock extends AbstractModel {

    // Holds stock name, symbol, last closing price (formatted $xx.xx)
    private Map<String, String> stockInfo;

    /**
     * a list of the different available request types for any instance of this
     * Model. Different AbstractModel instances will have different request
     * types.
     */
    public static final Set<String> RequestType =
            new HashSet<String>(Arrays.asList(new String[] { "moving_avg" }));

    public Stock () {
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataSet process (String requestType) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<String> getRequestTypes () {
        // TODO Auto-generated method stub
        // return the available request types so we can make buttons for them
        // expected response is List<String> (or something more generic)
        // see StockController to see what we want
        return null;
    }

}