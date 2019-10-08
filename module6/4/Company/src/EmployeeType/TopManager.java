package EmployeeType;

import Company.Company;

public class TopManager extends EmployeeType
{
    public TopManager(int baseSalary) {
        super(baseSalary);
    }

    @Override
    public int getMonthSalary(Company company) {
        return company.getIncome() >= company.getOVER_INCOME() ? baseSalary * 2 : baseSalary;
    }

    @Override
    public String getTypeEmployee() {
        return "Топ Менеджер";
    }
}