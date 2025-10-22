package L4.lecture;

import edu.wpi.first.math.MathUtil;

public class Date {
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

    public Date(int day, Month month, int year) {
        this.day = clampDay(day, month);
        this.month = month;
        this.year = year;
    }

    public static int clampDay(int day, Month month) {
        switch (month) {
            case January:
                return MathUtil.clamp(day, 1, 31);
            case February:
                return MathUtil.clamp(day, 1, 28);
            case March:
                return MathUtil.clamp(day, 1, 31);
            case April:
                return MathUtil.clamp(day, 1, 30);
            case May:
                return MathUtil.clamp(day, 1, 31);
            case June:
                return MathUtil.clamp(day, 1, 30);
            case July:
                return MathUtil.clamp(day, 1, 31);
            case August:
                return MathUtil.clamp(day, 1, 31);
            case September:
                return MathUtil.clamp(day, 1, 30);
            case October:
                return MathUtil.clamp(day, 1, 31);
            case November:
                return MathUtil.clamp(day, 1, 30);
            case December:
                return MathUtil.clamp(day, 1, 31);
            default:
                return day;
        }
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

    public void satDate(){
        System.out.println();
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
        this.day = clampDay(day, month);;
    }

    public static void main(String[] args) {
        Date d = new Date(50, Month.February, 2026);
        System.out.println(d.getDay());
        d.setDay(-15);
        System.out.println(d.getDay());
        System.out.println(d.getMonth());
    }
}
