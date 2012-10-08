package databases;

import java.util.ArrayList;


public class StockTable extends DataTable<Double> {

    private ArrayList<StockRowElement> myDataRows;

    public StockTable () {
        super();
    }
    
    public StockTable (StockTable st) {
        super(st);
    }

    @Override
    public void newRow (String s) {
        StockRowElement row = new StockRowElement();
        myDataRows.add(row);
        String[] sarray = s.split(",");
        for (String rdata : sarray) {
            row.parseData(rdata);
        }
    }
}