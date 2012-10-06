package databases;

import java.util.ArrayList;

public class StockTable extends DataTable{
    
    private ArrayList<StockRowElement> myDataRows;
    
    public StockTable(){
        super();
        
    }
    public void newRow(String s){
        StockRowElement row=new StockRowElement();
        myDataRows.add(row);
        String[] sarray=s.split(",");
        for(String rdata:sarray){
            row.addData(rdata);
        }
    }
}
