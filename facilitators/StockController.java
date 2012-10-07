package facilitators;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.List;
import views.Button;
import views.Canvas;
import model.Stock;

public class StockController extends Controller{

    private Stock currentStock;
    
    public StockController(Canvas c) {
        super.init(c);
    }
    
    @Override
    protected void startModel (File f) {
        currentStock = new Stock();
        currentStock.initialize(f);
    }

    @Override
    protected void startCanvas () {
        //TODO: create basic canvas elements: Header and Menu
        createButtons(currentStock.getRequestTypes()); // also pass menu as an argument
    }
    
    // there is a button corresponding to each request type in the model
    // this helper method populates the menu with them.
    private void createButtons (List<String> requestTypes) {
        int positionOfNextButton = 30;
        for (String label : requestTypes) {
            Point2D buttonPosition = new Point2D.Double(10, positionOfNextButton);
            Dimension buttonSize = new Dimension(200, 100);
            Button btn = new Button(buttonPosition, buttonSize, label);
            //TODO: add each button as a child to the Menu
        }
    }
    
}
