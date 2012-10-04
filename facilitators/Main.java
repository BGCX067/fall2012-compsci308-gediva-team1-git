package facilitators;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import views.Canvas;
import views.Menu;


public class Main {
    
    private static final Dimension SIZE = new Dimension(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
    private static final String TITLE = "GEDIVA";
    
    public static void main (String[] args) {
        Canvas display = new Canvas(SIZE);
        Point2D menuPosition = new Point2D.Double(0, 0);
        Dimension menuSize = new Dimension(200, 300);
        
        Menu test = new Menu(menuPosition, menuSize, "Testing this Shit", Color.DARK_GRAY, Color.WHITE);
        display.addView(test);

        // create container that will work with Window manager
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add our user interface components to Frame and show it
        frame.getContentPane().add(display);
        frame.pack();
        frame.setVisible(true);

    }
}
