package L2.training;

public class Circle {
    private double radius;
    private Point center;

    public Circle(double radius, Point center) {
        this.radius = radius;
        this.center = center;
    }
    public void moveCircle(Point newCenter){
        this.center = newCenter;
    }
    public void BiggerRadius(double addToRadius){
        this.radius +=addToRadius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double Circle_P(){
        return 2*Math.PI*this.radius;
    }

    public double Circle_S(){
        return Math.PI*(radius * radius);
    }
}
