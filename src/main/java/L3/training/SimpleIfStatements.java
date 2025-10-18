package L3.training;

public class SimpleIfStatements {
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0;
    }

    public static int getGreatest(int num1, int num2, int num3) {
        if (num1 > num2 && num1 > num3) {
            return num1;
        } else if (num2 > num1 && num2 > num3) {
            return num2;
        } else {
            return num3;
        }
    }

    public static void printMagnitude(int num) {
        if (num > 1_000_000) {
            System.out.println("Large");
        } else if (num > 100_000) {
            System.out.println("Medium");
        } else {
            System.out.println("Small");
        }
    }

    public static void getDay(int num) {
        if (num == 1) {
            System.out.println("Sunday");
        } else if (num == 2) {
            System.out.println("Monday");
        } else if (num == 3) {
            System.out.println("Tuesday");
        } else if (num == 4) {
            System.out.println("Wednesday");
        } else if (num == 5) {
            System.out.println("Thursday");
        } else if (num == 6) {
            System.out.println("Friday");
        } else if (num == 7) {
            System.out.println("Saturday");
        }
    }

    public static void isGreatNumbers(int num1, int num2) {
        if (num1 % 2 == 0 && num1 > num2) {
            System.out.println("Great Numbers");
        } else if (num2 % 2 != 0 && num2 > num1) {
            System.out.println("Nice Numbers");
        } else {
            System.out.println("OK Numbers");
        }
    }

    public static boolean areFunnyNumbers(int num1, int num2) {
        return num1 * num1 == num2 * num2 && ((num1 > 0 && num2 > 0) || (num1 < 0 && num2 < 0));
    }
}
