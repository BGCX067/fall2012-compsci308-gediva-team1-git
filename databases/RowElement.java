package databases;

import java.util.ArrayList;

/**
 * Provides an abstract representation of an elemnt of data
 * contained within a row of the DataTable.
 *
 * @param <T>
 */
public abstract class RowElement <T> implements Comparable<RowElement <T>> {
    private int myPrimaryIndex = 0;
    private ArrayList<T> myData;

    protected T getEntry(int index) {
        return getMyData().get(index);
    }

    /**
     * Returns the primary value.
     */
    public abstract T getPrimaryValue();

    /**
     * Adds data to this element if the data
     * is in string format.
     *
     * @param rdata the data to be added
     */
    public abstract void addData (String rdata);

    /**
     * Adds data to this element in the data
     * is an instance of T.
     *
     * @param rdata the data to be added
     */
    public abstract void addData (T rdata);

    @Override
    public abstract int compareTo (RowElement<T> r);

    protected void setMyData (ArrayList<T> data) {
        this.myData = data;
    }

    protected ArrayList<T> getMyData () {
        return myData;
    }

    protected void setMyPrimaryIndex (int index) {
        this.myPrimaryIndex = index;
    }

    protected int getMyPrimaryIndex () {
        return myPrimaryIndex;
    }
}
