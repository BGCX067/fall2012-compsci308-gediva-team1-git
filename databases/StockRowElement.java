package databases;

import java.text.ParseException;
import java.util.ArrayList;
import facilitators.Date;


public class StockRowElement extends RowElement<Comparable> {
   // ArrayList<Double> myData;

    public StockRowElement () {
        super.myData = (ArrayList<Comparable>) new ArrayList<Comparable>();
    }

    @Override
    public void addData (String rdata) {
        try {
            String date_regex = "((?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3}))[-:\\/.](?:[0]?[1-9]|[1][012])[-:\\/.](?:(?:[0-2]?\\d{1})|(?:[3][01]{1})))(?![\\d])";
            if (rdata.matches(date_regex)) {
                try {
                    String[] d = rdata.split("-");
                    Date date = new Date(d[0], d[1], d[2]);
                    super.myData.add(date);
                }
                catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else {
                super.myData.add(new Double(rdata));
            }
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Comparable getPrimaryValue () {
        return super.myData.get(myPrimaryIndex);
    }

    @Override
    public int compareTo (RowElement<Comparable> r) {
        return getPrimaryValue().compareTo(((StockRowElement) r).getPrimaryValue());
    }

    @Override
    public void addData (Comparable rdata) {
        // TODO Auto-generated method stub
        
    }

}
