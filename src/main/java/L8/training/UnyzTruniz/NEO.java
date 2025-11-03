package L8.training.UnyzTruniz;

public class NEO implements Motor{

    @Override
    public double setSpeed(double precent) {
        return precent*10000;
    }

    @Override
    public String setTarget(double deg) {
        return String.valueOf(deg / 360 * 2048);
    }
}
