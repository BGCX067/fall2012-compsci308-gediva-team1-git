package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

/**
 * This class describes how to paint a single view and/or
 * its children. The root view (in canvas) is actually the only
 * paint method called explicitly by the program, and that root view calls
 * paint on all it's children recursively.
 */
public class View extends JComponent {
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
    public void removeChild(View v) {
        Point2D inverseOffset = new Point2D.Double(-myPosition.getX(),
                -myPosition.getY());
        v.offsetPosition(inverseOffset);
        myChildren.remove(v);
    }

    /**
     * Offsets the position of the view so that
     * it and its parent do not overlap.
     *
     * @param offset the point to offset this view's
     * position to
     */
    public void offsetPosition(Point2D offset) {
        myPosition = new Point2D.Double(myPosition.getX() + offset.getX(),
                myPosition.getY() + offset.getY());
    }

    /**
     * Returns the type of view.
     */
    public String getType() {
        return "";
    }

    /**
     * Decides where the mouse is clicked and determines
     * whether the click is handled by this view or
     * one of its children.
     *
     * @param point of the mouse click
     */
    public void mouseClicked(Point point) {
        // if the point is inside the bound of a child view,
        // the child takes over click interaction.
        for (View child : myChildren) {
            Point p = new Point((int) child.getPosition().getX(),
                    (int) child.getPosition().getY());
            Rectangle bounds = new Rectangle(p, child.getSize());
            if (bounds.contains(point)) {
                child.mouseClicked(point);
            }
        }
    }

    /**
     * Returns the position of this view.
     */
    public Point2D getPosition() {
        return myPosition;
    }

    /**
     * Returns the size of the view.
     */
    public Dimension getSize() {
        return mySize;
    }

    /**
     * @return the list of children of this view
     */
    public List<View> getChildren() {
        return myChildren;
    }
}
