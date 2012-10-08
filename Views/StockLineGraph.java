package views;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.TreeMap;
import facilitators.Date;

public class StockLineGraph extends Graph<Date, Double> {
    
    public StockLineGraph(Point2D position, Dimension size, 
            TreeMap<Date, Double> values, String xAxisLabel, String yAxisLabel) {

        super(position, size, values, xAxisLabel, yAxisLabel); 

        yValuesToPoints = new TreeMap<Double, Number>();
        myPoints = new ArrayList<Point2D>();
    }

    /**
     * Paints the graph and all its points.
     */
    @Override 
    public void paint(Graphics2D pen) {
        super.paint(pen);

        myPoints = (ArrayList<Point2D>) getPoints();

        //fill in the points
        for(Point2D p : myPoints) {
            pen.fillOval((int) p.getX(), (int) p.getY(), 5, 5);
        }

        //connect the points
        connectPoints(pen);
    }

    /**
     * Connects all of the points in the graph.
     *
     * @param pen
     */
    private void connectPoints(Graphics2D pen) {
        //draw line from origin to start
        pen.drawLine((int) myOrigin.getX(), (int) myOrigin.getY(), 
                (int) myPoints.get(0).getX(), (int) myPoints.get(0).getY());

        //connect the rest of the points
        for(int x = 0; x < myPoints.size() - 1; x ++) {
            Point2D curPoint = myPoints.get(x);
            Point2D nextPoint = myPoints.get(x + 1);

            pen.drawLine((int) curPoint.getX(), (int) curPoint.getY(),
                    (int) nextPoint.getX(), (int) nextPoint.getY()); 
        }
    }
}
