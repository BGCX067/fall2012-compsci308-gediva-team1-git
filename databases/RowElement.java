package databases;

import java.util.List;

/**
 * Provides an abstract representation of an elemnt of data
 * contained within a row of the DataTable.
 * 
 * @author Lance Co Ting Keh and Mark Govea
 *
 * @param <T>
 */
public abstract class RowElement <T> implements Comparable<RowElement <T>> {
    private int myPrimaryIndex = 0;
    private List<T> myData;

    protected T getEntry(int index) {
        return getData().get(index);
    }

    /**
     * Returns the primary value.
     */
    protected abstract T getPrimaryValue();

    /**
     * Adds data to this element if the data
     * is in string format.
     *
     * @param rdata the data to be added
     */
    protected abstract void addData (String rdata);

    /**
     * Adds data to this element in the data
     * is an instance of T.
     *
     * @param rdata the data to be added
     */
    protected abstract void addData (T rdata);

    @Override
    public abstract int compareTo (RowElement<T> r);

    protected void setData (List<T> data) {
        this.myData = data;
    }

    protected List<T> getData () {
        return myData;
    }

    protected void setMyPrimaryIndex (int index) {
        this.myPrimaryIndex = index;
    }

    protected int getMyPrimaryIndex () {
        return myPrimaryIndex;
    }
}
