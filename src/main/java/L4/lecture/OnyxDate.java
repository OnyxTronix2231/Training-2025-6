package L4.lecture;

import edu.wpi.first.math.MathUtil;

public class OnyxDate {
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

    private int day;
    private Month month;
    private int year;

    public OnyxDate(int day, Month month, int year) {
        this.day = clampDay(day, month);
        this.month = month;
        this.year = year;
    }

    public static int howManyDays(Month month) {
        switch (month) {
            case January, March, May, July, August, October, December:
                return 31;
            case April, June, September, November:
                return 30;
            case February:
                return 28;
            default:
                return 0;
        }
    }

    public static int clampDay(int day, Month month) {
        return MathUtil.clamp(day, 0, howManyDays(month));
    }

    public static int getMonthNum(Month month) {
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

    public static Month getNumMonth(int num) {
        switch (num) {
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
                return Month.January;
        }
    }

    public void sayDate(){
        System.out.println("Today is:\n\t" + day + " in " + month + " " + year);
    }

    public void setToYear(int year) {
        this.year = year;
        this.month = Month.January;
        this.day = 1;
    }

    public static OnyxDate getGreatest(OnyxDate d1, OnyxDate d2) {
        // if one year is bigger - that is the later date
        if (d1.getYear() > d2.getYear()) {
            return d1;
        } else if (d1.getYear() < d2.getYear()) {
            return d2;
        }

        // years are same - so if one month is bigger - that is the later date
        if (getMonthNum(d1.getMonth()) > getMonthNum(d2.getMonth())) {
            return d1;
        } else if (getMonthNum(d1.getMonth()) < getMonthNum(d2.getMonth())) {
            return d2;
        }

        // both year, and month, are same - so if one day is bigger - that is the later date
        if (d1.getDay() > d2.getDay()) {
            return d1;
        } else if (d1.getDay() < d2.getDay()) {
            return d2;
        }

        // all the same! it doesn't matter - they are the same date!
        return d1;
    }

    public static OnyxDate getDistance(OnyxDate d1, OnyxDate d2) {
        int dDays = d1.getDay() - d2.getDay();
        if (d1.getDay() < d2.getDay()) {
            dDays += clampDay(50, d1.month);

            if (d1.getMonth() == Month.January) {
                d1.setMonth(Month.December);
                d1.setYear(d1.getYear() - 1);
            } else {
                d1.setMonth(getNumMonth(getMonthNum(d1.getMonth()) - 1));
            }
        }

        int dMonths = getMonthNum(d1.getMonth()) - getMonthNum(d2.getMonth());
        if (dMonths < 0) {
            dMonths += 12;
            d1.setYear(d1.getYear() - 1);
        }

        int dYears = d1.getYear() - d2.getYear();

        return new OnyxDate(dDays, getNumMonth(dMonths), dYears);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
        this.day = clampDay(day, month);
    }

    @Override
    public String toString() {
        return "OnyxDate{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    public static void main(String[] args) {
        OnyxDate d = new OnyxDate(50, Month.February, 2026);
        System.out.println(d.getDay());
        d.setDay(-15);
        System.out.println(d.getDay());
        System.out.println(d.getMonth());
        d.sayDate();
    }
}
