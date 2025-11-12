package L7.lecture.Closet;

public class Jeans extends Clothing {

    public Jeans(int size, boolean isClean) {
        super(size, isClean);
    }

    @Override
    public void wash() {
        super.wash();
        this.size /= 2;
    }
}
