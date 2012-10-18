package Views.labels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import Views.View;

/**
 * This class describes how to paint the menu
 * on the canvas.
 */
public class Menu extends View {
    private static final int PADDING_TOP = 25;
    private static final int PADDING_LEFT = 10;
    private String myTitle;
    private Color myTextColor = Color.BLACK;

    /**
     * Initializes the menu to be painted.
     *
     * @param position of the top left corner
     * @param size of the menu
     * @param title of the menu
     * @param bgColor background color
     * @param textColor text color
     */
    public Menu(Point2D position, Dimension size,
            String title, Color bgColor, Color textColor) {
        super(position, size, bgColor);
        myTitle = title;
        myTextColor = textColor;
    }

    /**
     * Initializes the menu to be painted.
     *
     * @param position of the top left corner
     * @param size of the menu
     * @param title of the menu
     */
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
