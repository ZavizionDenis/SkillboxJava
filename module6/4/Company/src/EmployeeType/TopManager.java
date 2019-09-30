package EmployeeType;

import Company.Company;

public class TopManager extends EmployeeType
{
    private boolean isOverIncome;

    public TopManager(int baseSalary) {
        super(baseSalary);
    }

    @Override
    public int getMonthSalary(Company company) {
        return this.monthSalary = company.isOverIncome() ? baseSalary * 2 : baseSalary;
    }

    @Override
    public String getTypeEmployee() {
        return "Топ Менеджер";
    }
}