package displays.labels;

import controllers.Controller;
import controllers.StockController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import displays.View;

/**
 * This class creates the buttons displayed
 * on the side of the window.
 * 
 * @author Alex Browne and Jesse Starr
 */
public class Button extends View {
    private static final int PADDING_TOP = 25;
    private static final int PADDING_LEFT = 10;
    private static final int RADIUS = 5;
    private Color myTextColor = Color.BLUE;
    private Color myBackgroundColor = Color.LIGHT_GRAY;
    private String myMessage;
    private Method myMethod;
    private Map<String, String> myAttributes;
    private Controller myResponder;

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
        myAttributes = new HashMap<String, String>();
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

    /**
     * Adds attributes to this button (determines what to do).
     *
     * @param key the action
     * @param value the result
     */
    public void addAttribute(String key, String value) {
        myAttributes.put(key, value);
    }
    
    
    /**
     * Sets the method for this button.
     * The method m must be a method of the
     * controller c.
     *
     * @param m the name of the method to set
     * @param c the controller of the button
     */
    public void setMethod(String m, StockController c) {
        try {
            Class mapClass = new HashMap<String, String>().getClass();
            Method method = c.getClass().getDeclaredMethod(m, new Class[] {mapClass});
            myMethod = method;
            myResponder = c;
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    

    /**
     * Set the instance that will respond to clicks.
     *
     * @param c the controller of the button
     */
    public void setResponder(Controller c) {
        myResponder = c;
    }

    @Override
    public void mouseClicked(Point p) {
        try {
            myMethod.invoke(myResponder, myAttributes);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
