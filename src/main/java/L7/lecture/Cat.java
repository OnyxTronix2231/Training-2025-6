package L7.lecture;

public class Cat extends Mammal {

    public Cat(String name, int age, Legs fourLegs) {
        super(name, age, fourLegs);
    }

    @Override
    public void makeNoise() {
        System.out.println("meow");
    }

    public void drinkMilk() {
        System.out.println("* drinking sounds *");
    }
}
