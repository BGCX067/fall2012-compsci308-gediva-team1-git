package databases;

import java.util.ArrayList;


public class StockTable extends DataTable<Double> {

    private ArrayList<StockRowElement> myDataRows;

    public StockTable () {
        super();
        myDataRows= new ArrayList<StockRowElement>();
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
        myDataRows.add(row);
    }
}