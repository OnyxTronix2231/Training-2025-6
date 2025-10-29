package L7.training.WorkerAndProgEX;

public class Programmer extends Worker {
    private String programmingLanguage;
    private String whatHeProgrammes;
    private double monthlyBonus;
    private int talent;

    public Programmer(String name, int workerNum, int personalNum, double age, String role, int moneyPerHour, String programmingLanguage, String whatHeProgrammes, double monthlyBonus, int talent) {
        super(name, workerNum, personalNum, age, role, moneyPerHour);
        this.programmingLanguage = programmingLanguage;
        this.whatHeProgrammes = whatHeProgrammes;
        this.monthlyBonus = monthlyBonus;
        this.talent = talent;
    }

    @Override
    public String toString() {
        return "Programmer{" +
                "programmingLanguage='" + programmingLanguage + '\'' +
                ", whatHeProgrammes='" + whatHeProgrammes + '\'' +
                ", monthlyBonus=" + monthlyBonus +
                ", Talent=" + talent +
                '}';
    }

    public double monthlyPayCheckWithBonus(int hours){
        int monthlyCheck = hours*getMoneyPerHour();
        return monthlyCheck + (monthlyCheck*0.1*this.talent);
    }


}
