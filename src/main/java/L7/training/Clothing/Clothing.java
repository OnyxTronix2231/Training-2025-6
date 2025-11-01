package L7.training.Clothing;

public class Clothing {
    protected int size;
    protected boolean isClean;

    public Clothing(int size, boolean isClean) {
        this.size = size;
        this.isClean = isClean;
    }

    public static void takeCareOf(Clothing clothing) {
        System.out.println(clothing.size);
        clothing.wash();
        System.out.println(clothing.size);
    }

    public void wash() {
        isClean = true;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isClean() {
        return isClean;
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }
}
