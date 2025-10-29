package L7.training.ClotesEX;

public class Cloth {
    protected int size;
    protected boolean isClean;

    public Cloth(int size, boolean isClean) {
        this.size = size;
        this.isClean = isClean;
    }
    public void wash(){
        this.isClean = true;
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

    public void printAndCleanAndPrint(Cloth cloth){
        System.out.println(cloth.getSize());
        cloth.wash();

    }
}

