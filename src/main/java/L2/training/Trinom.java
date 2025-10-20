package L2.training;

public class Trinom {
    private double first;
    private double second;
    private double third;

    public Trinom(double first, double second, double third)
    {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public void addConstant(double constant)
    {
        this.first += constant;
        this.second += constant;
        this.third += constant;
    }

    public void printTrinom()
    {
        System.out.println(this.first + "x^2+" + this.second + "x+" + this.third);
    }

    public static void main(String[] args)
    {
        Trinom trinom = new Trinom(1,2,3);
        trinom.printTrinom();
    }
}
