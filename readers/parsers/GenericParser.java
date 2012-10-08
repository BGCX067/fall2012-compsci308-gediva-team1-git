package readers.parsers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import javax.imageio.IIOException;

/**
 * 
 * @author GedivaTeam1
 *
 */
public abstract class GenericParser {
        
        public GenericParser(){
        }
        
        public BufferedReader generateReader(String name) throws FileNotFoundException,IOException{
            try{
                return new BufferedReader(getReader(name));
            }
            catch(FileNotFoundException e){
                System.err.println("File not found exception: "+ e.getMessage());  
                e.printStackTrace();
                throw new FileNotFoundException("FileNotFoundException: Make sure the file is accessible");
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                System.err.println("Caught IOException: "+ e.getMessage());
                e.printStackTrace();
                throw new IOException("IOException");
            }
            finally{
             //reopen window?
            }
        }
        
        /**
         * 
         * @param name
         */
        protected abstract Reader getReader (String name) throws IOException;
        
        public abstract Boolean isSupported(String name);

        

}
