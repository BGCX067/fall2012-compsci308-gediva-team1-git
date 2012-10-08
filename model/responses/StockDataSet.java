package model.responses;

import java.util.List;
import javax.media.jai.util.Range;
import databases.StockTable;


public class StockDataSet {
    
    private StockTable myTable;

    public StockDataSet(StockTable st) {
        myTable = new StockTable(st);
    }
    
    public List<Double> getData (String attribute) {
        return myTable.columnValues(attribute);
    }

    public StockDataSet range (String attribute, Range range) {
        myTable.sortbyColumn(attribute);
        return this;
    }
    
    public StockDataSet sort (String attribute) {
        myTable.sortbyColumn(attribute);
        return this;
    }

    public List<String> attributes () {
        return myTable.columnNames();
    }

}
