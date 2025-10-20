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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = clampDay(day, this.month);
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static void main(String[] args) {
        Date d = new Date(50, Month.February, 2026);
        System.out.println(d.getDay());
    }
}
