package L8.lecture;

public class Robot2 implements Breakable {

    private String robotName;

    public void drive() {
        System.out.println("vroom");
    }

    @Override
    public int toBreak(double speed) {
        return 1000;
    }

    @Override
    public boolean fix() {
        return false;
    }
}
