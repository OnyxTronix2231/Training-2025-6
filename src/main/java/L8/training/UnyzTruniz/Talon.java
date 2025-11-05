package L8.training.UnyzTruniz;

public class Talon implements Motor {
    public static final int MAX_ANGLE = 1;
    public static final int MAX_SPEED = 4000;

    @Override
    public double setSpeed(double speed) {
        return Math.min(MAX_SPEED, (speed * MAX_SPEED));
    }

    @Override
    public String setTarget(int angle) {
        return "My rotation is: " + Math.min((angle / 360), MAX_ANGLE);
    }
}
