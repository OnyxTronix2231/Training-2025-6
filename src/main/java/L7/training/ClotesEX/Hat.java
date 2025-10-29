package L7.training.ClotesEX;

public class Hat extends Cloth {
    String brand;

    public Hat(int size, boolean isClean, String brand) {
        super(size, isClean);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
