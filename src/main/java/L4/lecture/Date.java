package L4.lecture;

import edu.wpi.first.math.MathUtil;

public class Date {
    private enum  Months {
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
    private  int Month;
    private int  Year;

    public Date(Months months, int day, int month, int year) {
        this.Day = ClamDay(day,months );
        Month = month;
        Year = year;
    }

    public int getDay() {
        return Day;
    }

    public void setDay(int day) {
        Day = day;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }
    public static int ClamDay(int day,Months months){
        switch (months) {
            case January :
                return MathUtil.clamp(day,1,31);
            case February:
                return MathUtil.clamp(day,1,28);
            case March:
                return MathUtil.clamp(day,1,31);
            case April:
                return MathUtil.clamp(day,1,30);
            case May:
                return MathUtil.clamp(day,1,31);
            case June:
                return MathUtil.clamp(day,1,31);
            case July:
                return MathUtil.clamp(day,1,31);
            case August:
                return MathUtil.clamp(day,1,31);
            case September:
                return MathUtil.clamp(day,1,30);
            case October:
                return MathUtil.clamp(day,1,31);
            case November:
                return MathUtil.clamp(day,1,30);
            case December:
                return MathUtil.clamp(day,1,31);
            default:
                return 31;
        }
    }
}
