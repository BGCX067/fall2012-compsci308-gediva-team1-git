package databases;

import java.util.ArrayList;

public class StockRowElement extends RowElement {
    
    private ArrayList<Double> myData=new ArrayList<Double>();
    
    public void addData (String rdata) {
        // TODO Auto-generated method stub
        myData.add(new Double(rdata));        
    }
}
