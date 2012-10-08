package databases;

import java.util.ArrayList;

public class StockRowElement extends RowElement<Double> {
    ArrayList<Double> myData;
    
    public StockRowElement(){
        myData=(ArrayList<Double>) new ArrayList<Double>();
    }
    
    @Override
    public void parseData (String rdata) {
        myData.add(new Double(rdata));        
    }
    
    @Override
    public void addData (Double rdata) {
        myData.add(rdata);        
    }
    
    @Override
    public Double getPrimaryValue(){
        return (Double) myData.get(myPrimaryIndex);
    }

    @Override
    public int compareTo (RowElement<Double> r) {
        return getPrimaryValue().compareTo(((StockRowElement) r).getPrimaryValue());
    }

    
}
