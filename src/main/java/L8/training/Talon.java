package L8.training;

public class Talon implements Motor{


    @Override
    public double setSpeed(double percent) {
        return percent * 4000;
    }

    @Override
    public String setTarget(double angle) {
        return "The location is:" + (angle / 360);
    }
}
