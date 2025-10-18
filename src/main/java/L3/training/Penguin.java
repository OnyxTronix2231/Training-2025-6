package L3.training;

public class Penguin {
    private int age;
    private String name;

    public Penguin(int age, String name) {
        if (age<1){
            this.age = 1;
        }
        if (age>18){
            this.age = 18;
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void sayName(){
        System.out.println("Hello " + name);
    }
    public void olderPenguin(int age2 , String name2){
        if (age2 > age){
            System.out.println(name2);
        }
        else {
            System.out.println(name);
        }
    }

    @Override
    public String toString() {
        return "Penguin{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
