package L7.lecture;

public class Parrot extends Pet{

    public Parrot(String name, int age) {
        super(name, age);
        this.makeNoise();
        super.makeNoise();
        makeNoise();
    }

    public void fly() {
        System.out.println("flap flap");
    }

    @Override
    public void makeNoise() {
        System.out.println(
                name + "wants a cracker!"
        );
    }
}
