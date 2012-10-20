package displays;

import displays.graphs.BarGraph;
import displays.graphs.LineGraph;
import displays.labels.ErrorView;
import facilitators.Constants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFileChooser;


/**
 * This class describes how to paint a single view and/or
 * its children. The root view (in canvas) is actually the only
 * paint method called explicitly by the program, and that root view calls
 * paint on all it's children recursively.
 * 
 * @author Alex Browne and Jesse Starr
 */
public class View extends JComponent {
    private static final JFileChooser CHOOSER = new JFileChooser(
        System.getProperties().getProperty("user.dir"));
    private List<View> myChildren = new ArrayList<View>();
    private Color myBackgroundColor = Color.WHITE;
    private Color myBorderColor = Color.BLACK;
    private Point2D myPosition;
    private Dimension mySize;

    /**
     * Initializes a view.
     *
     * @param position of the top left corner
     * @param size of the view
     * @param bgcolor background color
     */
    public View(Point2D position, Dimension size, Color bgcolor) {
        myPosition = position;
        mySize = size;
        myBackgroundColor = bgcolor;
    }

    /**
     * Initializes a view.
     *
     * @param position of the top left corner
     * @param size of the view
     */
    public View(Point2D position, Dimension size) {
        myPosition = position;
        mySize = size;
    }

    /**
     * Paints the view and all of its children.
     *
     * @param pen used to paint
     */
    public void paint(Graphics2D pen) {
        paintBackground(pen);
        for (View child : myChildren) {
            child.paint(pen);
        }
    }

    protected void paintBackground(Graphics2D pen) {
        pen.setColor(myBackgroundColor);
        pen.fillRect((int) myPosition.getX(), (int) myPosition.getY(),
                (int) mySize.getWidth(), (int) mySize.getHeight());
        pen.setColor(myBorderColor);
        pen.drawRect((int) myPosition.getX(), (int) myPosition.getY(),
                (int) mySize.getWidth(), (int) mySize.getHeight());
    }

    /**
     * Adds a child to this view.
     * The children are graphs or labels (currently).
     *
     * @param v the view to be added
     */
    public void addChild(View v) {
        v.offsetPosition(myPosition);
        myChildren.add(v);
    }

    /**
     * Removes a child from this view
     * (in the case of deletion/editing of a graph
     * or reloading of data).
     * 
     * @param v the view to be removed
     */
    public void removeChild (View v) {
        Point2D inverseOffset =
                new Point2D.Double(-myPosition.getX(), -myPosition.getY());
        v.offsetPosition(inverseOffset);
        myChildren.remove(v);
    }

    /**
     * Offsets the position of the view so that
     * it and its parent do not overlap.
     * 
     * @param offset the point to offset this view's
     *        position to
     */
    public void offsetPosition (Point2D offset) {
        myPosition =
                new Point2D.Double(myPosition.getX() + offset.getX(),
                                   myPosition.getY() + offset.getY());
    }

    /**
     * Returns the type of view.
     */
    public String getType () {
        return "";
    }

    /**
     * Decides where the mouse is clicked and determines
     * whether the click is handled by this view or
     * one of its children.
     * 
     * @param point of the mouse click
     */
    public void mouseClicked (Point point) {
        // if the point is inside the bound of a child view,
        // the child takes over click interaction.
        for (View child : myChildren) {
            Point p =
                    new Point((int) child.getPosition().getX(),
                              (int) child.getPosition().getY());
            Rectangle bounds = new Rectangle(p, child.getSize());
            if (bounds.contains(point)) {
                child.mouseClicked(point);
                break;
            }
        }
    }

    /**
     * Returns the position of this view.
     */
    public Point2D getPosition () {
        return myPosition;
    }

    /**
     * Returns the size of the view.
     */
    public Dimension getSize () {
        return mySize;
    }

    /**
     * @return the list of children of this view
     */
    public List<View> getChildren () {
        return myChildren;
    }

    /**
     * Brings up a file dialogue box that allows the user to choose a
     * file from the filesystem
     * 
     * @return chosen file
     */
    public static File chooseFile () {
        int response = CHOOSER.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            return CHOOSER.getSelectedFile();
        }
        else {
            throw new RuntimeException("File chooser could not get the selected file");
        }
    }

    /**
     * Toggles the type of a displayed graph
     * 
     * @param canvas container of view to be toggled
     */
    public static void toggleGraph (Canvas canvas) {
        for (View v : canvas.getRoot().getChildren()) {
            BarGraph b = null;
            LineGraph l = null;
            if ("Bar".equals(v.getType())) {
                b = (BarGraph) v;
                l = new LineGraph(b.getPosition(), b.getSize(),
                                  b.getVals(), "Date", "Price");

            }
            else if ("Line".equals(v.getType())) {
                l = (LineGraph) v;
                b = new BarGraph(l.getPosition(), l.getSize(),
                                 l.getVals(), "Date", "Price");
            }
            else {
                break;
            }
            canvas.getRoot().removeChild(v);
            canvas.getRoot().addChild(l);
        }
    }

    /**
     * Displays a 
     * 
     * @param canvas container in which to show the error
     */
    public static void showFileSelectionError (Canvas canvas) {
        Point2D errorPosition = new Point2D.Double(0, 0);
        Dimension errorSize =
                new Dimension(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
        ErrorView errorMessage =
                new ErrorView(errorPosition, errorSize,
                              "File selection failed:" +
                                  "\nEither the file was invalid or not found :(" +
                                  "\nPlease close the app and try again. ");
        canvas.addView(errorMessage);
    }

}
