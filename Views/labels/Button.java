package views.labels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import views.View;

public class Button extends View {
    
    private Color myTextColor = Color.BLUE;
    private Color myBackgroundColor = Color.LIGHT_GRAY;
    private static int myRadius = 5;
    private String myMessage;
    
    // constants
    private int PADDING_TOP = 25;
    private int PADDING_LEFT = 10;
    
    public Button(Point2D position, Dimension size, String message) {
        super(position, size);
        myMessage = message;
        setFocusable(true);
        requestFocus();        
    }
    
    public Button(Point2D position, Dimension size, String message, Color bgColor, Color textColor) {
        super(position, size, bgColor);
        myTextColor = textColor;
        myMessage = message;
        myBackgroundColor = bgColor;
    }
    
    public void paint(Graphics2D pen) {
        pen.setColor(myBackgroundColor);
        pen.drawRoundRect((int) getPosition().getX(), (int) getPosition().getY(),
                (int) getSize().getWidth(), (int) getSize().getHeight(),
                myRadius, myRadius);
        pen.fillRoundRect((int) getPosition().getX(), (int) getPosition().getY(),
                (int) getSize().getWidth(), (int) getSize().getHeight(),
                myRadius, myRadius);
        Font large = new Font("Helvetica", Font.BOLD, 18);
        pen.setColor(myTextColor);
        pen.setFont(large);
        pen.drawString(myMessage,
                (int) getPosition().getX() + PADDING_LEFT,
                (int) getPosition().getY() + PADDING_TOP);
    }
    
    @Override
    public void mouseClicked(Point p) {
        System.out.println("The "+ myMessage +" button was pressed");
    }
    
}
