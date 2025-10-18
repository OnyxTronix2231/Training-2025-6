package L2.training;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point move_point(double move_x , double move_y){
        return new Point(getX()+move_x,getY()+move_y);
    }
    public double d_betweenTwoPointsSquared(){
        return x*x + y*y;
    }
    public double d_betweenTwoPointsSqrt(){
        return Math.sqrt(d_betweenTwoPointsSquared());
    }


}
