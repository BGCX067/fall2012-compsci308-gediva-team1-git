package facilitators;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.io.File;
import javax.swing.JFileChooser;
import views.Canvas;
import views.ErrorView;

public abstract class Controller {
    private Canvas myCanvas;
    private static final JFileChooser CHOOSER = new JFileChooser(
            System.getProperties().getProperty("user.dir"));
    
    public void init(Canvas display) {
        myCanvas = display;
        chooseFile();
    }
    
    public void chooseFile() {
        int response = CHOOSER.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            startModel(CHOOSER.getSelectedFile());
            startCanvas();
        }
        else showError();
    }
    
    protected abstract void startModel(File f);
    protected abstract void startCanvas();
    
    private void showError() {
        Point2D errorPosition = new Point2D.Double(0, 0);
        Dimension errorSize = new Dimension(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
        ErrorView errorMessage = new ErrorView(
                errorPosition, errorSize,
                "Either the file was invalid or not found :(\n Please close the app and try again.");
        myCanvas.addView(errorMessage);
    }
    
    protected Canvas getCanvas() {
        return myCanvas;
    }
    
}
