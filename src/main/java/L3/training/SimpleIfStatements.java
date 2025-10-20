package L3.training;

public class SimpleIfStatements {
    public static void main(String[] args)
    //בדיקה האם מספר זוגי
     public static boolean isEven (int num1) {
         return num1 % 2 == 0;
     }
     // בדיקה האם שנה מעוברת
     public static boolean isleapYear (int year){
         return year > 0 && year%4 ==0;
     // החזרת המספר הגדול ביותר מבין שלושה
     public static  int getGreatest (int number1, number2, number3){
         int max = number1;
         if (number2 > max) max = number2;
         if (number3 > max) max = number3;
         return max;

         }
         // הדפסת גודל מספר על פי ערך מוחלט
         public static void printMagnitude (double num){
         double absValue = Math.abs (num);
         if (absValue > 100,000,000){
                 System.out.println("Large");
                 else if (absValue >= 100,000){
                     System.out.println("Middle");
                     else{
                         System.out.println("Small");
                     }
                 }
             }

         public static void getDay (int dayNumber){
             if (dayNumber == 1){
                    System.out.println("ראשון");
                 else if (dayNumber == 2 ){
                     System.out.println("שני");
                 else if  (dayNumber == 3 ){
                      System.out.println("שלישי");
                 else if  (dayNumber == 4 ){
                       System.out.println("רביעי");
                 else if  (dayNumber == 5 ){
                       System.out.println("חמישי");
                 else if  (dayNumber == 6 ){
                         System.out.println("שישי");
                 else if  (dayNumber == 7 ){
                         System.out.println("שבת");
                 else{
                 System.out.println("יש לבחור מספר בין 1-7 ");
                 }
                                     }



                     }
                 }
             }
             }
         }

    }






}
