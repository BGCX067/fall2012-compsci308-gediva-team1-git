package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import databases.StockTable;


public class Stock extends AbstractModel {
    
    private Map<String,String> stockInfo;
    
    public Stock(){
        stockInfo= new HashMap<String,String>();
        myDataTable=new StockTable();
    }
    
    
    /**
     * parses the data and performs some stock specific parsing, like extracting
     * the name and ticker symbol.
     */
    @Override
    public boolean load (BufferedReader s) {
        try {
            myDataTable.setColumnNames(s.readLine());
            String currentline="";
            while((currentline=s.readLine()) != null){
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
     * a list of the different available request types for any instance of this
     * Model. Different AbstractModel instances will have different request
     * types.
     */
    public static enum RequestType {
        MOVING_AVG
    }

    /**
     * returns basic info about the stock– name, ticker symbol, etc
     */
    public DataSet getStockInfo () {
        return null;
    }


    @Override
    public String getIdentifier () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DataSet process (int r) {
        // TODO Auto-generated method stub
        return null;
    }

}