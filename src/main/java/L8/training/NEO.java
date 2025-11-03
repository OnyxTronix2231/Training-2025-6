package L8.training;

public class NEO implements Motor {


    @Override
    public double setSpeed(double percent) {
        return percent * 10000;
    }

    @Override
    public String setTarget(double angle) {
        return "The rotation is:" + ((angle / 360) * 2048);
    }
}
