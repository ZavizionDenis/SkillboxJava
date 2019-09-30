package EmployeeType;

import Company.Company;

public class SalesManager extends EmployeeType
{
    private static final double BONUS_RATE = 0.05;
    private int salesManagerDealSum;

    public SalesManager(int baseSalary) {
        super(baseSalary);
        salesManagerDealSum = (int) (100_000 + 200_000 * Math.random());
    }

    @Override
    public int getMonthSalary(Company company) {
        return this.monthSalary = (int) (baseSalary + salesManagerDealSum * BONUS_RATE);
    }

    public int getSalesManagerDealSum() {
        return salesManagerDealSum;
    }

    @Override
    public String getTypeEmployee() {
        return "Менеджер по продажам";
    }
}