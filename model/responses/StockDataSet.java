package model.responses;

import java.util.List;
import javax.media.jai.util.Range;
import databases.DataTable;
import databases.StockTable;


public class StockDataSet implements IDataSet<Double> {

    private StockTable myTable;

    public StockDataSet (DataTable<Double> st) {
        myTable = new StockTable(st);
    }

    @Override
    public List<Double> getData (String attribute) {
        return myTable.columnValues(attribute);
    }

    @Override
    public StockDataSet range (String attribute, Range range) {
        myTable.sortbyColumn(attribute);
        return this;
    }

    @Override
    public StockDataSet sort (String attribute) {
        myTable.sortbyColumn(attribute);
        return this;
    }

    @Override
    public List<String> attributes () {
        return myTable.columnNames();
    }

}
