package L6.training;

public class Ex1 {

    public static void maxNear(int []arr){
        int max = arr[0]+arr[1];
        int []imax = new int[2];
        imax[0] = 0;
        imax[1] = 1;
        for (int i = 2; i<arr.length-1;i++){
            if (max < arr[i]+arr[i+1]){
                max = arr[i]+arr[i+1];
                imax[0] = i;
                imax[1] = i+1;
            }
        }
        System.out.println(max);
        for (int i = 0; i < imax.length; i++) {
            System.out.println(imax[i]);
        }
    }
    public static void main(String[] args) {
        String phrase = "The quick brown fox jumps over the lazy dog";
        String []words = phrase.split(" ");
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }
        String togetherAgain = " ";
        for (int i =0;i<words.length;i++){
            togetherAgain+= " " + words[i];
        }
        System.out.println(togetherAgain);
        int []nums = {4,5,3,6,4};
        int n =nums.length;
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {

                    // Swap arr[j] and arr[j+1]
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
        for (int k = 0; k < nums.length; k++) {
            System.out.println(nums[k]);
        }
    }
}
