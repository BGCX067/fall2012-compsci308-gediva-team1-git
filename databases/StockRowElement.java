package databases;

import java.text.ParseException;
import java.util.ArrayList;


public class StockRowElement extends RowElement<Double> {
    ArrayList<Double> myData;

    public StockRowElement () {
        myData = (ArrayList<Double>) new ArrayList<Double>();
    }

    @Override
    public void addData (String rdata) {
        try {
            myData.add(new Double(rdata));
        }
        catch (RuntimeException e) {
            String[] data = rdata.split("-");
            String result = ((data[0].length() == 1) ? "0" : "") + data[0] + data[1] + "20" + data[2];
            try {
                myData.add(new Double(new java.text.SimpleDateFormat ("ddMMyyyy").parse(result).getTime()/1000));
            }
            catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void addData (Double rdata) {
        myData.add(rdata);
    }

    @Override
    public Double getPrimaryValue () {
        return (Double) myData.get(myPrimaryIndex);
    }

    @Override
    public int compareTo (RowElement<Double> r) {
        return getPrimaryValue().compareTo(((StockRowElement) r).getPrimaryValue());
    }

}
