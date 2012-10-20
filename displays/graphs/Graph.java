package displays.graphs;

import facilitators.Constants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import displays.View;

/**
 * @author Jesse Starr
 *
 * Generic graph class, used to make
 * graphs (x and y axis types) for sets of data. Graph takes
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
 * @param <V> the type of yValue (e.g. price)
 */
public abstract class Graph<K extends Comparable<? super K>,
        V extends Comparable<? super V>> extends View {

    private static final int AXIS_EDGE_BUFFER = 10;
    private static final int AXIS_LABEL_BUFFER = 20;
    private Point2D myOrigin;
    private Map<K, V> myXValuesToYValues;
    private Map<V, Number> myYValuesToYPixelVal;
    private List<Point2D> myPoints;
    private int myYAxisHeight;
    private int myXAxisLength;
    private String myYAxisLabel;
    private String myXAxisLabel;

    /**
     * Initializes the graph's origin, x-axis length/label,
     * y-axis height/label, and x and y values.
     *
     * @param position the position of the top left corner
     * of the graph window
     *
     * @param size of the window of the graph
     * @param values a map of x values to corresponding
     * y values
     *
     * @param x the label on the x axis
     * @param y the label on the y axis
     */
    public Graph (Point2D position, Dimension size, Map<K, V> values,
            String x, String y) {
        super(position, size);

        setMyOrigin(new Point2D.Double(getPosition().getX() +
                    Constants.GRAPH_ORIGIN_OFFSET,
                getPosition().getY() + getSize().height -
                    Constants.GRAPH_ORIGIN_OFFSET));

        myYAxisHeight = (int) getMyOrigin().getY() -
                getSize().height +
                Constants.GRAPH_ORIGIN_OFFSET +
                AXIS_EDGE_BUFFER;

        myXAxisLength = getSize().width +
                (int) getMyOrigin().getX() -
                Constants.GRAPH_ORIGIN_OFFSET -
                AXIS_EDGE_BUFFER;

        myYAxisLabel = y;
        myXAxisLabel = x;

        myXValuesToYValues = values;

        myYValuesToYPixelVal = new TreeMap<V, Number>();
        myPoints = new ArrayList<Point2D>();
    }

    /**
     * Paints background, axis, and labels of the graph.
     *
     * @param pen is used to paint
     */
    @Override
    public void paint(Graphics2D pen) {
        super.paintBackground(pen);

        drawAxes(pen);
        paintData(pen);
    }

    /**
     * Draws y axis, x axis, labels for both axes,
     * and the values (with tick marks) for both axes.
     *
     * @param pen
     */
    private void drawAxes(Graphics2D pen) {
        pen.drawLine((int) getMyOrigin().getX(), (int) getMyOrigin().getY(),
                (int) getMyOrigin().getX(), myYAxisHeight);
        pen.drawLine((int) getMyOrigin().getX(), (int) getMyOrigin().getY(),
                myXAxisLength, (int) getMyOrigin().getY());

        pen.drawString(myYAxisLabel,
                       (int) getPosition().getX() - AXIS_LABEL_BUFFER,
                ((int) getMyOrigin().getY() + myYAxisHeight) / 2);

        pen.drawString(myXAxisLabel,
                       ((int) getMyOrigin().getX() + myXAxisLength) / 2,
                (int) getMyOrigin().getY() + AXIS_LABEL_BUFFER);

        writeAxesValues(pen);
    }

     /**
     * Called by a subclass to paint itself depending
     * on the type of graph.
     *
     * @param pen
     */
    protected abstract void paintData(Graphics2D pen);

    /**
     * Returns the type of graph.
     */
    public abstract String getType();

    /**
     * @return the map of x values to y values
     */
    public Map<K, V> getVals() {
        return myXValuesToYValues;
    }

    /**
     * Writes all the values (and tick marks) along the
     * x and y axes.
     *
     * @param pen
     */
    private void writeAxesValues(Graphics2D pen) {
        List<K> xAxisValues = new ArrayList<K>();
        List<V> yAxisValues = new ArrayList<V>();

        xAxisValues.addAll(myXValuesToYValues.keySet());
        yAxisValues.addAll(myXValuesToYValues.values());
        Collections.sort(xAxisValues);
        Collections.sort(yAxisValues);

        Iterator<K> xValues = xAxisValues.iterator();
        Iterator<V> yValues = yAxisValues.iterator();

        int xAxisPosition = (int) getMyOrigin().getX() + getXScale();
        int yAxisPosition = (int) getMyOrigin().getY() - getYScale();

        pen.setColor(Color.BLACK);
        Font label = new Font("Helvetica", Font.BOLD, 12);
        pen.setFont(label);

        while (xValues.hasNext()) {
            drawTickMarks(pen, xAxisPosition, yAxisPosition);
            drawAxesValues(pen, xAxisPosition, yAxisPosition,
                    xValues.next(), yValues.next());

            xAxisPosition += getXScale();
            yAxisPosition -= getYScale();
        }
    }

    /**
     * Draws tick marks along the axis at the specified
     * x position (on the x axis) and y position (y axis).
     *
     * @param pen
     * @param xPos x position of the tick mark on x axis
     * @param yPos y position of tick mark on y axis
     */
    private void drawTickMarks(Graphics2D pen, int xPos, int yPos) {
        pen.drawLine(xPos, (int) getMyOrigin().getY() - 2,
                xPos, (int) getMyOrigin().getY() + 2);

        pen.drawLine((int) getMyOrigin().getX() - 2,
                yPos, (int) getMyOrigin().getX() + 2, yPos);
    }

    /**
     * Draws the values along the x and y axes next to the tick
     * marks specified by x position and y position.
     * Also map current y value to its y position (in pixels)
     * from the origin. This map will be used by a subclass
     * to find where all its points are (in pixel location)
     * relative to the origin.
     *
     * @param pen
     * @param xPos x position of value on x axis
     * @param yPos y position of value on y axis
     * @param x the x value to print
     * @param y the y value to print
     */
    private void drawAxesValues(Graphics2D pen, int xPos, int yPos, K x, V y) {
        pen.drawString(x.toString(), xPos - 25, (int) getMyOrigin().getY() - 5);
        pen.drawString(y.toString(), (int) getMyOrigin().getX() - 25, yPos + 5);

        myYValuesToYPixelVal.put(y, yPos);
    }

    /**
     * Returns the scaling factor for the x axis
     * (how far apart the x values are spaced on the axis).
     *
     * @return
     */
    protected int getXScale() {
        return (myXAxisLength - (int) getMyOrigin().getX()) /
                myXValuesToYValues.keySet().size();
    }

    /**
     * Returns the scaling factor for the y axis
     * (how far apart the y values are space on the axis).
     *
     * @return
     */
    protected int getYScale() {
        return ((int) getMyOrigin().getY() - myYAxisHeight) /
                myXValuesToYValues.values().size();
    }

    /**
     * Returns the point values specific to this graph.
     * Uses the xScale value as offset from origin for x value,
     * and gets the y value from the map yValuesToPoints (maps
     * y values of the data to actual pixel locations on the screen).
     * The point values are pixel locations of the data made line up
     * to the graph's origin.
     *
     */
    protected List<Point2D> calculatePoints() {
        int count = 1;

        for (K k : myXValuesToYValues.keySet()) {
            Point2D point = new Point2D.Double(getXScale() * count +
                    getMyOrigin().getX(),
                    myYValuesToYPixelVal.get(
                            myXValuesToYValues.get(k)).doubleValue());

            if (!getMyPoints().contains(point)) {
                getMyPoints().add(point);
            }

            count++;
        }
        return getMyPoints();
    }

    /**
     * Updates the points representing the data held
     * by the graph.
     *
     * @param myPoints the list of points on the graph
     * (position relative to origin of graph)
     */
    protected void setMyPoints (List<Point2D> points) {
        this.myPoints = points;
    }

    /**
     * Returns a list of points held by the graph.
     *
     * @return
     */
    protected List<Point2D> getMyPoints () {
        return myPoints;
    }

    /**
     * Updates the origin of the graph.
     * The origin's position is relative to the
     * Canvas.
     *
     * @param myOrigin
     */
    private void setMyOrigin (Point2D origin) {
        this.myOrigin = origin;
    }

    /**
     * Returns the point of the
     * origin of the graph (relative
     * to the Canvas origin).
     *
     * @return
     */
    public Point2D getMyOrigin () {
        return myOrigin;
    }
}
