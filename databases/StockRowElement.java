package databases;

import facilitators.Date;
import java.util.ArrayList;

/**
 * This class encompasses the information needed to
 * store and modify elements of a row of stock data.
 */
public class StockRowElement extends RowElement<Comparable> {

    /**
     * Initializes an element of a row of stock data.
     */
    public StockRowElement () {
        setMyData(new ArrayList<Comparable>());
    }

    @Override
    public void addData (String rdata) {
        try {
            // form1 regex is dates of form yyyy-mm-dd, e.g. 1992-09-29
            String dateForm1 = "((?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3}))[-:\\/.](?:[0]?[1-9]|[1][012])[-:\\/.](?:(?:[0-2]?\\d{1})|(?:[3][01]{1})))(?![\\d])";

            // form2 regex is dates of form dd-month-yy, e.g. 29-Sep-92
            String dateForm2 = "((?:(?:[0-2]?\\d{1})|(?:[3][01]{1}))[-:\\/.](?:Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|Apr(?:il)?|May|Jun(?:e)?|Jul(?:y)?|Aug(?:ust)?|Sep(?:tember)?|Sept|Oct(?:ober)?|Nov(?:ember)?|Dec(?:ember)?)[-:\\/.](?:(?:\\d{1}\\d{1})))(?![\\d])";

            if (rdata.matches(dateForm1) || rdata.matches(dateForm2)) {
                try {
                    String[] d = rdata.split("-");
                    Date date = new Date(d[0], d[1], d[2]);
                    super.getMyData().add(date);
                    addData(date);
                }
                catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            else {
            		addData(new Double(rdata));
            }
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Comparable getPrimaryValue () {
        return super.getMyData().get(getMyPrimaryIndex());
    }

    @Override
    public int compareTo (RowElement<Comparable> r) {
        return getPrimaryValue().compareTo(
                ((StockRowElement) r).getPrimaryValue());
    }

    /**
     * Adds another element to this row of stock data.
     *
     * @param rdata the data to add to this row
     */
    public void addData (Comparable rdata) {
        myData.add(rdata);
    }
}
