package L3.training;

public class Main {
    public static void main(String[] args) {
        Color color = new Color(200, 0, 0);
        System.out.println(color);

        Battery battery = new Battery(12);
        System.out.println(battery.getVolt());
        battery.charge(10);
        System.out.println(battery.getVolt());
        System.out.println(battery);
    }
}
