package L7.training.WorkerAndProgEX;

public class Worker {
    protected String name;
    protected int workerNum;
    protected int personalNum;
    protected double age;
    protected String role;
    protected int moneyPerHour;

    public Worker(String name, int workerNum, int personalNum, double age, String role, int moneyPerHour) {
        this.name = name;
        this.workerNum = workerNum;
        this.personalNum = personalNum;
        this.age = age;
        this.role = role;
        this.moneyPerHour = moneyPerHour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkerNum() {
        return workerNum;
    }

    public void setWorkerNum(int workerNum) {
        this.workerNum = workerNum;
    }

    public int getPersonalNum() {
        return personalNum;
    }

    public void setPersonalNum(int personalNum) {
        this.personalNum = personalNum;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getMoneyPerHour() {
        return moneyPerHour;
    }

    public void setMoneyPerHour(int moneyPerHour) {
        this.moneyPerHour = moneyPerHour;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", workerNum=" + workerNum +
                ", personalNum=" + personalNum +
                ", age=" + age +
                ", role='" + role + '\'' +
                ", moneyPerHour=" + moneyPerHour +
                '}';
    }

    public int monthlyPayCheck(int hours){
        return hours*this.moneyPerHour;
    }
}
