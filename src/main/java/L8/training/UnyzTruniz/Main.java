package L8.training.UnyzTruniz;


public class Main {
    public static void main(String[] args) {
        Robot robot = new Robot(new Wheel(new NEO(), () -> 0.5), new Wheel(new Talon(), () -> 0.5), () -> Math.random());
        for (int i = 0; i < 1500; i++) {
            robot.periodic();
        }
    }
}
