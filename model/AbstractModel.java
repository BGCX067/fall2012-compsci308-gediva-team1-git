package model;

import java.io.BufferedReader;


public abstract class AbstractModel {

    /**
     * initializer expects a Scanner. It calls helper methods to parse the data
     * and store it in the appropriate data structure(s)
     */
    public boolean initialize (BufferedReader s) {
        return false;
    }

    public abstract String getIdentifier ();

    /**
     * this is a helper method to parse data. It can be called explicitly
     * outside of init in case you wanted to reload the data or load different
     * data.
     */
    public abstract boolean reload (BufferedReader s);

    /**
     * receives a Request object. The request contains the request it should
     * respond to.
     */
    public abstract DataSet process (int r);

}