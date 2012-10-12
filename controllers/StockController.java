package controllers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import facilitators.Constants;
import facilitators.Date;
import views.Canvas;
import views.graphs.BarGraph;
import views.graphs.LineGraph;
import views.labels.Button;
import views.labels.ErrorView;
import views.labels.Header;
import views.labels.Menu;
import models.StockModel;
import models.responses.StockDataSet;

public class StockController extends Controller{

    private StockModel currentStock;
    
    public StockController(Canvas c) {
        super.init(c);
        
        System.out.println(currentStock.getStockInfo());
        System.out.println(currentStock.getRequestTypes());
        StockDataSet resultSet = (StockDataSet) currentStock.process("");
        System.out.println(resultSet.getData("Close"));
    }
    
    @Override
    protected void startModel (File f) {
        currentStock = new StockModel("MS","Morgan Stanley");
        currentStock.initialize(f);
    }

    @Override
    protected void startCanvas () {
        // set up menu
        Point2D menuPosition = new Point2D.Double(Constants.CANVAS_WIDTH - 200, Constants.HEADER_HEIGHT);
        Dimension menuSize = new Dimension(1000, Constants.CANVAS_HEIGHT - Constants.HEADER_HEIGHT);
        Menu defaultMenu = new Menu(menuPosition, menuSize, "Options");
        getCanvas().addView(defaultMenu);
        
        Map<String, String> info = currentStock.getStockInfo();
        Header defaultHeader = new Header(new Point2D.Double(0,0),
                new Dimension(1000, 30),
                info.get("Company Name"),
                info.get("Symbol"),
                info.get("Last Price"));
        getCanvas().addView(defaultHeader);
        
        createButtons(currentStock.getRequestTypes(), defaultMenu);
        
        TreeMap<Date, Double> tree = new TreeMap<Date, Double>();
        tree.put(new Date(2012, 10, 20), 50.0);
        tree.put(new Date(2011, 10, 15), 60.0);
        tree.put(new Date(2000, 5, 9), 10.0);
        tree.put(new Date(2009, 5, 6), 15.0);
        LineGraph g = new LineGraph(new Point2D.Double(200, 200), new Dimension(500, 500), 
                tree, "time", "yaxis");
        getCanvas().addView(g);
        
        getCanvas().update();

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
