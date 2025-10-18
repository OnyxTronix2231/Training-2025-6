package L2.training;

public class Main {
    public static void main(String[] args) {
        System.out.println("2. Color");
        Color color1 = new Color(200, 0, 0);
        Color color2 = new Color(0, 200, 0);
        Color color3 = Color.addByFactor(color1, color2, 25);
        System.out.println("addByFactor (50,150,0):");
        System.out.println(color3.getRed());
        System.out.println(color3.getGreen());
        System.out.println(color3.getBlue());

        Color factor = new Color(200, 0, 0);
        Color fResult = Color.getFactoredColor(factor, 25);
        System.out.println("getFactoredColor (50,0,0):");
        System.out.println(fResult.getRed());
        System.out.println(fResult.getGreen());
        System.out.println(fResult.getBlue());

        System.out.println("2.2 Clock");
        Clock clock = new Clock(1, 23, 9);
        System.out.println("should say 9:23");
        clock.sayTime();
        clock.reset(3, 12, 3);
        System.out.println("should say 3:12");
        clock.sayTime();

        System.out.println("Trinom");
        Trinom trinom = new Trinom(1.0, 2.0, 4.0);
        System.out.println("should say 1.0x^2+2.0x+4.0");
        trinom.printTrinom();
        trinom.moveTrinom(3);
        System.out.println("should say 1.0x^2+2.0x+7.0");
        trinom.printTrinom();

        Trinom trinom2 = new Trinom(1, 13, 42);
        System.out.println("Should say -6.0");
        System.out.println(trinom2.solve());

        System.out.println("Point");

        Point point = new Point(2, 2);
        System.out.println(point.getDistanceFromOrigin());
        point.setPoint(3, 3);
        System.out.println(point.getDistanceFromOrigin());

        System.out.println("Circle");

        Circle circle = new Circle(point, 2);
        circle.moveCircle(3, 3);
        System.out.println(circle.getPoint().getDistanceFromOrigin());
        circle.addRadius(2);
        System.out.println(circle.getRadius());
        System.out.println("area");
        System.out.println(circle.getArea());
        System.out.println("circumference");
        System.out.println(circle.getCircumference());


    }
}
