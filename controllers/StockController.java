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
import java.util.HashSet;
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

/**
 * The controller that is used when downloading
 * stock data.
 *
 */
public class StockController extends Controller {
    private static final int BUTTON_POSITION = 40;
    private static final int BUTTON_HEIGHT = 35;
    private static final int BUTTON_WIDTH = 180;
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
        chooseUrlBySymbol();
        startCanvas();

        System.out.println(myCurrentStock.getStockInfo());
        System.out.println(myCurrentStock.getRequestTypes());
        StockDataSet resultSet = (StockDataSet) myCurrentStock.process("");
        resultSet.sort("Date");
        System.out.println(resultSet.getData("Close"));
        System.out.println(resultSet.getData("Date"));
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
        startCanvas();
    }

    private String getStockSourceUrl(String symbol) {
        // this url returns a csv file from google.
        return "http://www.google.com/finance/historical?q="
                + symbol + "&output=csv";
    }

    @Override
    protected void startCanvas () {
        // set up menu
        Point2D menuPosition = new Point2D.Double(Constants.CANVAS_WIDTH
                - Constants.MENU_WIDTH, Constants.HEADER_HEIGHT);
        Dimension menuSize = new Dimension(Constants.MENU_WIDTH,
                Constants.CANVAS_HEIGHT - Constants.HEADER_HEIGHT);
        Menu defaultMenu = new Menu(menuPosition, menuSize, "Options");
        getCanvas().addView(defaultMenu);

        Map<String, String> info = myCurrentStock.getStockInfo();
        Header defaultHeader = new Header(new Point2D.Double(0, 0),
                new Dimension(Constants.CANVAS_WIDTH, Constants.HEADER_HEIGHT),
                info.get("Company Name"),
                info.get("Symbol"),
                info.get("Last Price"));
        getCanvas().addView(defaultHeader);

        createButtons(myCurrentStock.getRequestTypes(), defaultMenu);

        TreeMap<Date, Double> tree = new TreeMap<Date, Double>();
        StockDataSet resultSet = (StockDataSet) myCurrentStock.process("");
        List<Comparable> datesList = resultSet.getData("Date");
        List<Comparable> pricesList = resultSet.getData("Close");
        Iterator dates = datesList.iterator();
        Iterator prices = pricesList.iterator();
        Date date;
        Double price;

        // for now, limit the number of points to 20
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

    // there is a button corresponding to each request type in the model
    // this helper method populates the menu with them.
    private void createButtons (Set<String> requestTypes, Menu m) {
        int positionOfNextButton = BUTTON_POSITION;
        for (String label : requestTypes) {
            Point2D buttonPosition = new Point2D.Double(10,
                    positionOfNextButton);
            Dimension buttonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
            Button btn = new Button(buttonPosition, buttonSize, label);
            m.addChild(btn);
            positionOfNextButton += BUTTON_POSITION;
        }
    }
}
