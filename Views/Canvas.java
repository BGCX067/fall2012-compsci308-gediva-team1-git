package views;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.swing.JComponent;


public class Canvas extends JComponent {
    
    public Canvas (Dimension size) {
     // request component size
        setPreferredSize(size);
        // set component to receive user input
        setFocusable(true);
        requestFocus();
    }
    
    public void paint(Graphics2D pen) {
        
    }
    
}
