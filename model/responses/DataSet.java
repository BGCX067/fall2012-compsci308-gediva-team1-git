package model.responses;

import java.util.Iterator;
import java.util.List;
import javax.media.jai.util.Range;


public interface DataSet {

    public Iterator<?> getData (String attribute);

    public DataSet range (String attribute, Range range);
    
    public DataSet sort (String attribute);

    public List<String> attributes ();

}
