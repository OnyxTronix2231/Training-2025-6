package L9.lecture;

public class ConversionFunctions {
    public static final double DEG_IN_ROTATION = 360;
    public static final double ENC_IN_ROTATION = 2048;

    public static double euToDegrees(int encoderUnits) {
        return DEG_IN_ROTATION * encoderUnits / ENC_IN_ROTATION;
    }

    public static int degreesToEu(double degrees) {
        return (int) (ENC_IN_ROTATION * degrees / DEG_IN_ROTATION);
    }
}
