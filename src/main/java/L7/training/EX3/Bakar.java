package L7.training.EX3;

public class Bakar {
    protected String bakarName;
    protected int output;

    public Bakar(String bakarName, int output) {
        this.bakarName = bakarName;
        this.output = output;
    }

    public String getBakarName() {
        return bakarName;
    }

    public void setBakarName(String bakarName) {
        this.bakarName = bakarName;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        if (output<-1){
            output = -1;
        }
        if (output> 1){
            output = 1;
        }
        this.output = output/2;
    }

    @Override
    public String toString() {
        return "Bakar{" +
                "bakarName='" + bakarName + '\'' +
                ", output=" + output +
                '}';
    }

}
