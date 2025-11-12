package L8.lecture;

public class SlotMachine {


    private final RandomNumGenerator randomNumGenerator;

    public SlotMachine(RandomNumGenerator randomNumGenerator) {
        this.randomNumGenerator = randomNumGenerator;
    }

    public int bet() {
        int n = randomNumGenerator.generateNumber();
        System.out.println("Guess your number");
        return n;
    }
}
