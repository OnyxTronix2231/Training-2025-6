package L6.training;

public class Arrays {

    public static int sumArray(int[] intArray) {
        int Highest = 0;
        for (int i = 0; i < intArray.length - 1; i++) {
            if (intArray[i] + intArray[i + 1] > Highest) {
                Highest = intArray[i] + intArray[i + 1];
            }
        }
        return Highest;
    }

    public static void main(String[] args) {
        int[] Arrays = new int[]{3,4,5,6,7};
        System.out.println(sumArray(Arrays));

        String S = "The quick fox jumps over the lazy dog";

        String[] Words =  S.split(" ");
        for (int i = 0; i < Words.length; i++) {
            System.out.println(Words[i]);

        }
    }



}
