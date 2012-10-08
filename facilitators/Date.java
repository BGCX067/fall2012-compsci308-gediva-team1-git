package facilitators;

/*
 * A basic utility class for storing dates in a readable
 * and manipulatable format
 * 
 */

public class Date {
    private int myYear;
    private int myMonth;
    private int myDay;
    
    public Date(int year, int month, int day) {
        myYear = year;
        myMonth = month;
        myDay = day;
    }
    
    public String toString() {
        return myMonth + "-" + myDay + "-" + myYear;
    }
    
    public String getLongFormat() {
        String months[] = {"January", "Febuary", "March", "April", "May",
                "June", "July", "August", "September", "October",
                "November", "December"};
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
    
    
    
}
