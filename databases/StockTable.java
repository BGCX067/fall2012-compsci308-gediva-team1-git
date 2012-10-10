package databases;

import java.util.ArrayList;


public class StockTable extends DataTable<Double> {


    public StockTable () {
        super();
    }
    
    public StockTable (DataTable<Double> st) {
        super(st);
    }

    @Override
    public void newRow (String sRow) {
        StockRowElement row = new StockRowElement();
        String[] sarray = sRow.split(",");
        for (String rdata : sarray) {
            String mdata=rdata.replace("-","");
            System.out.println(mdata);
            row.addData(mdata);
        }
        super.myDataRows.add(row);
    }
}