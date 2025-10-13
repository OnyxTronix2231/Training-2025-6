package L2.lecture;

public class MeAndAFly {
    public static void main(String[] args) {
        int birth = 2004; // ha ha yuval is old - get over it!
        int age = 2025 - birth;
        System.out.println("The age is " + age);

        int daysInLife = age * 365;
        System.out.println("The days in life is " + daysInLife);

        int avgFly = 17;
        int ageFactor = daysInLife / avgFly;
        System.out.println("Im older than a fly by a factor of" + ageFactor);

        double daysLeft = daysInLife % avgFly;
        System.out.println("The days left is " + daysLeft);

        System.out.println("Dear" + ageFactor + "flies, I'm sorry you died.");
    }
}
