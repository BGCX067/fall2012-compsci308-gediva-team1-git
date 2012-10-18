package views.graphs;

import facilitators.Constants;
import facilitators.Date;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Map;

/**
 * Specifies the specifics for painting a bar graph.
 *
 * @author Jesse Starr
 */
public class BarGraph extends Graph<Date, Double> {
    private static final int BAR_SIZE = 8;

    /**
     * Initializes a bar graph from passed information.
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
    public BarGraph (Point2D position, Dimension size,
            Map<Date, Double> values, String xAxisLabel, String yAxisLabel) {
        super(position, size, values, xAxisLabel, yAxisLabel);
    }

    /**
     * Paints the bars corresponding
     * to the data held by the graph.
     *
     * @param pen used to paint
     */
    public void paintData(Graphics2D pen) {
        setMyPoints((ArrayList<Point2D>) calculatePoints());

        //make the bars
        drawBars(pen);
    }

    /**
     * Draws the bars representing the data held by the graph
     * on the screen.
     *
     * @param pen
     */
    private void drawBars(Graphics2D pen) {
        for (int x = 0; x < getMyPoints().size(); x++) {
            Point2D curPoint = getMyPoints().get(x);

            pen.fillRect((int)curPoint.getX() - 2, (int)curPoint.getY(),
                    BAR_SIZE, (int)(getMyOrigin().getY() - curPoint.getY()));
        }
    }

    /**
     * Returns the type of this graph (bar).
     */
    public String getType() {
        return "Bar";
    }
}
