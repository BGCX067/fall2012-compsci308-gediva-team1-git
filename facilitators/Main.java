package facilitators;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import views.Canvas;
import views.Header;
import views.Menu;


public class Main {
    
    private static final Dimension SIZE = new Dimension(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
    private static final String TITLE = "GEDIVA";
    
    public static void main (String[] args) { 
        
        Canvas display = new Canvas(SIZE);
        
        // create container that will work with Window manager
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add our user interface components to Frame and show it
        frame.getContentPane().add(display);
        frame.pack();
        frame.setVisible(true);
        
        Controller controller = new StockController(display);

    }
}
