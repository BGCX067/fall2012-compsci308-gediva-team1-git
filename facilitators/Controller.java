package facilitators;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import views.Button;
import views.Canvas;
import views.Header;
import views.Menu;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Controller {

    private Canvas myCanvas;
    private static final JFileChooser CHOOSER = new JFileChooser(
            System.getProperties().getProperty("user.dir"));
    
    public Controller(Canvas display) {
        myCanvas = display;
        if (chooseFile()) {
            startCanvas();
        } else {
            showError();
        }
    }
    
    public boolean chooseFile() {
        int response = CHOOSER.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            return true;
        }
        else return false;
    }
    
    private void startCanvas() {
        Point2D menuPosition = new Point2D.Double(Constants.CANVAS_WIDTH - 200, Constants.HEADER_HEIGHT);
        Dimension menuSize = new Dimension(1000, Constants.CANVAS_HEIGHT - Constants.HEADER_HEIGHT);
        Menu defaultMenu = new Menu(menuPosition, menuSize, "Options", Color.DARK_GRAY, Color.WHITE);
        myCanvas.addView(defaultMenu);
        
        Point2D buttonPosition = new Point2D.Double(10,45);
        Dimension buttonSize = new Dimension(100,40);
        Button testButton = new Button(buttonPosition, buttonSize, "click me");
        defaultMenu.addChild(testButton);
          
        Header defaultHeader = new Header(new Point2D.Double(0,0),
                new Dimension(Constants.CANVAS_WIDTH, 30),
                "Apple Inc.", "AAPL", "652.59");
        myCanvas.addView(defaultHeader);
    }
    
    private void showError() {
        Point2D errorPosition = new Point2D.Double(Constants.CANVAS_WIDTH - 200, Constants.HEADER_HEIGHT);
        Dimension errorSize = new Dimension(1000, Constants.CANVAS_HEIGHT - Constants.HEADER_HEIGHT);
        Menu errorMessage = new Menu(errorPosition, errorSize,
                "Either the file was invalid or not found :( Please close the app and try again.");
        myCanvas.addView(errorMessage);
    }
    
}
