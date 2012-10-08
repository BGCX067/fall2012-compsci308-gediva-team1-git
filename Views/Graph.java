package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import facilitators.Date;

/**
 * Generic graph class, used to make
 * graphs for sets of data. Graph takes
 * a position (from which to draw the background),
 * a size of the background, a Map of x axis values
 * to y axis values, and labels for x axis and y axis.
 * This class deals with setting up the basic information
 * for the graph and painting the axes, labels,
 * and values on the axes. Painting the graph's
 * points, bars, lines, etc. is up to the subclass
 * of graph since there are different kinds of
 * ways to show (paint) the data.
 *
 *
 * @param <K> the type of xValue (usually dates)
 * @param <V> the type of yValue
 */
public class Graph<K extends Comparable<? super K>, 
        V extends Comparable<? super V>> extends View {

    protected Point2D myOrigin;
    protected Map<K, V> myValues;
    protected Map<V, Number> yValuesToPoints;
    protected List<Point2D> myPoints;
    private int yMax;
    private int xMax;
    private String yLabel;
    private String xLabel;

    public Graph (Point2D position, Dimension size, Map<K, V> values,
            String xAxisLabel, String yAxisLabel) {
        super(position, size);

        //offset origin to make room for axis labels
        myOrigin = new Point2D.Double(getPosition().getX() + 10,
                getPosition().getY() + getSize().height - 10);

        yMax = (int) myOrigin.getY() - getSize().height + 20;
        xMax = getSize().width + (int) myOrigin.getX() - 20;

        yLabel = yAxisLabel;
        xLabel = xAxisLabel;

        myValues = values;
    }

    /**
     * Paints background, axis, and labels of the graph
     */
    @Override
    public void paint(Graphics2D pen) {
        super.paintBackground(pen);

        //draw y axis
        pen.drawLine((int) myOrigin.getX(), (int) myOrigin.getY(),
                (int) myOrigin.getX(), yMax);

        //draw x axis
        pen.drawLine((int) myOrigin.getX(), (int) myOrigin.getY(),
                xMax, (int) myOrigin.getY());

        //draw axes labels
        pen.drawString(yLabel, (int) getPosition().getX() - 10,
                ((int) myOrigin.getY() + yMax) / 2);

        pen.drawString(xLabel, ((int) myOrigin.getX() + xMax) / 2,
                (int) myOrigin.getY() + 10);

        //draw axes values and tick marks
        writeAxesValues(pen);
    }

    public void writeAxesValues(Graphics2D pen) {
        int xScale = getXScale();      //spacing of x values
        int yScale = getYScale();      //spacing of y values

        //sort the values given to the graph so that
        // the axes values are in order
        List<K> xAxis = new ArrayList<K>();
        List<V> yAxis = new ArrayList<V>();
        xAxis.addAll(myValues.keySet());
        yAxis.addAll(myValues.values());
        Collections.sort(xAxis);
        Collections.sort(yAxis);

        Iterator<K> xVals = xAxis.iterator();
        Iterator<V> yVals = yAxis.iterator();

        //xPos - current x position on the x axis
        //yPos - current y position on the y axis
        int xPos = (int) myOrigin.getX() + xScale;
        int yPos = (int) myOrigin.getY() - yScale;

        pen.setColor(Color.BLACK);
        Font label = new Font("Helvetica", Font.BOLD, 14);
        pen.setFont(label);

        //draw tick marks, values, and puts the y values into a map
        // with its y position relative to the origin (for use by 
        // subclasses to determine all their (x,y) points)
        while (xVals.hasNext()) {
            pen.drawLine(xPos, (int) myOrigin.getY() - 2, 
                    xPos, (int) myOrigin.getY() + 2); //draw tick mark on x axis

            pen.drawLine((int) myOrigin.getX() - 2, 
                    yPos, (int) myOrigin.getX() + 2, yPos); //draw tick mark on y axis

            K x = xVals.next();
            V y = yVals.next();
            pen.drawString(x.toString(), xPos, (int) myOrigin.getY() - 5);   //draw next x value
            pen.drawString(y.toString(), (int) myOrigin.getX() - 5, yPos);   //draw next y value

            yValuesToPoints.put(y, yPos);

            xPos += xScale;
            yPos -= yScale;
        }
    }

    /**
     * Get the scaling factor for the x axis.
     * @return
     */
    protected int getXScale() {
        return (xMax - (int) myOrigin.getX()) / myValues.keySet().size();
    }

    /**
     * Get the scaling factor for the y axis.
     * @return
     */
    protected int getYScale() {
        return ((int) myOrigin.getY() - yMax) / myValues.values().size();
    }
    
    /**
     * Get the point values specific to this graph.
     * Uses the xScale value as offset from origin for x value,
     * and gets the y value from the map yValuesToPoints (maps
     * y values of the data to actual points on the screen).
     * 
     */
    protected List<Point2D> getPoints() {
        int count = 1;

        for(K k : myValues.keySet()) {
            Point2D point = new Point2D.Double(getXScale() * count + myOrigin.getX(), 
                    yValuesToPoints.get(myValues.get(k)).doubleValue());

            if(!myPoints.contains(point)) {
                myPoints.add(point);
            }

            count ++;
        }
        
        return myPoints;
    }
}
