package L4.training;

public class Date {
    public enum Month {
        Januaty,
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
