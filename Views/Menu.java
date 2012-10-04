package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Menu extends View {
   
    private String myTitle;
    private Color myTextColor = Color.BLACK;
    
    // constants
    private int PADDING_TOP = 10;
    private int PADDING_LEFT = 10;
    
    public Menu(Point2D position, Dimension size, String title, Color bgColor, Color textcolor) {
        super(position, size, bgColor);
    }
    
    // if no text color is specified, black is assumed
    public Menu(Point2D position, Dimension size, String title) {
        super(position, size);
    }
    
    public void paint(Graphics2D pen) {
        Font large = new Font("Helvetica", Font.BOLD, 18);
        pen.setColor(myTextColor);
        pen.setFont(large);
        pen.drawString(myTitle, PADDING_LEFT, PADDING_TOP);
    }
}
