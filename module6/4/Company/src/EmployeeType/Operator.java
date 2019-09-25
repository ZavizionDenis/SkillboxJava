package EmployeeType;

public class Operator extends EmployeeType
{
    public Operator(int baseSalary) {
        super(baseSalary);
    }

    @Override
    public int getMonthSalary() {
        this.monthSalary = baseSalary;
        return super.getMonthSalary();
    }

    @Override
    public String getTypeEmployee() {
        return "Оператор";
    }
}