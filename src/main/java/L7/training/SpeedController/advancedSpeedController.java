package L7.training.SpeedController;

public class advancedSpeedController extends SpeedController {
    protected String wheelScope;
    protected double maxSpeed;

    public advancedSpeedController(String mechanismName, double outputSpeed, String wheelScope, double maxSpeed) {
        super(mechanismName, outputSpeed);
        this.wheelScope = wheelScope;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void setOutputSpeed(double outputSpeed) {
        super.setOutputSpeed(outputSpeed / 2);
    }

    public double getSpeedInMS() {
        return outputSpeed / 60;
    }

    @Override
    public String toString() {
        return super.toString() + "\n  wheelScope: " + wheelScope + "\n  maxSpeed: " + maxSpeed;
    }

}
