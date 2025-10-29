package L7.training.ClotesEX;

public class Jeans extends Cloth {


    public Jeans(int size, boolean isClean) {
        super(size, isClean);
    }
    @Override
    public void wash(){
        super.wash();
        this.size = this.size/2;
    }

}
