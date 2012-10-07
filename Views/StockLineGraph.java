package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class StockLineGraph extends Graph<Point2D> {

    private Point2D myOrigin;
    private int yMax;
    private int xMax;
    private ArrayList<Point2D> myPoints;
    
    public StockLineGraph(Point2D position, Dimension size, ArrayList<Point2D> points) {
        super(position, size);
        myPoints = points;
    }

    /**
     * Paints the graph and all it's points.
     */
    @Override 
    public void paint(Graphics2D pen) {
        super.paintBackground(pen);
        
        //draw y axis
        pen.drawLine((int) myOrigin.getX(), (int) myOrigin.getY(), (int) myOrigin.getX(), yMax);
        
        //draw x axis
        pen.drawLine((int) myOrigin.getX(), (int) myOrigin.getY(), xMax, (int) myOrigin.getY());
        
        //fill in the points
        for(Point2D p : myPoints) {
            pen.fillOval((int) p.getX(), (int) p.getY(), 5, 5);
        }
        
        //connect the points
        connectPoints(pen);
    }

    /**
     * Connects all of the points in the graph.
     * If the next point is lower on the graph, paint the line red.
     * If the next point is higher on the graph, paint it green.
     *
     * @param pen
     */
    private void connectPoints(Graphics2D pen) {
        for(int x = 0; x < myPoints.size() - 1; x ++) {
            Point2D curPoint = myPoints.get(x);
            Point2D nextPoint = myPoints.get(x + 1);

            if(nextPoint.getY() <= curPoint.getY()) {
                pen.setColor(Color.green);
            }
            else {
                pen.setColor(Color.red);
            }

            pen.drawLine((int) curPoint.getX(), (int) curPoint.getY(),
                    (int) nextPoint.getX(), (int) nextPoint.getY()); 
        }
    }
}
