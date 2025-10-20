package L3.lecture;

public class StringCompare {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abc";

        System.out.println(s1 + s2); // abcabc

        boolean b = s1.equals(s2);
        System.out.println(b);

        System.out.println(s1.length());
    }
}
