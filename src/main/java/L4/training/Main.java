package L4.training;

public class Main {
    public static void main(String[] args) {
        Date date = new Date(Date.Month.January, 1, 2025);
        System.out.println(date);
        date.increaseByOneDay();
        System.out.println(date);
        date.setDay(31);
        System.out.println(date);
        date.increaseByOneDay();
        System.out.println(date);
    }
}
