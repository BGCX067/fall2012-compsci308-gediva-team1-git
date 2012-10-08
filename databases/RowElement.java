package databases;

import java.util.ArrayList;

public abstract class RowElement <T> implements Comparable<RowElement <T>> {
    
    protected int myPrimaryIndex=0;
    protected ArrayList<T> myData;
    
    public void setPrimaryIndex(int index){
        myPrimaryIndex=index;
    }
    
    protected T getEntry(int index) {
        return myData.get(index);
    }
    
    public abstract T getPrimaryValue();
    
    public abstract void addData (String rdata);

    @Override
    public abstract int compareTo (RowElement<T> r);
    
}
