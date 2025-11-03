package L6.training;

public class Array {
    public static void main(String[] args) {
        int[] Array = new int[10];
        Array[0] = 10;
        Array[1] = Array.length;
        Array[Array.length - 1] = Array[0] + Array[1];

        if (Array[1] > Array[2]) {
            Array[2] = Array[1] + 1;
        } else
            Array[2] -= 5;

        while (Array[2] > Array[3]) {
            Array[3]++;
        }

        int arrayTotal = 0;
        for (int i = 0; i < Array.length; i++) {
            System.out.println(Array[i]);
            arrayTotal += Array[i];
        }

        System.out.println("The total value of the array is: " + arrayTotal);
    }
}
