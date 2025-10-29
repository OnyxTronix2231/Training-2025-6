package L7.lecture.Closet;

public class Clothing {
    protected int size;
    protected boolean isClean;

    public Clothing(int size, boolean isClean) {
        this.size = size;
        this.isClean = isClean;
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

    public static void ploop(Clothing clothing) {
        System.out.println(clothing.getSize());
        clothing.wash();
        System.out.println(clothing.getSize());
    }
}
