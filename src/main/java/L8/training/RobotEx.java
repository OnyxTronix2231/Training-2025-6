package L8.training;

public class RobotEx implements Breakable {
    protected String name2;
    protected int cost;
    protected int speed;
    protected boolean isFixed;

    public RobotEx(String name2, int cost, int speed, boolean isFixed) {
        this.name2 = name2;
        this.cost = cost;
        this.speed = speed;
        this.isFixed = isFixed;
    }

    @Override
    public int break1(double speed) {
        return 67;
    }

    @Override
    public boolean fix() {
        return true;
    }

}
