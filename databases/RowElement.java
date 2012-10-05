package databases;

import java.util.ArrayList;

public class RowElement implements Comparable<RowElement> {
    
    private int myPrimaryIndex=0;
    //make this extendable
    private ArrayList<Double> myData=new ArrayList<Double>();
    
    public void setPrimaryIndex(int index){
        myPrimaryIndex=index;
    }
    
    public double getPrimaryValue(){
        return myData.get(myPrimaryIndex);
    }
    
    public void addData (String rdata) {
        // TODO Auto-generated method stub
        myData.add(new Double(rdata));        
    }

    @Override
    public int compareTo (RowElement r) {
        // TODO Auto-generated method stub
        return myData.get(myPrimaryIndex).compareTo(r.getPrimaryValue());
    }

}
