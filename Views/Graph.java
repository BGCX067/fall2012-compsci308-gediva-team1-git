package views;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Graph<T> extends View{
    protected Point2D myOrigin;
    protected int yMax;
    protected int xMax;
    protected ArrayList<T> myValues;
    protected String yLabel;
    protected String xLabel;

    public Graph (Point2D position, Dimension size,
            String xAxisLabel, String yAxisLabel) {
        super(position, size);

        //offset origin to make room for axis labels
        myOrigin = new Point2D.Double(getPosition().getX() + 10, getPosition().getY() + 10);
        yMax = getSize().height - (int) myOrigin.getY();
        xMax = getSize().width - (int) myOrigin.getX();
        yLabel = yAxisLabel;
        xLabel = xAxisLabel;
    }

    /**
     * Paints background, axis, and labels of the graph
     */
    @Override
    public void paint(Graphics2D pen) {
        super.paintBackground(pen);
        
        //draw y axis
        pen.drawLine((int) myOrigin.getX(), (int) myOrigin.getY(), (int) myOrigin.getX(), yMax);
        
        //draw x axis
        pen.drawLine((int) myOrigin.getX(), (int) myOrigin.getY(), xMax, (int) myOrigin.getY());
        
        //draw axis labels
        pen.drawString(yLabel, (int) getPosition().getX(), yMax/2);
        pen.drawString(xLabel, xMax/2, (int) getPosition().getY());
    }

    //axis values, scale them?...
}
