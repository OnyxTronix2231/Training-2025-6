package L2.training;

public class Trinom {
    double a;
    double b;
    double c;

    public Trinom(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void moveTrinom(int by) {
        c += by;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public void printTrinom() {
        System.out.println(a + "x^2+" + b + "x+" + c);
    }

    public double solve() {
        double result = (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
        return result;
    }
}
