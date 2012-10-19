package databases;

/**
 * Specifies an instance of DataTable for implementing
 * stock data.
 */
public class StockTable extends DataTable<Comparable> {
    /**
     * Initializes and empty data table.
     */
    public StockTable () {
        super();
    }

    /**
     * Initializes a data table for stock info.
     *
     * @param st is the data table from which to
     * initialize.
     */
    public StockTable (DataTable<Comparable> st) {
        super(st);
    }

    /**
     * Add a new row to this data table.
     *
     * @param sRow the row to add to the data
     * table (info is separated by commas).
     */
    public void newRow (String sRow) {
        StockRowElement row = new StockRowElement();
        String[] sarray={};
        
        for (String d: DELIMITERS){
            sarray = sRow.split(d);
            if(!sarray[0].equals(sRow)) break;
        }
        
        for (String rdata : sarray) {
            row.addData(rdata);
        }
        super.getMyDataRows().add(row);
    }
}
