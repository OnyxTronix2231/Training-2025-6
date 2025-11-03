package L8.training.UnyzTruniz;

public class Talon implements Motor{
    @Override
    public double setSpeed(double precent) {
        return precent*4000;
    }

    @Override
    public String setTarget(double deg) {
        return String.valueOf(deg*360);
    }
}
