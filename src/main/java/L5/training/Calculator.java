package L5.training;

public class Calculator {
    public static int addRecursively(int num) {
        int total = 0;
        for (int i = 1; i <= num; i++) {
            total += i;
        }
        return total;
    }

    public static int multiply(int n1, int n2) {
        int total = 0;
        for (int i = 0; i < n1; i++) {
            total += n2;
        }
        return total;
    }

    public static int power(int n1, int n2) {
        int total = n1;
        for (int i = 1; i < n2; i++) {
            total = multiply(total, n1);
        }
        return total;
    }

    public static boolean isPrime(int num) {
        if (num % 2 == 0 && num != 2) {
            return false;
        }

        for (int i = 3; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static int addDigits(int number) {
        int total = 0;
        int num = number;
        while (num > 0) {
            total += num % 10;
            num /= 10;
        }
        return total;
    }
}
