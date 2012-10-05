package parsers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

public class WebParser extends GenericParser{
    static final String PROTOCOL = "http:";
    
    @Override
    protected Reader getReader (String name) throws IOException {
        return new InputStreamReader(getConnection(name).getInputStream());
    }
    
    private URLConnection getConnection (String name) throws IOException {
        return new URL(name).openConnection();
    }
    

}
