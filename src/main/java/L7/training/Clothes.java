package L7.training;

public class Clothes {

    protected int Size;
    protected boolean isClean;

    public Clothes(int Size, boolean isClean) {
        this.Size = Size;
        this.isClean = isClean;
    }

    public boolean isClean() {
        return isClean;
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

    public void Wash() {
        isClean = true;
    }

    public static void getClothes(Clothes clothes) {
        System.out.println(clothes.getSize());
        clothes.Wash();
        System.out.println(clothes.getSize());
    }
    
}
