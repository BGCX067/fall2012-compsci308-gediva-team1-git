package readers.parsers;

import java.io.BufferedReader;
import java.io.File;
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
    
    public Reader getReader(File file) throws IOException{
        return new FileReader(file);
    }

    @Override
    public Boolean isSupported (String name) {
        File file = new File("/demo.txt");
        return file.canRead();
    }
    
    public Boolean isSupported(File file){
        return file.canRead();
    }
    
}
