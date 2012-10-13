package parsers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Creates a parser that takes information from
 * the web.
 */
public class WebParser extends GenericParser {
    static final String HYPERTEXT_PROTOCOL = "http:";

    protected Reader getReader (String name) throws IOException {
        return new InputStreamReader(getConnection(name).getInputStream());
    }

    private URLConnection getConnection (String name) throws IOException {
        return new URL(name).openConnection();
    }

    /**
     * Returns whether the parser is workable.
     *
     * @param name is the name of the source of
     * the web parser.
     */
    public Boolean isSupported (String name) {
        return name.startsWith(HYPERTEXT_PROTOCOL);
    }
}
