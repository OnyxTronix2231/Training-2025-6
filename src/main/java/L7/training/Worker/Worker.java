package L7.training.Worker;

public class Worker {
    protected String name;
    protected int workerNum;
    protected int idNum;
    protected int age;
    protected String job;
    protected double hourlySalary;

    @Override
    public String toString() {
        return "Worker" + "\n  workerNum: " + workerNum +
                "\n  name: " + name +
                "\n  idNum: " + idNum +
                "\n  age: " + age +
                "\n  job: " + job +
                "\n  hourlySalary: " + hourlySalary;
    }

    public double calculateMonthlySalary(double hoursWorked) {
        return hourlySalary * hoursWorked;
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

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(double hourlySalary) {
        this.hourlySalary = hourlySalary;
    }
}
