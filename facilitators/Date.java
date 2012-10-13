package facilitators;

/**
 * A basic utility class for storing dates in a readable
 * and manipulatable format.
 *
 */
public class Date implements Comparable<Date> {
    private static String[] ourMonths = {"Jan", "Feb", "Mar",
        "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" };
    private int myYear;
    private int myMonth;
    private int myDay;

    /**
     * Initializes a date if the info is in number format.
     * @param year of the date
     * @param month of the date
     * @param day of the date
     */
    public Date (int year, int month, int day) {
        myYear = year;
        myMonth = month;
        myDay = day;
    }

    /**
     * Initializes a date if the input is in string format.
     *
     * @param year of the date
     * @param month of the date
     * @param day of the date
     */
    public Date (String year, String month, String day) {
        myYear = Integer.parseInt(year);
        try {
            myMonth = Integer.parseInt(month);
        }
        catch (NumberFormatException e) {
            myMonth = convertMonthToInt(month);
        }
        myDay = Integer.parseInt(day);
    }

    /**
     * Prints the short format of this date.
     */
    public String toString () {
        // for the short format, we take the last two digits
        // of the year using a mod operator
        int formattedYear = myYear % 100;
        return myMonth + "-" + myDay + "-" + formattedYear;
    }

    /**
     * Returns the long format of the date.
     */
    public String getLongFormat () {
        String month = ourMonths[myMonth];
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
     * Sorts date by year, then month, then day.
     *
     * @param d the date to compare against
     */
    @Override
    public int compareTo (Date d) {
        if (getYear() != d.getYear()) { return getYear() - d.getYear(); }

        if (getMonth() != d.getMonth()) { return getMonth() - d.getMonth(); }

        return getDay() - d.getDay();
    }

    private int convertMonthToInt(String month) {
        for (int i = 0; i < ourMonths.length; i++) {
            if (ourMonths[i].equals(month)) {
                return i;
            }
        }
        // something went wrong
        return -1;
    }
}
