package L6.training;

public class Main {
    public static int sumItems(int[] ints) {
        int max = 0;
        for (int i = 0; i < ints.length - 1; i++) {
            if (ints[i] + ints[i + 1] > max) {
                max = ints[i] + ints[i + 1];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String s1 = "The quick brown fox jumps over the lazy dog";
        String[] s2 = s1.split(" ");
        for (String s : s2) {
            System.out.println(s + " ");
        }
        StringBuilder s3 = new StringBuilder();
        for (String s : s2) {
            s3.append(s).append(" ");
        }
        System.out.println(s3);

        int[] ints = new int[]{1, 7, 64, 28, 3, 4};

        for (int j = 0; j < ints.length; j++) {
            for (int i = 0; i < ints.length - 1; i++) {
                if (ints[i] > ints[i + 1]) {
                    int temp = ints[i];
                    ints[i] = ints[i + 1];
                    ints[i + 1] = temp;
                }
            }
        }

        for (int i = 0; i < ints.length; i++) {
            System.out.println(i + ": " + ints[i]);
        }

        System.out.println(sumItems(new int[]{-3, 11, -1, 5}));
    }
}