package EmployeeType;

import Company.Company;

public class Operator extends EmployeeType
{
    public Operator(int baseSalary) {
        super(baseSalary);
    }

    @Override
    public int getMonthSalary(Company company) {
        return this.monthSalary = baseSalary;
    }

    @Override
    public String getTypeEmployee() {
        return "Оператор";
    }
}