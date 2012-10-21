package controllers;

import displays.Canvas;
import displays.View;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Abstract controller class that defines
 * some methods to be used by a subclass
 * of controller specific to a type of data.
 * 
 * @author Lance Co Ting Keh, Alex Browne, Jesse Starr, and Mark Govea
 */
public abstract class Controller {
    private Canvas myCanvas;

    /**
     * Initializes the controller with a canvas.
     * 
     * @param display is the canvas (screen)
     */
    public void init (Canvas display) {
        myCanvas = display;
    }

    /**
     * Lets the user choose a file to download
     * data.
     */
    public void chooseFile () {
        File file = View.chooseFile(myCanvas);
        if (file != null) {
            startModel(file);
        }
    }

    /**
     * Lets the user type in a URL to load in data from.
     */
    public void chooseUrl () {
        String url = "";
        System.out.println("Enter a url for data: ");

        try {
            BufferedReader bufferRead =
                    new BufferedReader(new InputStreamReader(System.in));
            url = bufferRead.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        startModel(url);
    }

    /**
     * Toggles the type of graph on the screen between
     * line or bar graph.
     */
    public void toggleGraph () {
        View.toggleGraph(myCanvas);
    }

    protected Canvas getCanvas () {
        return myCanvas;
    }

    protected abstract void startModel (File f);

    protected abstract void startModel (String f);

    protected abstract void startCanvas ();

}
