package L2.training;

import org.ejml.sparse.csc.decomposition.chol.CholeskyUpLooking_DSCC;

public class Clock {
    private int second;
    private int minute;
    private int hour;

    public Clock(int second, int minute, int hour)
    {
        this.second = second;
        this.minute = minute;
        this.hour = hour;
    }

    public void setTime(int second, int minute, int hour)
    {
        this.second = second;
        this.minute = minute;
        this.hour = hour;
    }

    public void setSecond(int second)
    {
        this.second = second;
    }

    public void setMinute(int minute)
    {
        this.minute = minute;
    }

    public void setHour(int hour)
    {
        this.hour = hour;
    }

    public int getSecond()
    {
        return this.second;
    }

    public int getMinute()
    {
        return this.minute;
    }

    public int getHour()
    {
        return this.hour;
    }

    public void sayTime()
    {
        System.out.println("The time is: " + this.hour + ":" + this.minute + ":" + this.second);
    }

    public static void main(String[] args)
    {
        Clock clock = new Clock(1,2,3);
        clock.sayTime();
    }
}
