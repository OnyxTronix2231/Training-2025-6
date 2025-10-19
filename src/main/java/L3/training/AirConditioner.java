package L3.training;

public class AirConditioner {
    private String companyName;
    private int temperature;

    public AirConditioner(String companyName, int temperature) {
        this.companyName = companyName;
        if (temperature<16){
            temperature = 16;
        }
        if (temperature > 30){
            temperature = 30;
        }
        this.temperature = temperature;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    public void setHyperMode(){
        if (temperature > 23){
            temperature = 30;
        }
        if (temperature <23){
            temperature = 16;
        }
        if (temperature == 23){
            System.out.println("You are in the middle");
        }
    }
    public void printState(){
        System.out.println("Name of the company: " + companyName);
        System.out.println("Temperature: " + temperature);
    }
}
