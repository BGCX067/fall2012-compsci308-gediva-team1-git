package databases;

import java.util.ArrayList;

public abstract class RowElement <T> implements Comparable<Object> {
    
    protected int myPrimaryIndex=0;
    protected ArrayList<T> myData;
    
    public void setPrimaryIndex(int index){
        myPrimaryIndex=index;
    }
    
    
    public abstract T getPrimaryValue();
    
    public abstract void addData (String rdata);

    @Override
    public abstract int compareTo (Object r);
    
}
