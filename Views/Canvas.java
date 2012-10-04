package views;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import javax.swing.JComponent;


public class Canvas extends JComponent {
    
    View rootView;
    
    public Canvas (Dimension size) {
     // request component size
        setPreferredSize(size);
        // set component to receive user input
        setFocusable(true);
        requestFocus();
        Point2D rootPosition = new Point2D.Double(0,0);
        Dimension rootSize = new Dimension(getWidth(), getHeight());
        rootView = new View(rootPosition, rootSize);
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D pen = (Graphics2D) g;
        rootView.paint(pen);
        System.out.println("Calling paint!");
    }
    
    public void addView(View v) {
        rootView.addChild(v);
        repaint();
    }
    
    public void update() {
        repaint();
    }
    
}
