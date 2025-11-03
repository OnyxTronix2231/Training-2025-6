package L8.training;

public class Pot implements Breakable {
    protected double size;
    protected int cost;
    protected boolean isFixed;
    protected double speedXd;

    public Pot(double size, int cost, boolean isFixed, double speedXd) {
        this.size = size;
        this.cost = cost;
        this.isFixed = isFixed;
        this.speedXd = speedXd;
    }

    @Override
    public int break1(double speed) {
        return (int)speed*67;
    }

    @Override
    public boolean fix() {
        return true;
    }
}
