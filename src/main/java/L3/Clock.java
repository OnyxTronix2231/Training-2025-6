package L3;

public class Clock {

    private int seconds;
    private int minutes;
    private int hours;

    public Clock (int seconds, int minutes, int hours) {
            this.seconds = seconds;
            this.minutes = minutes;
            this.hours = hours;

    }

    public void setTime(int seconds, int minutes, int hours) {
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;

    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void sayTime(int seconds, int minutes, int hours) {
        System.out.println(seconds);
        System.out.println(minutes);
        System.out.println(hours);
    }
}

