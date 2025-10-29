package L7.training.EX3;

public class AdvancedBakar extends Bakar {
    private int PWheelInMeters;
    private int RPM;

    public AdvancedBakar(String bakarName, int output, int PWheelInMeters, int RPM) {
        super(bakarName, output);
        this.PWheelInMeters = PWheelInMeters;
        this.RPM = RPM;
    }

    public int currentSpeed(){
        return this.PWheelInMeters * this.RPM;
    }

    @Override
    public String toString() {
        return "AdvancedBakar{" +
                "PWheelInMeters=" + PWheelInMeters +
                ", RPM=" + RPM +
                ", bakarName='" + bakarName + '\'' +
                ", output=" + output +
                '}';
    }
}
