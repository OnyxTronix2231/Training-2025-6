package L2.training;

public class WorkingWithNumbers {
    public static void main(String[] args) {

    }

    public static void add(int a, int b) {
        System.out.println(a + b);
    }

    public static void divide(double a, double b) {
        System.out.println(a / b);
    }
                                                                        
    public static int add3(int a, int b, int c) {
        return a + b + c;
    }

    public static int fakeAverage(int a, int b) {
        return (a + b) / 2;
    }

    public static double realAverage(int a, int b) {
        return (a + b) / 2.0;
    }

    public static void printState(boolean state) {
        System.out.println(state);
    }

    public static boolean flipState(boolean state) {
        return !state;
    }
}
