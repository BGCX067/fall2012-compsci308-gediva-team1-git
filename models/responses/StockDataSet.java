package models.responses;

import java.util.List;
//import javax.media.jai.util.Range;
import databases.DataTable;
import databases.StockTable;


public class StockDataSet implements IDataSet<Comparable> {

    private StockTable myTable;

    public StockDataSet (DataTable<Comparable> st) {
        myTable = new StockTable(st);
    }

    @Override
    public List<Comparable> getData (String attribute) {
          List<Comparable> list = myTable.columnValues(attribute);
        return myTable.columnValues(attribute);
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
