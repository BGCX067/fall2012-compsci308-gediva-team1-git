package views.labels;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import views.View;

/**
 * Creates the header for the current view screen.
 * Dependent on current stock that is loaded.
 *
 * @author Jesse Starr
 */
public class Header extends View {
    private static final int TOP_PADDING = 20;
    private static final int LEFT_PADDING = 10;
    private String myTitle;
    private String mySubtitle;
    private String myPrice;
    private Color myWordColor;

    /**
     * Initializes the header for the currently loaded stock on the canvas.
     *
     * @param position of the top left corner of the header
     * @param size of the header label
     * @param title main title of the header
     * @param subtitle of the header
     * @param price the price of the currently displayed stock
     * @param bgcolor background color
     * @param wordColor text color
     */
    public Header (Point2D position, Dimension size, String title,
            String subtitle, String price, Color bgcolor, Color wordColor) {
        super(position, size, bgcolor);
        myWordColor = wordColor;
        myTitle = title;
        mySubtitle = subtitle;
        myPrice = price;
    }

    /**
     * Initializes the header for the currently loaded stock on the canvas.
     *
     * @param position of the top left corner of the header
     * @param size of the header label
     * @param title main title of the header
     * @param subtitle of the header
     * @param price the price of the currently displayed stock
     */
    public Header (Point2D position, Dimension size, String title,
            String subtitle, String price) {
        super(position, size);
        myWordColor = Color.BLACK;
        myTitle = title;
        mySubtitle = subtitle;
        myPrice = price;
    }

    /**
     * Initializes a generic header.
     *
     * @param position of the top left corner of the header
     * @param size of the header label
     * @param title main title of the header
     * @param subtitle of the header
     */
    public Header (Point2D position, Dimension size, String title,
            String subtitle) {
        super(position, size);
        myWordColor = Color.BLACK;
        myTitle = title;
        mySubtitle = subtitle;
    }

    /**
     * Changes the title of this header.
     *
     * @param title what to change the main
     * title to
     */
    public void setTitle(String title) {
        myTitle = title;
    }

    /**
     * Changes the subtitle of this header.
     *
     * @param subtitle the string to change
     * the subtitle to
     */
    public void setSubtitle(String subtitle) {
        mySubtitle = subtitle;
    }

    /**
     * Changes the price displayed by this header.
     *
     * @param price the new price
     */
    public void setPrice(String price) {
        myPrice = price;
    }

    @Override
    public void paint(Graphics2D pen) {
        super.paintBackground(pen);

        Font large = new Font("Helvetica", Font.BOLD, 22);
        pen.setColor(myWordColor);
        pen.setFont(large);
        String header = String.format(
                "     Stock name: %s     Symbol: %s     Price: %s",
                myTitle, mySubtitle, myPrice);

        pen.drawString(header, (int)getPosition().getX() + LEFT_PADDING,
                (int)getPosition().getY() + TOP_PADDING);
    }
}
