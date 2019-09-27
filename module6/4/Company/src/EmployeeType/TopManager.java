package EmployeeType;

public class TopManager extends EmployeeType
{
    private boolean isOverIncome;

    public TopManager(int baseSalary) {
        super(baseSalary);
    }

    @Override
    public int getMonthSalary() {
        return this.monthSalary = isOverIncome ? baseSalary * 2 : baseSalary;
    }

    @Override
    public String getTypeEmployee() {
        return "Топ Менеджер";
    }


    public void setIsOverIncome(boolean isOverIncome) {
        this.isOverIncome = isOverIncome;
    }
}