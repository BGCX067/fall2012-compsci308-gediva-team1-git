package databases;

public class StockTable extends DataTable{
    
    public void newRow(String s){
        RowElement row=new RowElement();
        myDataRows.add(row);
        String[] sarray=s.split(",");
        for(String rdata:sarray){
            row.addData(rdata);
        }
    }

}
