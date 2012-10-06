package databases;

import java.util.ArrayList;

public class StockRowElement extends RowElement<Double> {
    ArrayList<Double> myData;
    
    public StockRowElement(){
        myData=(ArrayList<Double>) new ArrayList<Double>();
    }
    
    public void addData (String rdata) {
        myData.add(new Double(rdata));        
    }
    
    @Override
    public Double getPrimaryValue(){
        return (Double) myData.get(myPrimaryIndex);
    }

    @Override
    public int compareTo (RowElement<Double> r) {
        return myData.get(myPrimaryIndex).compareTo(((StockRowElement) r).getPrimaryValue());
    }

    
}
