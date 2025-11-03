package L6.lecture;

public class ExercizeSolution {
    public static int highestNeighbors(int[] intArr) {
        int highest = intArr[0] + intArr[1];
        for (int i = 0; i < intArr.length - 1; i++) {
            if (intArr[i] + intArr[i + 1] > highest) {
                highest = intArr[i] + intArr[i + 1];
            }
        }
        return highest;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {-3, 11, -1, 5};
        System.out.println(highestNeighbors(arr));

        String msg = "The Quick brown fox jumps over the lazy dog";
        String[] words = msg.split(" ");
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }

    }
}
