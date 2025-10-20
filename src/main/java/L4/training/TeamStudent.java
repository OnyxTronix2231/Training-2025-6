package L4.training;

public class TeamStudent {
    private static int studentCount = 0;
    private static int shirtsToBuy = 0;

    private String name;
    private boolean hasShirt;
    private shirtSize shirtSize;

    public TeamStudent(String name) {
        this.name = name;

        studentCount++;
    }

    public static boolean canBuyShirts() {
        return shirtsToBuy >= 5;
    }

    public static int getStudentCount() {
        return studentCount;
    }

    public static void setStudentCount(int studentCount) {
        TeamStudent.studentCount = studentCount;
    }

    public static int getShirtsToBuy() {
        return shirtsToBuy;
    }

    public static void setShirtsToBuy(int shirtsToBuy) {
        TeamStudent.shirtsToBuy = shirtsToBuy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void buyShirt() {
        if (!hasShirt) {
            shirtsToBuy++;
            hasShirt = true;
        }
    }

    public shirtSize getShirtSize() {
        return shirtSize;
    }

    public void setShirtSize(shirtSize shirtSize) {
        this.shirtSize = shirtSize;
    }

    public enum shirtSize {
        S, M, L, XL
    }
}
