package views.labels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import views.View;

public class Menu extends View {
   
    private String myTitle;
    private Color myTextColor = Color.BLACK;
    
    // constants
    private int PADDING_TOP = 25;
    private int PADDING_LEFT = 10;
    
    public Menu(Point2D position, Dimension size, String title, Color bgColor, Color textColor) {
        super(position, size, bgColor);
        myTitle = title;
        myTextColor = textColor;
    }
    
    // if no text color or background color is specified, assume bg = white, text = black
    public Menu(Point2D position, Dimension size, String title) {
        super(position, size);
        myTitle = title;
    }
    
    @Override
    public void paint(Graphics2D pen) {
        super.paint(pen);
        Font large = new Font("Helvetica", Font.BOLD, 18);
        pen.setColor(myTextColor);
        pen.setFont(large);
        pen.drawString(myTitle,
                (int) getPosition().getX() + PADDING_LEFT,
                (int) getPosition().getY() + PADDING_TOP);
    }
}
