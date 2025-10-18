package L3.training;

public class Battery {
    private int volt;

    public Battery(int volt) {
        if (volt > 15)
            this.volt = 15;
        else this.volt = Math.max(volt, 0);
    }

    public int getVolt() {
        return volt;
    }

    public void setVolt(int volt) {
        if (volt > 15)
            this.volt = 15;
        else this.volt = Math.max(volt, 0);
    }

    public void sayState() {
        if (volt > 12)
            System.out.println("Good");
        else if (volt > 10)
            System.out.println("Not so good");
        else if (volt > 6)
            System.out.println("Bad");
        else System.out.println("Really bad");
    }

    public void charge(int hours) {
        volt += hours * 3;
        if (volt > 15)
            volt = 15;
    }

    @Override
    public String toString() {
        return "Battery:" +
                "\n    Volt: " + volt;
    }
}
