package model;

import java.io.BufferedReader;


public class Stock extends AbstractModel {

    /**
     * the constructor expects a Scanner. It just passes it to
     * super.initialize()
     */
    public Stock (BufferedReader s) {
        super.initialize(s); // TODO: handle false;
    }

    /**
     * parses the data and performs some stock specific parsing, like extracting
     * the name and ticker symbol.
     */
    public boolean reload (BufferedReader s) {
        return false;
    }

    /**
     * a list of the different available request types for any instance of this
     * Model. Different AbstractModel instances will have different request
     * types.
     */
    public static enum RequestType {

    }

    /**
     * returns basic info about the stock– name, ticker symbol, etc
     */
    public DataSet getInfo () {

    }

    /**
     * all requests go in through the process command
     * The process method calls on a helper method based on the RequestType
     */
    public DataSet process (RequestType r) {

    }

}