package databases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public abstract class DataTable<T> {

    private List<String> myColumnNames;
    protected List<RowElement<T>> myDataRows;

    public DataTable () {
        myColumnNames = new ArrayList<String>();
        myDataRows = new ArrayList<RowElement<T>>();
    }

    public DataTable (DataTable<T> st) {
        myColumnNames = new ArrayList<String>(st.columnNames());//blantant error
        myDataRows = new ArrayList<RowElement<T>>(st.getDataRows());
    }

    public void setColumnNames (String s) {
        String[] sArray = s.split(",");
        for (String cname : sArray) {
            cname = cname.replaceAll("[^A-Za-z0-9]", ""); //remove abnormal characters
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

    public void parseColumnValues (String colname, Iterator<String> it) {
        for (RowElement<T> row : myDataRows) {
            row.addData(it.next());
        }
    }
    
    public void setColumnValues (String colname, Iterator<T> it) {
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
        for(int i=0; i<myDataRows.size();i++){
            result.add(myDataRows.get(i).getEntry(index));
        }
        return Collections.unmodifiableList(result);
    }
    
    public void removeRow (int index) {
        myDataRows.remove(index);
    }
        
    public List<RowElement<T>> getDataRows(){
        return myDataRows;
    }

}
