package L4.training;

public class Main {
    public static void main(String[] args) {
        Date date = new Date(Date.Month.January, 1, 2025);
        System.out.println(date);
        date.increaseByOneDay();
        System.out.println(date);
        date.setDay(31);
        System.out.println(date);
        date.increaseByOneDay();
        System.out.println(date);

        System.out.println("Monkey");
        Monkey monkey = new Monkey("Gosho", date);
        System.out.println(monkey);
        monkey.sayHello();
        Monkey monkey2 = new Monkey("Peso", new Date(Date.Month.February, 1, 2025));
        System.out.println(monkey2);
        monkey2.sayHello();
        System.out.println("Youngest: ");
        System.out.println(Monkey.getYoungestMonkey(monkey, monkey2).toString());

        System.out.println("ShopProduct");
        ShopProduct product = new ShopProduct("Milk", 7, "DF631");
        System.out.println(product);
        ShopProduct product2 = new ShopProduct("Milk", 7, "DF631");
        System.out.println(ShopProduct.equals(product, product2)); // true
        ShopProduct product3 = new ShopProduct("Eggs", 5, "GD248");
        System.out.println(product3);
        System.out.println(ShopProduct.equals(product, product3)); // false
    }
}
