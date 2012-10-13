package views.labels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import views.View;

/**
 * This class creates the buttons displayed
 * on the side of the window.
 */
public class Button extends View {
    private static final int PADDING_TOP = 25;
    private static final int PADDING_LEFT = 10;
    private static final int RADIUS = 5;
    private Color myTextColor = Color.BLUE;
    private Color myBackgroundColor = Color.LIGHT_GRAY;
    private String myMessage;

    /**
     * Initializes a button's position, size and label.
     *
     * @param position of the top left corner of the button
     * @param size of the button
     * @param message displayed on the button
     */
    public Button(Point2D position, Dimension size, String message) {
        super(position, size);
        myMessage = message;
        setFocusable(true);
        requestFocus();
    }

    /**
     * Initializes a button, but with background and text colors.
     *
     * @param position of the top left corner of the button
     * @param size of the button
     * @param message displayed on the button
     * @param bgColor background color
     * @param textColor text color
     */
    public Button(Point2D position, Dimension size,
            String message, Color bgColor, Color textColor) {
        super(position, size, bgColor);
        myTextColor = textColor;
        myMessage = message;
        myBackgroundColor = bgColor;
    }

    /**
     * Paints the button on the screen.
     *
     * @param pen used to paint
     */
    public void paint(Graphics2D pen) {
        pen.setColor(myBackgroundColor);
        pen.drawRoundRect((int) getPosition().getX(),
                (int) getPosition().getY(),
                (int) getSize().getWidth(),
                (int) getSize().getHeight(),
                RADIUS, RADIUS);
        pen.fillRoundRect((int) getPosition().getX(),
                (int) getPosition().getY(),
                (int) getSize().getWidth(),
                (int) getSize().getHeight(),
                RADIUS, RADIUS);
        Font large = new Font("Helvetica", Font.BOLD, 18);
        pen.setColor(myTextColor);
        pen.setFont(large);
        pen.drawString(myMessage,
                (int) getPosition().getX() + PADDING_LEFT,
                (int) getPosition().getY() + PADDING_TOP);
    }

    @Override
    public void mouseClicked(Point p) {
        System.out.println("The " + myMessage + " button was pressed");
    }
}
