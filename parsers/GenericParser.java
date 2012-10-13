package parsers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

/**
 * Abstract class for a file parser from which
 * to get the data.
 *
 * @author GedivaTeam1
 *
 */
public abstract class GenericParser {
    /**
     * Creates a generic parser that is empty.
     */
    public GenericParser() { }

    /**
     * Generates a parser to read from a certain file.
     *
     * @param name is the name of the file from which
     * to read
     *
     * @return
     * @throws IOException for any other error
     */
    public BufferedReader generateReader(String name) throws IOException {
        try {
            return new BufferedReader(getReader(name));
        }
        catch (FileNotFoundException e) {
            System.err.println("File not found exception: " + e.getMessage());
            e.printStackTrace();
            throw new FileNotFoundException("FileNotFoundException:"
                    + " Make sure the file is accessible");
        }
        catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
            e.printStackTrace();
            throw new IOException("IOException");
        }
        finally {
            System.out.println("Sorry, try reloading the program.");
        }
    }

    protected abstract Reader getReader (String name) throws IOException;

    /**
     * Determines whether the file is readable/openable.
     *
     * @param name is the name of the file
     * @return
     */
    public abstract Boolean isSupported(String name);
}
