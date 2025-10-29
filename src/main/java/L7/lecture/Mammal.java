package L7.lecture;

public class Mammal extends Pet {

    protected Legs fourLegs;

    public Mammal(String name, int age, Legs fourLegs) {
        super(name, age);
        this.fourLegs = fourLegs;
    }

    public void walk() {
        System.out.println(fourLegs.toString());
    }
}
