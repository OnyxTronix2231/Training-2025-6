package L2.training;

public class bunny {
    public static void main(String[] args) {
        double bal = 500;
        double priceUSD = 4;
        double conversion = 3.29;

        double balUSD = bal/conversion;
        System.out.println("bunny has "+balUSD+" dollars");

        double maxAmt = balUSD/priceUSD;
        System.out.println("bunny can buy " + maxAmt + " cakes");

        double moneyLeft = balUSD % priceUSD;
        System.out.println("If bunny bought as many whole cakes as he could he would have "+moneyLeft+"$ left");

        double fullCakesBuy = (balUSD-moneyLeft)/priceUSD;
        System.out.println("The bunny can buy "+fullCakesBuy+" whole cakes");

        boolean canBuy40 = fullCakesBuy >= 40;
        System.out.println("The bunny can buy 40 cakes: " + canBuy40);
//        System.out.println("The bunny can buy 40 cakes: " + (canBuy40 ? "yes" : "no"));
        /*
        if(fullCakesBuy>=40){
            System.out.println("Bunny can buy 40 or more cakes");
        } else {
            System.out.println("Bunny cannot buy more than 39 cokes");
        }
         */
    }
}
