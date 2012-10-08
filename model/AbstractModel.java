package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import parsers.FileParser;
import parsers.GenericParser;
import parsers.WebParser;
import model.responses.IDataSet;
import databases.DataTable;



public abstract class AbstractModel {
    
    /**
     * the database that this model uses
     * it is strongly recommended that this is overridden by a specific child of DataTable
     */
    protected DataTable<?> myDataTable;
    private Map <String, GenericParser> myParsers = new HashMap<String,GenericParser>();
    
    public AbstractModel(){
        myParsers.put("FileParser",new FileParser());
        myParsers.put("WebParser", new WebParser());
    }
    
    /**
     * Returns an unmodifiable list of supported parsers
     * @return
     */
    public List<String> supportedParsers(){
        return Collections.unmodifiableList(new ArrayList<String>(myParsers.keySet()));
    }
    

    protected BufferedReader generateReader (String name) throws IOException {
        try{
            for (GenericParser g : myParsers.values()) {
                if (g.isSupported(name)) {
                    return g.generateReader(name);
                }
            }
            return null;
        }
        catch(IOException e){
            throw new IOException("Cannot handle source");
        }
    }
    
    
    /**
     * initializer expects a Scanner. It calls helper methods to parse the data
     * and store it in the appropriate data structure(s)
     */
    public boolean initialize (String name) {
        //based on the specific input format, call a different 
        try{
            BufferedReader rawdata= generateReader(name); 
            load(rawdata);
            return true;
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            return false;            
        }

    }

    public boolean initialize(File file){
        return initialize(file.getPath());
    }
    
    public abstract String getIdentifier ();

    /**
     * this is a helper method to parse data. It can be called explicitly
     * outside of init in case you wanted to reload the data or load different
     * data.
     */
    public abstract boolean load (BufferedReader s);

    /**
     * receives a Request object. The request contains the request it should
     * respond to.
     */
    public abstract IDataSet<?> process (String requestType);

    public abstract List<String> getRequestTypes ();
    
}