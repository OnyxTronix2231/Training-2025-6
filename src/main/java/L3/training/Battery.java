package L3.training;

public class Battery {
    // כמות הוולוט בסוללה
    private double volts;
    public Battery (double volts){
        if (volts > 15){
            this.volts;
        else if (volts < 0) {
            this.volts = 0;
            else{
                this.volts = volts;

            public double getvolts(){
                return volts;

                public void setvolts(){
                    if (volts > 15){
                        this.volts = 15;
                        else if ( volts < 0 );
                            this.volts = 0;
                        else{
                            this.volts = volts;

                        }
// מדפיסה את מצב הסוללה
                        public void sayState(){
                            if (volts > 12){
                                System.out.println("good");
                            else if (volts >= 10){
                                    System.out.println("not so good");
                            else if (volts >= 6){
                                        System.out.println("bad");
                            else{
                                            System.out.println("really bad");
                                        }
                                    }
                                }
                            }
                        }
                    }
                        }
                    }

                }
            }
        }
    }
}
