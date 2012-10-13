package views.graphs;

import facilitators.Constants;
import facilitators.Date;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Map;

/**
 * Creates and draws a line graph for the data in Map<> values.
 * Uses the abstract class Graph to determine its points, and
 * uses its own paintGraph method to paint the data plot info.
 *
 * @author Jesse Starr
 *
 */
public class LineGraph extends Graph<Date, Double> {

    /**
     * Initializes a line graph from given information.
     *
     * @param position the position of the top left corner
     * of the graph window
     *
     * @param size of the window of the graph
     * @param values a map of x values to corresponding
     * y values
     *
     * @param xAxisLabel the label on the x axis
     * @param yAxisLabel the label on the y axis
     */
    public LineGraph (Point2D position, Dimension size,
            Map<Date, Double> values, String xAxisLabel, String yAxisLabel) {
        super(position, size, values, xAxisLabel, yAxisLabel);
    }

    /**
     * Paints the points and the line connecting them.
     *
     * @param pen is used to draw
     */
    public void paintData(Graphics2D pen) {
        setMyPoints((ArrayList<Point2D>) calculatePoints());

        //fill in the points
        for (Point2D p : getMyPoints()) {
            pen.fillOval((int) p.getX(), (int) p.getY(),
                    Constants.GRAPH_POINT_SIZE, Constants.GRAPH_POINT_SIZE);
        }

        //connect the points
        connectPoints(pen);
    }

    /**
     * Connects all of the points in the graph
     * with a line.
     *
     * @param pen
     */
    private void connectPoints(Graphics2D pen) {
        //draw line from origin to start
        pen.drawLine((int) getMyOrigin().getX(),
                (int) getMyOrigin().getY(),
                (int) getMyPoints().get(0).getX(),
                (int) getMyPoints().get(0).getY());

        //connect the rest of the points
        for (int x = 0; x < getMyPoints().size() - 1; x++) {
            Point2D curPoint = getMyPoints().get(x);
            Point2D nextPoint = getMyPoints().get(x + 1);

            pen.drawLine((int) curPoint.getX(), (int) curPoint.getY(),
                    (int) nextPoint.getX(), (int) nextPoint.getY());
        }
    }
}
