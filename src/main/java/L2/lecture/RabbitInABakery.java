package L2.lecture;

public class RabbitInABakery {
    public static void main(String[] args) {

        int rabitsMoney = 500; // ILS
        double rabitsDolar = rabitsMoney / 3.29;
        System.out.println("rabit's money in dolars: " + rabitsDolar);

        double cakeCount = rabitsDolar / 5;
        System.out.println("number of cakes: " + cakeCount);

        double moneyLeft = rabitsDolar % 5;
        System.out.println("money left in dolars: " + moneyLeft);

        double wholeCakes = (rabitsDolar - moneyLeft) / 5;
        System.out.println("whole cakes: " + wholeCakes);

        boolean can40 = rabitsDolar / 4 > 40;
        System.out.println("can he buy 40? " + can40);
    }
}
