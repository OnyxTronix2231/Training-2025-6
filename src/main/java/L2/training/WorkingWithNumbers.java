package L2.training;

class WorkingWithNumbers {

    static void add(int num1, int num2) {
        System.out.println(num1 + num2);
    }

    static void divide(double num1, double num2) {
        System.out.println(num1 / num2);
    }

    static int sum(int num1, int num2, int num3) {
        return num1 + num2 + num3;
    }

    static double realAverage(int num1, int num2) {
        return (num1 + num2) / 2.0;
    }

    static int fakeAverage(int num1, int num2) {
        return (int)realAverage(num1, num2);
    }

    static void printState(boolean value)
    {
        System.out.println(value);
    }

    static boolean flippedState(boolean value)
    {
        return !value;
    }

    public static void main(String[] args) {
        add(1, 3);
        divide(2, 2);
        System.out.println(sum(1, 2, 3));
        System.out.println(fakeAverage(1, 2));
        System.out.println(realAverage(1, 2));

    }
}