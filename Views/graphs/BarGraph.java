package views.graphs;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Map;
import facilitators.Date;

/**
 * 
 * @author Jesse Starr
 *
 */
public class BarGraph extends Graph<Date, Double> {

    public BarGraph (Point2D position, Dimension size,
            Map<Date, Double> values, String xAxisLabel, String yAxisLabel) {
        super(position, size, values, xAxisLabel, yAxisLabel);
    }
    
    /**
     * Paints the points and the line connecting them.
     */ 
    public void paintGraph(Graphics2D pen) {
        setMyPoints((ArrayList<Point2D>) calculatePoints());

        //fill in the points
        for(Point2D p : getMyPoints()) {
            pen.fillOval((int) p.getX(), (int) p.getY(), 5, 5);
        }

        //connect the points
        drawBars(pen);
    }
    
    private void drawBars(Graphics2D pen){
        for(int x = 0; x < getMyPoints().size(); x ++) {
            Point2D curPoint = getMyPoints().get(x);
            
            pen.fillRect((int)curPoint.getX() - 2, (int)curPoint.getY(), 8, (int)(getMyOrigin().getY() - curPoint.getY()));
        }
    }
}
