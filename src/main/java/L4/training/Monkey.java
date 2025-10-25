package L4.training;

public class Monkey {
    private String name;
    private OnyxDate date;
    public static  int monkeycount  = 0 ;
    public Monkey(String name, OnyxDate date) {
        this.name = name;
        this.date = date;
        monkeycount ++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OnyxDate getDate() {
        return date;
    }

    public void setDate(OnyxDate date) {
        this.date = date;
    }

    public void sayHello(Monkey m1){
        System.out.println("Hello " + m1.name);
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
    public static OnyxDate getEldest(Monkey m1,Monkey m2){
        return OnyxDate.getGreatest(m1.date,m2.date);
    }
    public void flyToTheMoon(){
        System.out.println("Goodbye : " + name);
        monkeycount--;
    }

}
