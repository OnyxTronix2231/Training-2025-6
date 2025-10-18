package L3.training;

public class BallonShop {
    private String shop_name;
    private double ballon_price;

    public BallonShop(String shop_name, double ballon_price) {
        if (ballon_price<1){
            this.ballon_price = 1;
        }
        this.shop_name = shop_name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public double getBallon_price() {
        return ballon_price;
    }

    public void setBallon_price(double ballon_price) {
        this.ballon_price = ballon_price;
    }
    public boolean canBuy(int amount,double balance){
        return amount * getBallon_price() < balance;
    }

    public static void main(String[] args) {
        BallonShop shop = new BallonShop("Idk",0.5);
        System.out.println(shop.canBuy(5,3));
    }
}
