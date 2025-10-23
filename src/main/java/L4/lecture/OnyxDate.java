package L4.lecture;

import edu.wpi.first.math.MathUtil;

public class OnyxDate {
    private enum Months {
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

    private int Day;
    private Months Month;
    private int Year;

    public OnyxDate(Months month, int day, int year) {
        this.Day = ClampDay(day, month);
        this.Month = month;
        Year = year;
    }

    public int getDay() {
        return Day;
    }

    public void setDay(int day) {
        Day = day;
    }

    public Months getMonth() {
        return Month;
    }

    public void setMonth(Months month) {
        Month = month;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public static int ClampDay(int day, Months months) {
        switch (months) {
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
                return MathUtil.clamp(day, 1, 31);
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

    public void sayDay() {
        System.out.println("day: " + Day);
        System.out.println("month: " + Month);
        System.out.println("year: " + Year);
    }

    public void setToYear(int year) {
        setDay(1);
        setMonth(Months.January);
        setYear(year);
    }

    public static int getMonthNum(Months months) {
        switch (months) {
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
        }
        return 1;
    }

    public static OnyxDate getGreatest(OnyxDate d1, OnyxDate d2) {
        if (d1.Year > d2.Year) {
            return d1;
        } else if (d2.Year > d1.Year) {
            return d2;
        } else if (d2.Year == d1.Year) {
            if (getMonthNum(d1.getMonth()) > getMonthNum(d2.getMonth())) {
                return d1;
            } else if (getMonthNum(d1.getMonth()) > getMonthNum(d2.getMonth())) {
                return d2;
            } else if (d2.Year == d1.Year && d2.Month == d1.Month) {
                if (d1.getDay() > d2.getYear()) {
                    return d1;
                } else {
                    return d2;
                }
            }
        }
        return d1;
    }


    public static void main(String[] args) {
        OnyxDate d1 = new OnyxDate(Months.December, 4, 2008);
    }
}