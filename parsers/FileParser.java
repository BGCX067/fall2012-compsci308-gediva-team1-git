package parsers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Creates a parser that takes information from
 * a file.
 */
public class FileParser extends GenericParser {
    /**
     * Throws an IOexception if invalid file.
     */
    protected Reader getReader (String name) throws IOException {
        return new FileReader(name);
    }

    /**
     * Initializes the parser to read from a file.
     *
     * @param file the file from which to read
     * @return
     * @throws IOException the exception to be thrown
     * if the file is invalid
     */
    public Reader getReader(File file) throws IOException {
        return new FileReader(file);
    }

    /**
     * Returns whether the file can be read or not.
     *
     * @param name is the string name of a file
     */
    public Boolean isSupported (String name) {
        File file = new File(name);
        return file.canRead();
    }

    /**
     * Returns whether the file can be read or not.
     *
     * @param file is the file from which to read
     */
    public Boolean isSupported(File file) {
        return file.canRead();
    }
}
