package L4.training;

public class TeamMember {

    public enum Size {
        S,
        M,
        L,
        XL
    }
    private static int count = 0;
    public static int shirtCount = 0;

    private String name;
    private Size size;
    private int grade;
    private boolean buysShirt;
    private boolean alreadyBought;

    public TeamMember(String name, int grade, Size size) {
        this.name = name;
        this.grade = grade;
        this.buysShirt = false;
        this.alreadyBought = false;

        this.size = size;

        count ++;
    }

    public static int getCount() {
        return count;
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

    public boolean isBuysShirt() {
        return buysShirt;
    }

    public void BuyShirt() {
        if (!alreadyBought) {
            shirtCount++;
            alreadyBought = true;
        }
        this.buysShirt = true;
    }

    public static final int MIN_SHIRT_COUNT = 2;

    public static boolean canBuyShirts() {
        return shirtCount >= MIN_SHIRT_COUNT;
    }

    public static void main(String[] args) {
        TeamMember teamMember = new TeamMember("Jack", 1, Size.XL);

        System.out.println(TeamMember.shirtCount);
        teamMember.BuyShirt();
        teamMember.BuyShirt();
        teamMember.BuyShirt();

        System.out.println(TeamMember.shirtCount);

        TeamMember teamMember1 = new TeamMember("Jack", 1, Size.M);
        teamMember1.BuyShirt();
        teamMember1.BuyShirt();

        System.out.println(TeamMember.shirtCount);
        System.out.println(TeamMember.canBuyShirts());


    }
}
