package L5.training;

public class Calculator {
    public int SumNumber(int num) {
        for (int i = 0; i < num; i++) {
            num += i;
        }
        return num;
    }

    public int Multiply(int num1, int num2) {
        int num3 = 0;
        for (int i = 0; i < num2; i++) {
            num3 += num1;
        }
        return num3;
    }
}
