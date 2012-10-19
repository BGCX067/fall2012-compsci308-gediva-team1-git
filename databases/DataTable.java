package databases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * Abstract class for initializing the
 * data table used by the rest of the
 * program.
 * 
 * @author Lance Co Ting Keh and Mark Govea
 * 
 * @param <T> is the type of data represented
 *        in the table.
 */
public abstract class DataTable<T> {
    protected static final String[] DELIMITERS = { ",", "\t", " " };

    private List<String> myColumnNames;
    private List<RowElement<T>> myDataRows;

    /**
     * Constructor for the data table.
     * Simply initializes the rows and
     * columns as empty arraylists.
     */
    public DataTable () {
        myColumnNames = new ArrayList<String>();
        setMyDataRows(new ArrayList<RowElement<T>>());
    }

    /**
     * Initializes this data table based on
     * one that is passed in.
     * 
     * @param startFrom is the data table from which to
     *        initialize the current data table
     */
    public DataTable (DataTable<T> startFrom) {
        myColumnNames = new ArrayList<String>(startFrom.columnNames());
        setMyDataRows(new ArrayList<RowElement<T>>(startFrom.getMyDataRows()));
    }

    /**
     * Sets the column names of this data table.
     * 
     * @param s is the string holding the column
     *        names (all separated by commas).
     */
    public void setColumnNames (String s) {
        String[] sArray = {};

        for (String d : DELIMITERS) {
            sArray = s.split(d);
            if (!sArray[0].equals(s)) {
                break;
            }
        }

        for (String cname : sArray) {
            // remove abnormal characters
            String modcname = cname.replaceAll("[^A-Za-z0-9]", "");
            myColumnNames.add(modcname);
        }
    }

    /**
     * Abstract method that is filled out by
     * a specific instance of DataTable used
     * to add a new row.
     * 
     * @param s is the string holding the new
     *        data (separated by commas).
     */
    protected abstract void newRow (String s);

    /**
     * Setting a column as the primary key.
     * 
     * @param colname is the name of the column
     *        from which to set the primary key.
     */
    public void setPrimaryKey (String colname) {
        int primaryindex = myColumnNames.indexOf(colname);
        for (RowElement<T> row : getMyDataRows()) {
            row.setMyPrimaryIndex(primaryindex);
        }
    }

    /**
     * Sorts the data table by column.
     * 
     * @param colname is the name of the column
     *        from which to base the sorting procedure.
     */
    public void sortbyColumn (String colname) {
        setPrimaryKey(colname);
        Collections.sort(getMyDataRows());
    }

    /**
     * Adds an empty column to the data table.
     * 
     * @param colname the name of the column
     *        to be added.
     */
    public void addColumn (String colname) {
        myColumnNames.add(colname);

    }

    public void parseColumnValues (String colname, Iterator<String> it) {
        for (RowElement<T> row : getMyDataRows()) {
            row.addData(it.next());
        }
    }

    public void setColumnValues (String colname, Iterator<T> it) {
        for (RowElement<T> row : getMyDataRows()) {
            row.addData(it.next());
        }
    }

    /**
     * Clears the table of all data.
     */
    public void clear () {
        getMyDataRows().clear();
        myColumnNames.clear();
    }

    /**
     * Reverses the rows of the data table.
     */
    public void reverse () {
        Collections.reverse(getMyDataRows());
    }

    /**
     * Returns an unmodifialbe version of the
     * column names for this table.
     */
    public List<String> columnNames () {
        return Collections.unmodifiableList(myColumnNames);
    }

    public List<T> columnValues (String attribute) {
        int index = myColumnNames.indexOf(attribute);
        List<T> result = new ArrayList<T>();
        for (int i = 0; i < getMyDataRows().size(); i++) {
            result.add(getMyDataRows().get(i).getEntry(index));
        }
        return Collections.unmodifiableList(result);
    }

    /**
     * Removes the row specified by index.
     * 
     * @param index the position of the row
     *        to be removed (from the top).
     */
    public void removeRow (int index) {
        getMyDataRows().remove(index);
    }

    private void setMyDataRows (List<RowElement<T>> dataRows) {
        this.myDataRows = dataRows;
    }

    protected List<RowElement<T>> getMyDataRows () {
        return myDataRows;
    }
}
