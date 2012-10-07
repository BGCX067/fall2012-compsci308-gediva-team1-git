package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Error extends View {
    private String myMessage;
    private Color myTextColor = Color.RED;
    
    // constants
    private int PADDING_TOP = 25;
    private int PADDING_LEFT = 10;
    
    public Error(Point2D position, Dimension size, String message, Color bgColor, Color textColor) {
        super(position, size, bgColor);
        myMessage = message;
        myTextColor = textColor;
    }
    
    // if no text color or background color is specified, assume bg = white, text = black
    public Error(Point2D position, Dimension size, String title) {
        super(position, size);
    }
    
    @Override
    public void paint(Graphics2D pen) {
        super.paint(pen);
        Font large = new Font("Helvetica", Font.BOLD, 18);
        pen.setColor(myTextColor);
        pen.setFont(large);
        pen.drawString(myMessage,
                (int) getPosition().getX() + PADDING_LEFT,
                (int) getPosition().getY() + PADDING_TOP);
    }
}
