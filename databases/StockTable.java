package databases;

import java.util.ArrayList;


public class StockTable extends DataTable<Comparable> {


    public StockTable () {
        super();
    }
    
    public StockTable (DataTable<Comparable> st) {
        super(st);
    }

    @Override
    public void newRow (String sRow) {
        StockRowElement row = new StockRowElement();
        String[] sarray = sRow.split(",");
        for (String rdata : sarray) {
            row.addData(rdata);
        }
        super.myDataRows.add(row);
    }
}