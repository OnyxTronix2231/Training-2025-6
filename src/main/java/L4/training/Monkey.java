package L4.training;

public class Monkey {
    static int monkeyCount = 0;
    String name;
    Date birthday;

    public Monkey(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
        monkeyCount++;
    }

    public static int getMonkeyCount() {
        return monkeyCount;
    }

    public static Monkey getYoungestMonkey(Monkey monkey1, Monkey monkey2) {
        return !Date.getGreatest(monkey1.birthday, monkey2.birthday).equals(monkey1.birthday) ? monkey1 : monkey2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void sayHello() {
        System.out.println("Hello " + name);
    }

    @Override
    public String toString() {
        return "Monkey " + "\n  Name: " + name + "\n  Birthday: " + birthday.toString();
    }

    public void flyToTheMoon() {
        System.out.println("Bye, " + name + "!");
        monkeyCount--;
    }
}