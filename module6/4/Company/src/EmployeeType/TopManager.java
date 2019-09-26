package EmployeeType;

public class TopManager extends EmployeeType
{
    private static boolean isOverIncome;

    public TopManager(int baseSalary) {
        super(baseSalary);
    }

    @Override
    public int getMonthSalary() {
        this.monthSalary = isOverIncome ? baseSalary * 2 : baseSalary;
        return super.getMonthSalary();
    }

    @Override
    public String getTypeEmployee() {
        return "Топ Менеджер";
    }

    public static void setIsOverIncome(boolean isOverIncome) {
        TopManager.isOverIncome = isOverIncome;
    }
}