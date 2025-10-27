package L5.training;

public class exercise5 {
    public int fromZeroToNum(int num){
        int count = 0;
        for (int i = 0; i < num; i++) {
            count +=i;
        }
        return count;
    }
    public static int numCafulNum(int num,int num2){
        int count = 0;
        for (int i=0;i<num2;i++){
            count+=num;
        }
        return count;
    }
    public static int numByPowerNum2(int num,int num2){
        int count = 1;
        for (int i=0;i<num2;i++){
            count*=num;
        }
        return count;
    }
    public static boolean isRishoni(int num){
        for (int i=1;i<=num;i++){
            if (num / i  == 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(isRishoni(4));
    }
}
