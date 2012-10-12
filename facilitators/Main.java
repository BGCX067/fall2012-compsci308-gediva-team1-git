package facilitators;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import views.Canvas;
import model.StockModel;
//import views.Canvas;
import model.responses.StockDataSet;


public class Main {
    
    private static final Dimension SIZE = new Dimension(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
    private static final String TITLE = "GEDIVA";
    private static final JFileChooser CHOOSER = new JFileChooser(
            System.getProperties().getProperty("user.dir"));
    
    
    public static void main (String[] args) { 
        
        
        //Sample model code 
        StockModel myStockModel= new StockModel("MS","Morgan Stanley");
      //  myStockModel.initialize("/data/data.csv");
        int response = CHOOSER.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            myStockModel.initialize(CHOOSER.getSelectedFile());
        }

        System.out.println(myStockModel.getStockInfo());
        System.out.println(myStockModel.getRequestTypes());
        StockDataSet resultSet=(StockDataSet) myStockModel.process("");
        System.out.println(resultSet.getData("Close"));
        
        Canvas display = new Canvas(SIZE);
        
        // create container that will work with Window manager
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add our user interface components to Frame and show it
        frame.getContentPane().add(display);
        frame.pack();
        frame.setVisible(true);
        
//        Controller controller = new StockController(display);

    }
}
