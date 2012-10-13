package controllers;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import models.StockModel;
import models.responses.StockDataSet;
import views.Canvas;
import views.graphs.LineGraph;
import views.labels.Button;
import views.labels.Header;
import views.labels.Menu;
import facilitators.Constants;
import facilitators.Date;
import java.lang.reflect.Method;

public class StockController extends Controller{

    private StockModel currentStock;
    
    public StockController(Canvas c) {
        super.init(c);
        
        startMenu();
        
//        System.out.println(currentStock.getStockInfo());
//        System.out.println(currentStock.getRequestTypes());
//        StockDataSet resultSet = (StockDataSet) currentStock.process("");
//        resultSet.sort("Date");
//        System.out.println(resultSet.getData("Close"));
//        System.out.println(resultSet.getData("Date"));
    }
    
    @Override
    protected void startModel (File f) {
        currentStock = new StockModel("MS","Morgan Stanley");
        currentStock.initialize(f);
    }
    
    @Override
    protected void startModel (String f) {
        currentStock = new StockModel("MS","Morgan Stanley");
        currentStock.initialize(f);
    }
    
    // for url stuff, providing the symbol for the model means we can
    // scrape the name and set the StockInfo accordingly
    protected void startModel (String f, String symbol, String name) {
        currentStock = new StockModel(symbol, name);
        currentStock.initialize(f);
    }
    
    public void chooseUrlBySymbol() {
        String symbol = "";
        System.out.println("Enter a stock symbol: ");
        
        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            symbol = bufferRead.readLine();
        }
        catch(IOException e)
        {
                e.printStackTrace();
        }
        
        String name = "";
        System.out.println("Enter the company name: ");
        
        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            name = bufferRead.readLine();
        }
        catch(IOException e)
        {
                e.printStackTrace();
        }
        startModel(getStockSourceUrl(symbol), symbol, name);
    }
    
    private String getStockSourceUrl(String symbol) {
        // this url returns a csv file from google.
        return "http://www.google.com/finance/historical?q=" 
                + symbol + "&output=csv";
    }

    @Override
    protected void startCanvas () {
        
        startHeader();
        
        TreeMap<Date, Double> tree = new TreeMap<Date, Double>();
        StockDataSet resultSet = (StockDataSet) currentStock.process("");
        List<Comparable> datesList = resultSet.getData("Date");
        List<Comparable> pricesList = resultSet.getData("Close");
        Iterator dates = datesList.iterator();
        Iterator prices = pricesList.iterator();
        Date date;
        Double price;
        // for now, limit the number of points to 20
        int counter = 0;
        while(dates.hasNext() && prices.hasNext() && counter < 20) {
            date = (Date) dates.next();
            price = (Double) prices.next();
            tree.put(date, price);
            counter ++;
        }
        
        LineGraph g = new LineGraph(new Point2D.Double(0, Constants.HEADER_HEIGHT),
                new Dimension(Constants.CANVAS_WIDTH - Constants.MENU_WIDTH,
                        Constants.CANVAS_HEIGHT - Constants.HEADER_HEIGHT), 
                tree, "date", "price");
        getCanvas().addView(g);
        
        getCanvas().update();

    }
    
    public void startMenu() {
        // set up menu
        Point2D menuPosition = new Point2D.Double(Constants.CANVAS_WIDTH - Constants.MENU_WIDTH, Constants.HEADER_HEIGHT);
        Dimension menuSize = new Dimension(Constants.MENU_WIDTH, Constants.CANVAS_HEIGHT - Constants.HEADER_HEIGHT);
        Menu defaultMenu = new Menu(menuPosition, menuSize, "Options");
        getCanvas().addView(defaultMenu);
        
        createButtons(null, defaultMenu);
    }
    
    public void startHeader() {
        Map<String, String> info = currentStock.getStockInfo();
        Header defaultHeader = new Header(new Point2D.Double(0,0),
                new Dimension(1000, 30),
                info.get("Company Name"),
                info.get("Symbol"),
                info.get("Last Price"));
        getCanvas().addView(defaultHeader);
    }
    
    // there is a button corresponding to each request type in the model
    // this helper method populates the menu with them.
    private void createButtons (Set<String> requestTypes, Menu m) {
        int positionOfNextButton = 40;
        
        if (requestTypes != null) {
            //-- create buttons based on available request types...
            for (String label : requestTypes) {
                Point2D buttonPosition = new Point2D.Double(10, positionOfNextButton);
                Dimension buttonSize = new Dimension(180, 35);
                Button btn = new Button(buttonPosition, buttonSize, label);
                
                btn.addAttribute("type", label);
                m.addChild(btn);
                positionOfNextButton += 45;
            }
        }
        
        
        //-- create buttons for data loading...
        positionOfNextButton += 45;
        Point2D buttonPosition = new Point2D.Double(10, positionOfNextButton);
        Dimension buttonSize = new Dimension(180, 35);
        Button btn = new Button(buttonPosition, buttonSize, "Load File");
        setMethodForButton("respondToChooseFile", btn);
        btn.setResponder(this);
        m.addChild(btn);
        
        positionOfNextButton += 45;
        buttonPosition = new Point2D.Double(10, positionOfNextButton);
        btn = new Button(buttonPosition, buttonSize, "Load from Url");
        setMethodForButton("respondToChooseUrl", btn);
        btn.setResponder(this);
        m.addChild(btn);
        
        positionOfNextButton += 45;
        buttonPosition = new Point2D.Double(10, positionOfNextButton);
        btn = new Button(buttonPosition, buttonSize, "Load from Symbol");
        setMethodForButton("repondToChooseSymbol", btn);
        btn.setResponder(this);
        m.addChild(btn);
        
        getCanvas().update();
        
    }
    
    public void buttonTest(HashMap<String, String> attributes) {
        System.out.println("The " + attributes.get("type") + "Button was pressed.");
    }
    
    public void setMethodForButton(String methodName, Button btn) {
        try {
            Class mapClass = new HashMap<String, String>().getClass();
            Method method = this.getClass().getDeclaredMethod(methodName, new Class[] {mapClass});
            btn.setMethod(method);
            btn.setResponder(this);
          } catch (SecurityException e) {
            e.printStackTrace();
          } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    
    // button responders....
    
    
    public void repondToChooseSymbol(HashMap<String, String> attributes) {
        chooseUrlBySymbol();
        startCanvas();
    }
    
    public void respondToChooseUrl(HashMap<String, String> attributes) {
        chooseUrl();
        startCanvas();
    }
    
    public void respondToChooseFile(HashMap<String, String> attributes) {
        chooseFile();
        startCanvas();
    }
    
}
