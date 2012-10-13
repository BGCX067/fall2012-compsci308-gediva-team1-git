package controllers;

import facilitators.Constants;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFileChooser;
import views.Canvas;
import views.labels.ErrorView;

/**
 * Abstract controller class that defines
 * some methods to be used by a subclass
 * of controller specific to a type of data.
 *
 */
public abstract class Controller {
    private static final JFileChooser CHOOSER = new JFileChooser(
            System.getProperties().getProperty("user.dir"));
    private Canvas myCanvas;

    /**
     * Initializes the controller with a canvas.
     *
     * @param display is the canvas (screen)
     */
    public void init(Canvas display) {
        myCanvas = display;
    }

    /**
     * Lets the user choose a file to download
     * data.
     */
    public void chooseFile() {
        int response = CHOOSER.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            startModel(CHOOSER.getSelectedFile());
            startCanvas();
        }
        else {
            showError();
        }
    }

    /**
     * Lets the user type in a URL to load in data from.
     */
    public void chooseUrl() {
        String url = "";
        System.out.println("Enter a url for data: ");

        try {
            BufferedReader bufferRead = new BufferedReader(
                    new InputStreamReader(System.in));
            url = bufferRead.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        startModel(url);
    }

    protected abstract void startModel(File f);
    protected abstract void startModel(String f);
    protected abstract void startCanvas();

    private void showError() {
        Point2D errorPosition = new Point2D.Double(0, 0);
        Dimension errorSize = new Dimension(Constants.CANVAS_WIDTH,
                Constants.CANVAS_HEIGHT);
        ErrorView errorMessage = new ErrorView(
                errorPosition, errorSize,
                "Either the file was invalid or not found :"
                + "(\n Please close the app and try again.");
        myCanvas.addView(errorMessage);
    }

    protected Canvas getCanvas() {
        return myCanvas;
    }
}
