package Views.labels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import Views.View;

/**
 * This class specifies how to display an
 * error on the canvas.
 */
public class ErrorView extends View {
    private static final int PADDING_TOP = 25;
    private static final int PADDING_LEFT = 10;
    private String myMessage;
    private Color myTextColor = Color.RED;

    /**
     * Initializes an error message to be displayed on the screen.
     *
     * @param position of the top left corner of the error message
     * @param size of the error message label
     * @param message displayed on the label
     * @param bgColor background color
     * @param textColor text color
     */
    public ErrorView(Point2D position, Dimension size,
            String message, Color bgColor, Color textColor) {
        super(position, size, bgColor);
        myMessage = message;
        myTextColor = textColor;
    }
    /**
     * Initializes an error message to be displayed on the screen.
     *
     * @param position of the top left corner of the error message
     * @param size of the error message label
     * @param message displayed on the label
     */
    public ErrorView(Point2D position, Dimension size, String message) {
        super(position, size);
        myMessage = message;
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
