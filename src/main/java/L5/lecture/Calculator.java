package L5.lecture;

public class Calculator {
    public static int factorial(int number) {
        int result = 1;
        int num = number;
        while (num != 0) {
            result *= num;
            num --;
        }
        return result;
    }
    public static int forFactorial(int number){
        int result = 1;
        for (int i = number ;i>0;i--){
            result *=i;
        }
        return result;
    }

    public static double test() {
        int x = 0;
        for (int i = 0; i < 5; i++) {
            x = x + 1;
        }
        return x;
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}
