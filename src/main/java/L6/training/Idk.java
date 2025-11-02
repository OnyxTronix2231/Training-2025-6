package L6.training;

public class Idk {

    public static void main(String[] args) {
        int []arr = new int[10];
        arr[0] = 10;
        arr[1] = arr.length-1;
        arr[arr.length-1] = arr[0] + arr[1];
        if (arr[1] > arr[2]){
            arr[2] = arr[1]+1;
        }
        else {
            arr[2] = arr[2]-5;
        }
        while (arr[3] < arr[2]){
            arr[3]++;
        }
        int count = 0;
        for (int i=0;i< arr.length;i++){
            count+=arr[i];
        }
        System.out.println(count);
    }
}
