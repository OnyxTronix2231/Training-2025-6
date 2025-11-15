package L8.lecture;

import L7.lecture.Dog;
import L7.lecture.Legs;
import L7.lecture.Pet;

import java.awt.*;

public class Dog2 extends Pet implements Noisy {
    private Legs legs;

    public Dog2(String name, int age, Legs legs) {
        super(name, age);
        this.legs = legs;
    }

    @Override
    public void makeNoise() {
        System.out.println("woof");
    }

    public static void main(String[] args) {
        Noisy n1 = new Dog2("Rex", 4, new Legs());
        Noisy n2 = new Truck(Color.BLACK, 110);

        n1.makeNoise();
        n2.makeNoise();

        //n2.drive(50);
    }
}
