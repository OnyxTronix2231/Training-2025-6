package L7.training.SpeedController;

import edu.wpi.first.math.MathUtil;

public class SpeedController {
    protected String mechanismName;
    protected double outputSpeed;

    public SpeedController(String mechanismName, double outputSpeed) {
        this.mechanismName = mechanismName;
        this.outputSpeed = MathUtil.clamp(outputSpeed, -1, 1);
    }

    public String getMechanismName() {
        return mechanismName;
    }

    public void setMechanismName(String mechanismName) {
        this.mechanismName = mechanismName;
    }

    public double getOutputSpeed() {
        return outputSpeed;
    }

    public void setOutputSpeed(double outputSpeed) {
        this.outputSpeed = MathUtil.clamp(outputSpeed, -1, 1);
    }

    @Override
    public String toString() {
        return "SpeedController" +
                "  mechanismName: " + mechanismName +
                "  outputSpeed=" + outputSpeed;
    }
}
