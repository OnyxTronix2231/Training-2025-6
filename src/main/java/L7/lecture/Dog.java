package L7.lecture;

public class Dog extends Mammal {

    private String toy;

    public Dog(String name, int age, Legs fourLegs, String toy) {
        super(name, age, fourLegs);
        this.toy = toy;
    }

    @Override
    public void makeNoise() {
        System.out.println("woof woof");
    }

    public String fetchToy() {
        return toy;
    }
}
