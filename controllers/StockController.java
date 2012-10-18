package controllers;

import facilitators.Constants;
import facilitators.Date;
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
import java.lang.reflect.Method;
import displays.Canvas;
import displays.graphs.LineGraph;
import displays.labels.Button;
import displays.labels.Header;
import displays.labels.Menu;

/**
 * The controller that is used when downloading
 * stock data.
 *
 */
public class StockController extends Controller {
    private static final int BUTTON_POSITION_Y = 40;
    private static final int BUTTON_POSITION_X = 10;
    private static final int BUTTON_HEIGHT = 35;
    private static final int BUTTON_WIDTH = 180;
    private static final int BUTTON_SPACING = 45;
    private StockModel myCurrentStock;

    /**
     * Initializes the stock controller by
     * calling the initializer for the
     * superclass.
     * @param c is the canvas from which
     * this controller is initialized.
     */
    public StockController(Canvas c) {
        super.init(c);
        startMenu();
    }

    @Override
    protected void startModel (File f) {
        myCurrentStock = new StockModel("MS", "Morgan Stanley");
        myCurrentStock.initialize(f);
    }

    @Override
    protected void startModel (String f) {
        myCurrentStock = new StockModel("MS", "Morgan Stanley");
        myCurrentStock.initialize(f);
    }

    // for url stuff, providing the symbol for the model means we can
    // scrape the name and set the StockInfo accordingly
    protected void startModel (String f, String symbol, String name) {
        myCurrentStock = new StockModel(symbol, name);
        myCurrentStock.initialize(f);
    }

    /**
     * Lets the user choose a source URL by the symbol
     * of a stock that they want.
     */
    public void chooseUrlBySymbol() {
        String symbol = "";
        System.out.println("Enter a stock symbol: ");

        try {
            BufferedReader bufferRead = new BufferedReader(
                    new InputStreamReader(System.in));
            symbol = bufferRead.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String name = "";
        System.out.println("Enter the company name: ");

        try {
            BufferedReader bufferRead = new BufferedReader(
                    new InputStreamReader(System.in));
            name = bufferRead.readLine();
        }
        catch (IOException e) {
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
        StockDataSet resultSet = (StockDataSet) myCurrentStock.process("");
        List<Comparable> datesList = resultSet.getData("Date");
        List<Comparable> pricesList = resultSet.getData("Close");
        Iterator dates = datesList.iterator();
        Iterator prices = pricesList.iterator();
        Date date;
        Double price;

        int counter = 0;
        while (dates.hasNext() && prices.hasNext() && counter < 20) {
            date = (Date) dates.next();
            price = (Double) prices.next();
            tree.put(date, price);
            counter++;
        }

        LineGraph g = new LineGraph(new Point2D.Double(0,
                Constants.HEADER_HEIGHT),
                new Dimension(Constants.CANVAS_WIDTH - Constants.MENU_WIDTH,
                        Constants.CANVAS_HEIGHT - Constants.HEADER_HEIGHT),
                tree, "date", "price");
        getCanvas().addView(g);
        getCanvas().update();
    }

    public void startMenu() {
        // set up menu
        Point2D menuPosition = new Point2D.Double(Constants.CANVAS_WIDTH
                - Constants.MENU_WIDTH, Constants.HEADER_HEIGHT);
        Dimension menuSize = new Dimension(Constants.MENU_WIDTH,
                Constants.CANVAS_HEIGHT - Constants.HEADER_HEIGHT);
        Menu defaultMenu = new Menu(menuPosition, menuSize, "Options");
        getCanvas().addView(defaultMenu);

        createButtons(null, defaultMenu);
    }

    public void startHeader() {
        Map<String, String> info = myCurrentStock.getStockInfo();
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
        int yPositionOfNextButton = BUTTON_POSITION_Y;

        String btnNames[] = {"Load File", "Load from Url",
                "Load from Symbol", "Switch graph view"};
        
        String btnMethods[] = {"respondToChooseFile", "respondToChooseUrl",
                "respondToChooseSymbol", "respondToToggleGraph"};
        
        
        for (int i = 0; i < btnNames.length; i++) {
            yPositionOfNextButton += BUTTON_SPACING;
            Point2D buttonPosition = new Point2D.Double(BUTTON_POSITION_X, yPositionOfNextButton);
            Dimension buttonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
            Button btn = new Button(buttonPosition, buttonSize, btnNames[i]);
            btn.setMethod(btnMethods[i], this);
            btn.setResponder(this);
            m.addChild(btn);
        }

        getCanvas().update();
    }

    // button responders....

    public void respondToChooseSymbol(HashMap<String, String> attributes) {
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

    public void respondToToggleGraph(HashMap<String, String> attributes) {
        toggleGraph();
        getCanvas().update();
    }
}
