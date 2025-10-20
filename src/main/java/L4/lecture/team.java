package L4.lecture;

public class team {
    public static int  amountOfNewMembers = 0;
    private static int shirtCount = 0;
    private String name;
    private int grade;
    private boolean buyShirt;
    public team(String name, int grade) {
        this.name = name;
        this.grade = grade;
        this.buyShirt = false;

        amountOfNewMembers++;
    }

    public static int getAmountOfNewMembers() {
        return amountOfNewMembers;
    }

    public static void setAmountOfNewMembers(int amountOfNewMembers) {
        team.amountOfNewMembers = amountOfNewMembers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public boolean isBuyShirt() {
        return buyShirt;
    }

    public void setBuyShirt(boolean buyShirt) {
        this.buyShirt = buyShirt;
    }
}
