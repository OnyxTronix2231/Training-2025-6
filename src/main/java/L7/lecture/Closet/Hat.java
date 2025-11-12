package L7.lecture.Closet;

public class Hat extends Clothing{

    private String brand;

    public Hat(int size, boolean isClean, String brand) {
        super(size, isClean);
        this.brand = brand;
    }

    public void showOff() {
        for (int i = 0; i < 5; i++) {
            System.out.println(brand);
        }
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
