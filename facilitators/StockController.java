package facilitators;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import views.Button;
import views.Canvas;
import views.Header;
import views.Menu;
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
        // set up menu
        Point2D menuPosition = new Point2D.Double(Constants.CANVAS_WIDTH - 200, Constants.HEADER_HEIGHT);
        Dimension menuSize = new Dimension(1000, Constants.CANVAS_HEIGHT - Constants.HEADER_HEIGHT);
        Menu defaultMenu = new Menu(menuPosition, menuSize, "Options");
        getCanvas().addView(defaultMenu);
        
        // Map info = currentStock.getStockInfo();
        Map<String, String> info = new HashMap<String, String>();
        info.put("name", "Apple Inc.");
        info.put("symbol", "AAPL");
        info.put("price", "652.59");
        Header defaultHeader = new Header(new Point2D.Double(0,0),
                new Dimension(Constants.CANVAS_WIDTH, 30),
                info.get("name"),
                info.get("symbol"),
                info.get("price"));
        getCanvas().addView(defaultHeader);
        
        //createButtons(currentStock.getRequestTypes(), defaultMenu);
        Set<String> types = new HashSet<String>();
        types.add("Closing Price");
        types.add("Moving Average");
        types.add("Candle Sticks");
        createButtons(types, defaultMenu);
        
    }
    
    // there is a button corresponding to each request type in the model
    // this helper method populates the menu with them.
    private void createButtons (Set<String> requestTypes, Menu m) {
        int positionOfNextButton = 40;
        for (String label : requestTypes) {
            Point2D buttonPosition = new Point2D.Double(10, positionOfNextButton);
            Dimension buttonSize = new Dimension(180, 35);
            Button btn = new Button(buttonPosition, buttonSize, label);
            m.addChild(btn);
            positionOfNextButton += 45;
        }
    }
    
}
