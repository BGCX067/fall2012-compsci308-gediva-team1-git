package displays;

import controllers.StockController;
import displays.graphs.LineGraph;
import displays.labels.Button;
import displays.labels.Header;
import displays.labels.Menu;
import facilitators.Constants;
import facilitators.Date;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import models.responses.IDataSet;


/**
 * 
 * @author Mark Govea, Alex Browne, Lance Co Ting Keh, and Jesse Starr
 */
public class StockViewFactory {
    private StockController myController;

    public StockViewFactory (StockController controller) {
        myController = controller;
    }

    /**
     * Generates the view's menu and puts it in the canvas
     * 
     * @param canvas
     */
    public void startMenu (Canvas canvas) {
        // set up menu
        Point2D menuPosition =
                new Point2D.Double(Constants.CANVAS_WIDTH -
                                   Constants.MENU_WIDTH,
                                   Constants.HEADER_HEIGHT);
        Dimension menuSize =
                new Dimension(Constants.MENU_WIDTH,
                              Constants.CANVAS_HEIGHT -
                              Constants.HEADER_HEIGHT);
        Menu defaultMenu = new Menu(menuPosition, menuSize, "Options");
        canvas.addView(defaultMenu);

        createButtons(canvas, null, defaultMenu);
    }

    /**
     * Populate 
     * 
     * @param canvas
     * @param dataSet
     * @param info
     */
    public void startCanvas (Canvas canvas,
                                    IDataSet<Comparable> dataSet,
                                    Map<String, String> info) {

        startHeader(canvas, info);

        Map<Date, Double> map = new TreeMap<Date, Double>();
        List<Comparable> datesList = dataSet.getData("Date");
        List<Comparable> pricesList = dataSet.getData("Close");
        Iterator dates = datesList.iterator();
        Iterator prices = pricesList.iterator();
        Date date;
        Double price;

        int counter = 0;
        while (dates.hasNext() && prices.hasNext() && counter < 20) {
            date = (Date) dates.next();
            price = (Double) prices.next();
            map.put(date, price);
            counter++;
        }

        LineGraph g =
                new LineGraph(new Point2D.Double(0, Constants.HEADER_HEIGHT),
                              new Dimension(Constants.CANVAS_WIDTH -
                                            Constants.MENU_WIDTH,
                                            Constants.CANVAS_HEIGHT -
                                                    Constants.HEADER_HEIGHT),
                              map, "date", "price");
        canvas.addView(g);
        canvas.update();
    }

    /*
     * Generates a header for the current stock and puts it in the canvas
     */
    private void startHeader (Canvas canvas, Map<String, String> info) {
        // Map<String, String> info = myCurrentStock.getStockInfo();
        Header defaultHeader =
                new Header(new Point2D.Double(0, 0),
                           new Dimension(Constants.CANVAS_WIDTH,
                                         Constants.HEADER_HEIGHT),
                           info.get("Company Name"), info.get("Symbol"),
                           info.get("Last Price"));
        canvas.addView(defaultHeader);
    }

    // there is a button corresponding to each request type in the model
    // this helper method populates the menu with them.
    private void createButtons (Canvas canvas, Set<String> requestTypes, Menu m) {
        int yPositionOfNextButton = Constants.BUTTON_POSITION_Y;

        String[] btnNames = {
            "Load File",
            "Load from Url",
            "Load from Symbol",
            "Switch graph view"
        };

        String[] btnMethods = {
            "respondToChooseFile",
            "respondToChooseUrl",
            "respondToChooseSymbol",
            "respondToToggleGraph"
        };

        for (int i = 0; i < btnNames.length; i++) {
            yPositionOfNextButton += Constants.BUTTON_SPACING;
            Point2D buttonPosition =
                    new Point2D.Double(Constants.BUTTON_POSITION_X,
                                       yPositionOfNextButton);
            Dimension buttonSize =
                    new Dimension(Constants.BUTTON_WIDTH,
                                  Constants.BUTTON_HEIGHT);
            Button btn = new Button(buttonPosition, buttonSize, btnNames[i]);
            btn.setMethod(btnMethods[i], myController);
            btn.setResponder(myController);
            m.addChild(btn);
        }

        canvas.update();
    }

}
