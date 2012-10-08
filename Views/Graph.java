package views;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Graph<T> extends View{
    protected Point2D myOrigin;
    protected int yMax;
    protected int xMax;
    protected ArrayList<T> myValues;

    public Graph (Point2D position, Dimension size) {
        super(position, size);

        myOrigin = new Point2D.Double(getPosition().getX() + 10, getPosition().getY() + 10);
        yMax = getSize().height - (int) myOrigin.getY();
        xMax = getSize().width - (int) myOrigin.getX();
    }

    //make labels, axis values, scale them...
}
