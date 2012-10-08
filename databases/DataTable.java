package databases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public abstract class DataTable<T> {

    private List<String> myColumnNames;
    private List<RowElement<T>> myDataRows;

    public DataTable () {
        myColumnNames = new ArrayList<String>();
        myDataRows = new ArrayList<RowElement<T>>();
    }

    public DataTable (DataTable<T> dt) {
        myColumnNames = new ArrayList<String>(dt.myColumnNames);
        myDataRows = new ArrayList<RowElement<T>>(dt.myDataRows);
    }

    public void setColumnNames (String s) {
        String[] sArray = s.split(",");
        for (String cname : sArray) {
            myColumnNames.add(cname);
        }
    }

    public abstract void newRow (String s);

    /**
     * Setting a column as the primary key
     * 
     * @param colname
     * @return
     */
    public void setPrimaryKey (String colname) {
        int primaryindex = myColumnNames.indexOf(colname);
        for (RowElement<T> row : myDataRows) {
            row.setPrimaryIndex(primaryindex);
        }
    }

    public void sortbyColumn (String colname) {
        setPrimaryKey(colname);
        Collections.sort(myDataRows);
    }

    public void addColumn (String colname) {
        myColumnNames.add(colname);

    }

    public void setColumnValues (String colname, Iterator<String> it) {
        for (RowElement<T> row : myDataRows) {
            row.addData(it.next());
        }
    }

    public void clear () {
        myDataRows.clear();
    }

    public void reverse () {
        Collections.reverse(myDataRows);
    }
    
    public List<String> columnNames () {
        return Collections.unmodifiableList(myColumnNames);
    }
    
    public List<T> columnValues (String attribute) {
        int index = myColumnNames.indexOf(attribute);
        List<T> result = new ArrayList<T>();
        for (RowElement<T> row : myDataRows) {
            result.add(row.getEntry(index));
        }
        return Collections.unmodifiableList(result);
    }

}
