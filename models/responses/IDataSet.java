package models.responses;

import java.util.List;

public interface IDataSet<T> {

    public List<T> getData (String attribute);
    
    public IDataSet<T> sort (String attribute);

    public List<String> attributes ();

}
