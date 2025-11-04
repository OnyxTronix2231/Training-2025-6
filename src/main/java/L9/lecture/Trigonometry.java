package L9.lecture;

public class Trigonometry {

    public static final double ROBOT_HEIGHT_MM = 655;
    public static final double TARGET_HEIGHT_MM = 2050;

    public static double getDistFromTarget(double angle) {
        double angleInRad = Math.toRadians(angle);
        double tan = Math.tan(angleInRad);
        return (TARGET_HEIGHT_MM - ROBOT_HEIGHT_MM) / tan;
    }
}
