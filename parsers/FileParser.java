package parsers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileParser extends GenericParser{
    
    /*
     * Throws an IOexception if invalid file
     */
    @Override
    protected Reader getReader (String name) throws IOException {
        return new FileReader(name);
    }
    

}
