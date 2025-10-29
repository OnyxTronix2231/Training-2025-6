package L7.training;

public class Jeans extends Clothes {

    public Jeans(int Size, boolean isClean) {
        super(Size, isClean);
    }

    @Override
    public void Wash() {
        isClean = true;
        Size /= 2;
    }


}
