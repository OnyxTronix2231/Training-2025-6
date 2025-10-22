package L4.training;

import edu.wpi.first.math.MathUtil;

public class Date {
    private Month month;
    private int day;
    private int year;

    public Date(Month month, int day, int year) {
        this.month = month;
        this.year = year;
        this.setDay(day);
    }

    public static Date getGreatest(Date a, Date b) {
        if (a.year > b.year) {
            return a;
        } else if (a.year < b.year) {
            return b;
        } else {
            if (getMonthNumByMonth(a.month) > getMonthNumByMonth(b.month)) {
                return a;
            } else if (getMonthNumByMonth(a.month) < getMonthNumByMonth(b.month)) {
                return b;
            } else {
                if (a.day > b.day) {
                    return a;
                } else {
                    return b;
                }
            }
        }
    }

    public static int getMonthNumByMonth(Month month) {
        switch (month) {
            case January:
                return 1;
            case February:
                return 2;
            case March:
                return 3;
            case April:
                return 4;
            case May:
                return 5;
            case June:
                return 6;
            case July:
                return 7;
            case August:
                return 8;
            case September:
                return 9;
            case October:
                return 10;
            case November:
                return 11;
            case December:
                return 12;
            default:
                return 0;
        }
    }

    public static Month getMonthByNum(int monthNum) {
        switch (monthNum) {
            case 1:
                return Month.January;
            case 2:
                return Month.February;
            case 3:
                return Month.March;
            case 4:
                return Month.April;
            case 5:
                return Month.May;
            case 6:
                return Month.June;
            case 7:
                return Month.July;
            case 8:
                return Month.August;
            case 9:
                return Month.September;
            case 10:
                return Month.October;
            case 11:
                return Month.November;
            case 12:
                return Month.December;
            default:
                return null;
        }
    }

    public void setToYear(int year) {
        this.year = year;
        this.day = 1;
        this.month = Month.January;
    }

    public void sayDay() {
        System.out.println(day + "/" + month + "/" + year);
    }

    public void increaseByOneDay() {
        if (day + 1 > monthLength(month)) {
            day = 1;
            if (month == Month.December) {
                month = Month.January;
                year++;
            } else
                month = getMonthByNum(getMonthNumByMonth(month) + 1);
        } else {
            day++;
        }
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = MathUtil.clamp(day, 1, monthLength(month));
    }

    private int monthLength(Month month) {

        switch (month) {
            case February:
                return 28;
            case January, March, May, December, October, August, July:
                return 31;
            case April, September, June:
                return 30;
            default:
                return 0;

        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Date\n" + "  Day: " + day + "\n  Month: " + month + "\n  Year: " + year;
    }

    public enum Month {
        January,
        February,
        March,
        April,
        May,
        June,
        July,
        August,
        September,
        October,
        November,
        December
    }
}
