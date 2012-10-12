package views.labels;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import views.View;

/**
 * 
 * @author Jesse Starr
 * 
 * Creates the header for the current view screen.
 * Dependent on current stock that is loaded.
 *
 */
public class Header extends View{

    private String myTitle;
    private String mySubtitle;
    private String myPrice;
    private Color myWordColor;
    private int TOP_PADDING = 20;
    private int LEFT_PADDING = 10;

    public Header (Point2D position, Dimension size, String title, 
            String subtitle, String price, Color bgcolor, Color wordColor) {
        super(position, size, bgcolor);
        myWordColor = wordColor;
        myTitle = title;
        mySubtitle = subtitle;
        myPrice = price;
    }

    //constructed without color specifications
    public Header (Point2D position, Dimension size, String title, 
            String subtitle, String price) {
        super(position, size);
        myWordColor = Color.BLACK;
        myTitle = title;
        mySubtitle = subtitle;
        myPrice = price;
    }

    public void setTitle(String title) {
        myTitle = title;
    }

    public void setSubtitle(String subtitle) {
        mySubtitle = subtitle;
    }

    public void setPrice(String price) {
        myPrice = price;
    }

    @Override
    public void paint(Graphics2D pen) {
        super.paintBackground(pen);

        Font large = new Font("Helvetica", Font.BOLD, 22);
        pen.setColor(myWordColor);
        pen.setFont(large);
        String header = String.format("     Stock name: %s     Symbol: %s     Price: %s", myTitle, mySubtitle, myPrice);
        pen.drawString(header, (int)getPosition().getX() + LEFT_PADDING, (int)getPosition().getY() + TOP_PADDING);
    }
}
