package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class View {
    
    private ArrayList<View> myChildren = new ArrayList<View>();
    private Color myBackgroundColor = Color.WHITE;
    private Point2D myPosition;

    private Dimension mySize;
    
    public View(Point2D position, Dimension size, Color bgcolor) {
        myPosition = position;
        mySize = size;
        myBackgroundColor = bgcolor;
    }
    
    // if no bgcolor is provided, white is assumed
    public View(Point2D position, Dimension size) {
        myPosition = position;
        mySize = size;
    }
    
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
    }
    
    public void addChild(View v) {
        v.offsetPosition(myPosition);
        myChildren.add(v);
    }
    
    public void removeChild(View v) {
        Point2D inverseOffset = new Point2D.Double(- myPosition.getX(), - myPosition.getY());
        v.offsetPosition(inverseOffset);
        myChildren.remove(v);
    }
    
    public void offsetPosition(Point2D offset) {
        myPosition = new Point2D.Double(myPosition.getX() + offset.getX(),
                myPosition.getY() + offset.getY());
    }
    
    
    /**
     * @return the myPosition
     */
    public Point2D getPosition () {
        return myPosition;
    }

    /**
     * @return the mySize
     */
    public Dimension getSize () {
        return mySize;
    }

}
