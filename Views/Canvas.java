package views;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import javax.swing.JComponent;

/**
 * This class describes how to display/initialize
 * the canvas (window screen displayed to the user)
 * for the current program.
 */
public class Canvas extends JComponent {
    private View myRootView;

    /**
     * Initializes the canvas (window screen)
     * to be displayed to the user.
     *
     * @param size of the canvas
     */
    public Canvas (Dimension size) {
     // request component size
        setPreferredSize(size);
        // set component to receive user input
        setFocusable(true);
        requestFocus();
        Point2D rootPosition = new Point2D.Double(0, 0);
        Dimension rootSize = new Dimension(getWidth() , getHeight());
        myRootView = new View(rootPosition, rootSize);
        repaint();
        initListener();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D pen = (Graphics2D) g;
        myRootView.paint(pen);
    }

    /**
     * Adds a view (graph or label) to the root view.
     *
     * @param v the view to be added
     */
    public void addView(View v) {
        myRootView.addChild(v);
    }

    /**
     * Updates all of the views of this canvas.
     */
    public void update() {
        repaint();
    }

    /**
     * @return the root view
     */
    public View getRoot() {
        return myRootView;
    }

    /**
     * Initializes a listener to listen for mouse
     * events.
     */
    private void initListener() {
        addMouseListener(new MouseListener() {
            @Override
            public void mousePressed (MouseEvent e) {
            }

            @Override
            public void mouseClicked (MouseEvent e) {
                myRootView.mouseClicked(e.getPoint());
            }

            @Override
            public void mouseEntered (MouseEvent e) {
            }

            @Override
            public void mouseExited (MouseEvent e) {
            }

            @Override
            public void mouseReleased (MouseEvent e) {
            }
        });
    }
}
