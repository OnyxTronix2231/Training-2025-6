package L2.training;

public class a {
    public static void main(String[] args) {
        int birthYear = 2010;
        int age = 2025 - birthYear;
        System.out.println(age);
        int daysAlive = age * 365;
        System.out.println(daysAlive);
        int timesFly = daysAlive / 17;
        System.out.println(timesFly);
        int daysLeftFlyFinish = 17 - daysAlive % timesFly;
        System.out.println(daysLeftFlyFinish);
        System.out.println("We extraordinarily feel sorry for the " + timesFly + " flies that died during my lifespan of " + age + " years.");
    }
}
