package models.responses;

import java.util.List;

/**
 * Provides an interface for a set
 * of data.
 * 
 * @author Lance Co Ting Keh and Mark Govea
 *
 * @param <T> the type of data in the
 * data set.
 */
public interface IDataSet<T> {

    /**
     * Gets the data according to an attribute.
     *
     * @param attribute what to look for
     *        when getting the data.
     * @return a list containing ordered
     *        data of the specified attribute
     */
    List<T> getData(String attribute);

    /**
     * Sorts the data in the data set
     * according to an attribute.
     *
     * @param attribute what to sort
     *        the data by.
     * @return a sorted version of the dataSet
     */
    IDataSet<T> sort(String attribute);

    /**
     * Returns a list of attributes held
     * by the data table.
     *
     * @return list of attributes
     */
    List<String> attributes();

}
