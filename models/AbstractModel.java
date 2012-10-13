package models;

import databases.DataTable;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import models.responses.IDataSet;
import parsers.FileParser;
import parsers.GenericParser;
import parsers.WebParser;

/**
 * Describes the abstract implementation for the
 * model used by the program.
 */
public abstract class AbstractModel {

    private DataTable<?> myDataTable;
    private Map <String, GenericParser> myParsers =
        new HashMap<String, GenericParser>();

    /**
     * Initializes a model for use by the program.
     */
    public AbstractModel() {
        myParsers.put("FileParser", new FileParser());
        myParsers.put("WebParser", new WebParser());
    }

    /**
     * Returns an unmodifiable list of supported parsers.
     * @return
     */
    public List<String> supportedParsers() {
        return Collections.unmodifiableList(
                new ArrayList<String>(myParsers.keySet()));
    }

    protected BufferedReader generateReader (String name) throws IOException {
        try {
            for (GenericParser g : myParsers.values()) {
                if (g.isSupported(name)) {
                    return g.generateReader(name);
                }
            }
            return null;
        }
        catch (IOException e) {
            throw new IOException("Cannot handle source");
        }
    }

    /**
     * Initializer expects a Scanner. It calls helper methods to parse the data
     * and store it in the appropriate data structure(s).
     *
     * @param name of the source from which to get the raw data
     */
    public boolean initialize (String name) {
        try {
            BufferedReader rawdata = generateReader(name);
            load(rawdata);
            return true;
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Returns true/false depending on the success
     * of initializing from a file.
     *
     * @param file from which to try initializing
     * @return
     */
    public boolean initialize(File file) {
        return initialize(file.getPath());
    }

    /**
     * Gets the identifier of this model.
     */
    public abstract String getIdentifier ();

    /**
     * this is a helper method to parse data. It can be called explicitly
     * outside of init in case you wanted to reload the data or load different
     * data.
     *
     * @param s the source from which to load data
     */
    public abstract boolean load (BufferedReader s);

    /**
     * receives a Request object. The request contains the request it should
     * respond to.
     *
     * @param requestType the type of data requested
     */
    public abstract IDataSet<?> process (String requestType);

    /**
     * Returns a list of possible request types.
     */
    public abstract Set<String> getRequestTypes ();
}
