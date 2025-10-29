package L7.training;

public class Hat extends Clothes {

    protected String Brand;

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public Hat(int Size, boolean isClean, String Brand) {
        super(Size, isClean);
        this.Brand = Brand;
    }

}
