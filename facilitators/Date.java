package facilitators;

/**
 * A basic utility class for storing dates in a readable
 * and manipulatable format
 * 
 */

public class Date implements Comparable<Date> {
    private int myYear;
    private int myMonth;
    private int myDay;

    public Date (int year, int month, int day) {
        myYear = year;
        myMonth = month;
        myDay = day;
    }

    public Date (String year, String month, String day) {
        myYear = Integer.parseInt(year);
        myMonth = Integer.parseInt(month);
        myDay = Integer.parseInt(day);
    }

    public String toString () {
        // for the short format, we take the last two digits
        // of the year using a mod operator
        int formattedYear = myYear % 100;
        return myMonth + "-" + myDay + "-" + formattedYear;
    }

    public String getLongFormat () {
        String months[] =
                { "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov",
                 "Dec" };
        String month = months[myMonth];

        return month + " " + myDay + ", " + myYear;
    }

    /**
     * @return the myYear
     */
    public int getYear () {
        return myYear;
    }

    /**
     * @param year the year to set
     */
    public void setYear (int year) {
        this.myYear = year;
    }

    /**
     * @return the myMonth
     */
    public int getMonth () {
        return myMonth;
    }

    /**
     * @param month the month to set
     */
    public void setMonth (int month) {
        this.myMonth = month;
    }

    /**
     * @return the myDay
     */
    public int getDay () {
        return myDay;
    }

    /**
     * @param day the day to set
     */
    public void setDay (int day) {
        this.myDay = day;
    }

    /**
     * Sorts date by year, then month, then day
     * 
     * @param d the date to compare against
     */
    @Override
    public int compareTo (Date d) {
        if (getYear() != d.getYear()) { return getYear() - d.getYear(); }

        if (getMonth() != d.getMonth()) { return getMonth() - d.getMonth(); }

        return getDay() - d.getDay();
    }
}
