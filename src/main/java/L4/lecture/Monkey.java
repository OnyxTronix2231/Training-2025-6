package L4.lecture;

public class Monkey {
    private String name;
    private OnyxDate dateOfBirth;

    public static int monkeyCount = 0;

    public Monkey(String name, OnyxDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        monkeyCount++;
    }

    public void sayHello() {
        System.out.println("Hello, " + name + "!");
    }

    public static OnyxDate getEldest(Monkey m1, Monkey m2) {
        return OnyxDate.getGreatest(m1.getDateOfBirth(), m2.getDateOfBirth());
    }

    public void flyToTheMoon() {
        System.out.println("Farewell Mr. " + name + ", you've inspired us all!");
        monkeyCount --;
    }

    public OnyxDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(OnyxDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
