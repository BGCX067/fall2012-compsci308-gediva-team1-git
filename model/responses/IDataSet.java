package model.responses;

import java.util.List;
//import javax.media.jai.util.Range;


public interface IDataSet<T> {

    public List<T> getData (String attribute);

    public IDataSet<T> range (String attribute);
            //Range range);
    
    public IDataSet<T> sort (String attribute);

    public List<String> attributes ();

}
