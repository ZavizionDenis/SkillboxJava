package EmployeeType;

import Company.Company;

abstract public class EmployeeType
{
    protected int baseSalary;

    public EmployeeType(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    abstract public int getMonthSalary(Company company);

    abstract public String getTypeEmployee ();
}