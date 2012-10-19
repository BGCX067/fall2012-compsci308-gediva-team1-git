package models.responses;

import databases.DataTable;
import databases.StockTable;
import java.util.List;

/**
 * Instance of IDataSet that is specific to stock data.
 * 
 * @author Lance Co Ting Keh and Mark Govea
 */
public class StockDataSet implements IDataSet<Comparable> {
    private StockTable myTable;

    /**
     * Initializes this stock data set.
     *
     * @param st the data table that holds the data
     * for this data set.
     */
    public StockDataSet (DataTable<Comparable> st) {
        myTable = new StockTable(st);
    }

    @Override
    public List<Comparable> getData (String attribute) {
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
