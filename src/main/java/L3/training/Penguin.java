package L3.training;

public class Penguin {
    private String name;
    private int age;

    public Penguin(String name, int age) {
        this.name = name;
        this.age = age;
        if (age < 0) {
            this.age = age * -1;
        }
    }

    public static void sayOlder(Penguin p1, Penguin p2) {
        if (p1.getAge() > p2.getAge()) {
            System.out.println(p1.getName() + " is older");
        } else {
            System.out.println(p2.getName() + " is older");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void sayName() {
        System.out.println("Hello, " + name + "!");
    }

    @Override
    public String toString() {
        return "Penguin:" + "\n    Name: " + name + "\n    Age: " + age;
    }
}
