package L2.lecture;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getDistanceSquared(){
        return x*x + y*y;
    }

    public double getDistance() {
        return Math.sqrt(x*x + y*y);
    }
}
