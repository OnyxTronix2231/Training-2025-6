package L8.training.UnyzTruniz;

public class NEO implements Motor {
    public static final int MAX_ANGLE = 2048;
    public static final int MAX_SPEED = 10_000;

    @Override
    public double setSpeed(double speed) {
        return Math.min(MAX_SPEED, (speed * MAX_SPEED));
    }

    @Override
    public String setTarget(int angle) {
        return "My rotation is: " + ((angle / 360) * MAX_ANGLE);
    }
}
