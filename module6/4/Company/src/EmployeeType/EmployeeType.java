package EmployeeType;

import Company.Company;

abstract public class EmployeeType implements Comparable <EmployeeType>
{
    protected int baseSalary;
    protected int monthSalary;

    public EmployeeType(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    abstract public int getMonthSalary(Company company);

    abstract public String getTypeEmployee ();

    @Override
    public int compareTo(EmployeeType employee) {
        if (monthSalary > employee.monthSalary) {
            return 1;
        }
        if (monthSalary < employee.monthSalary) {
            return -1;
        }
        return 0;
    }
}