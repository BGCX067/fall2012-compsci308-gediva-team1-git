package databases;

import facilitators.Date;
import java.util.ArrayList;


/**
 * This class encompasses the information needed to
 * store and modify elements of a row of stock data.
 * 
 * @author Lance Co Ting Keh and Mark Govea
 */
public class StockRowElement extends RowElement<Comparable> {
    private static final String[] DATE_FORMATS = {
             // form1 regex is dates of form yyyy-mm-dd, e.g. 1992-09-29
        "((?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3}))" +
            "[-:\\/.](?:[0]?[1-9]|[1][012])[-:\\/.]" +
            "(?:(?:[0-2]?\\d{1})|(?:[3][01]{1})))(?![\\d])",
              // form2 regex is dates of form dd-month-yy, e.g. 29-Sep-92
        "((?:(?:[0-2]?\\d{1})|(?:[3][01]{1}))[-:\\/.]" +
            "(?:Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|" +
            "Apr(?:il)?|May|Jun(?:e)?|Jul(?:y)?|Aug(?:ust)?|" +
            "Sep(?:tember)?|Sept|Oct(?:ober)?|Nov(?:ember)?|" +
            "Dec(?:ember)?)[-:\\/.](?:(?:\\d{1}\\d{1})))(?![\\d])"
    };

    /**
     * Initializes an element of a row of stock data.
     */
    public StockRowElement () {
        setData(new ArrayList<Comparable>());
    }

    @Override
    public void addData (String rdata) {
        if (rdata.matches(DATE_FORMATS[0]) || rdata.matches(DATE_FORMATS[1])) {
            String[] d = rdata.split("-");
            Date date = null;
            if (rdata.matches(DATE_FORMATS[0])) {
                // d = {year, month, day}
                date = new Date(d[0], d[1], d[2]);
            }
            else {
                // d = {day, month, year}
                date = new Date(d[2], d[1], d[0]);
            }
            super.getData().add(date);
            addData(date);
        }

        else {
            try {
                addData(new Double(rdata));
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
                throw new IllegalArgumentException();
            }
        }

    }

    @Override
    public Comparable getPrimaryValue () {
        return super.getData().get(getMyPrimaryIndex());
    }

    @Override
    public int compareTo (RowElement<Comparable> r) {
        return getPrimaryValue().compareTo(((StockRowElement) r).getPrimaryValue());
    }

    /**
     * Adds another element to this row of stock data.
     * 
     * @param rdata the data to add to this row
     */
    public void addData (Comparable rdata) {
        getData().add(rdata);
    }
}
