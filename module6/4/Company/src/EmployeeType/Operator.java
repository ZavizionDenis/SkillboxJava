package EmployeeType;

public class Operator extends EmployeeType
{
    public Operator(int baseSalary) {
        super(baseSalary);
    }

    @Override
    public int getMonthSalary() {
        return this.monthSalary = baseSalary;
    }

    @Override
    public String getTypeEmployee() {
        return "Оператор";
    }
}