package models.responses;

import java.util.List;
//import javax.media.jai.util.Range;
import databases.DataTable;
import databases.StockTable;


public class StockDataSet implements IDataSet<Double> {

    private StockTable myTable;

    public StockDataSet (DataTable<Double> st) {
        myTable = new StockTable(st);
    }

    @Override
    public List<Double> getData (String attribute) {
          List<Double> list = myTable.columnValues(attribute);
        //  for (int i = list.size() - 1; i >= 0; i--) {
        //      if (!range.contains(list.get(i))) {
        //          myTable.removeRow(i);
        //      }
        //  }
        return myTable.columnValues(attribute);
    }
    
   // public StockDataSet range()

   // @Override
   // public StockDataSet range (String attribute){
            //, Range range) {
      //  myTable.sortbyColumn(attribute);
      //  List<Double> list = myTable.columnValues(attribute);
      //  for (int i = list.size() - 1; i >= 0; i--) {
      //      if (!range.contains(list.get(i))) {
      //          myTable.removeRow(i);
     //       }
    //    }
   //     return this;
   // }

    @Override
    public StockDataSet sort (String attribute) {
        myTable.sortbyColumn(attribute);
        return this;
    }

    @Override
    public List<String> attributes () {
        return myTable.columnNames();
    }

    @Override
    public IDataSet<Double> range (String attribute) {
        // TODO Auto-generated method stub
        return null;
    }

}
