package L4.training;

public class ShopProduct {
    String name;
    int price;
    String SSN;

    public ShopProduct(String name, int price, String SSN) {
        this.name = name;
        this.price = price;
        this.SSN = SSN;
    }

    public static boolean equals(ShopProduct p1, ShopProduct p2) {
        return p1.SSN.equals(p2.SSN);
    }

    @Override
    public String toString() {
        return "ShopProduct\n" +
                "  SSN: " + SSN + "\n" +
                "  Name: " + name + "\n" +
                "  Price: " + price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public int getPrice(int count) {
        if (count > 5) {
            double discountedPrice = price - (price * 22.31 / 100);
            return (int) (discountedPrice * (count - 5)) + (price * 5);
        } else return count * price;
    }


}
