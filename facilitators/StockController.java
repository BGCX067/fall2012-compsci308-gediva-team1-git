package facilitators;

import java.io.File;
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
        
    }
    
}
