package model.responses;

import java.util.List;
import javax.media.jai.util.Range;


public interface IDataSet {

    public List<?> getData (String attribute);

    public IDataSet range (String attribute, Range range);
    
    public IDataSet sort (String attribute);

    public List<String> attributes ();

}
