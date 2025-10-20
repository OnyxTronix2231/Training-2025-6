package L3;

public class Point {

    private double x;
    private double y;

    public Point (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getDistanceSquared() {
        return x*x + y*y;
    }

}
