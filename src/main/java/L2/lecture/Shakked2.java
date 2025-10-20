package L2.lecture;

public class Shakked2 {
    public static void main(String[] args) {

      int birth = 2010;

      int today = 2025- birth;
        System.out.println("myAge"+ today);

      int myBirth = today * 365;
        System.out.println("mybirth:" + myBirth);

      int zvov = 17;
      int zvovMe = myBirth/zvov;
        System.out.println(zvovMe);

      int life = 365%zvov;
        System.out.println(life);

      int myLife = myBirth/zvov;
        System.out.println(myLife);






    }
}
