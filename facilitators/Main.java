package facilitators;

import controllers.Controller;
import controllers.StockController;
import displays.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;


/**
 * The main class for the program.
 * Begins the program.
 * 
 * @author Lance Co Ting Keh, Alex Browne, Jesse Starr, and Mark Govea
 */
public class Main {

    private static final Dimension SIZE =
            new Dimension(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);

    private static final String TITLE = "GEDIVA";

    /**
     * Runs the GEDIVA project
     * 
     * @param args unused inputs
     */
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
