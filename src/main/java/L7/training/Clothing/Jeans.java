package L7.training.Clothing;

public class Jeans extends Clothing {
    public Jeans(int size, boolean isClean) {
        super(size, isClean);
    }

    @Override
    public void wash() {
        super.wash();
        size /= 2;
    }
}
