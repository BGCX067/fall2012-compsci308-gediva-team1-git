package databases;

import java.util.ArrayList;

public class StockRowElement extends RowElement {
    
    public StockRowElement(){
        myData=new ArrayList<Double>();
    }
    
    public void addData (String rdata) {
        // TODO Auto-generated method stub
        myData.add(new Double(rdata));        
    }
    
    @Override
    public Double getPrimaryValue(){
        return (Double) myData.get(myPrimaryIndex);
    }

    @Override
    public int compareTo (Object r) {
        return ((Double) myData.get(myPrimaryIndex)).compareTo(((StockRowElement) r).getPrimaryValue());
    }
    
}
