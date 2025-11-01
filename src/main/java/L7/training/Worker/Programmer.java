package L7.training.Worker;

import edu.wpi.first.math.MathUtil;

public class Programmer extends Worker {
    protected String language;
    protected String whatTheyDo;
    protected int monthlyBonus;
    protected int experience;

    public Programmer(String language, String whatTheyDo, int monthlyBonus, int experience) {
        this.language = language;
        this.whatTheyDo = whatTheyDo;
        this.monthlyBonus = monthlyBonus;
        this.experience = MathUtil.clamp(experience, 0, 10);
    }

    @Override
    public String toString() {
        return super.toString() + "\n  language: " + language +
                "\n  whatTheyDo: " + whatTheyDo +
                "\n  monthlyBonus: " + monthlyBonus +
                "\n  experience: " + experience;
    }

    public double calculateMonthlySalary(double hoursWorked) {
        return super.calculateMonthlySalary(hoursWorked) + (monthlyBonus * (1 + (experience / 10.0)));
    }
}
