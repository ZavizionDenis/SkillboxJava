package EmployeeType;

abstract public class EmployeeType implements Employee, Comparable <EmployeeType>
{
    protected int baseSalary;
    protected int monthSalary;

    public EmployeeType(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public int getMonthSalary() {
        return monthSalary;
    }

    abstract public String getTypeEmployee ();

    @Override
    public int compareTo(EmployeeType employee) {
        if (getMonthSalary() > employee.getMonthSalary()) {
            return 1;
        }
        if (getMonthSalary() < employee.getMonthSalary()) {
            return -1;
        }
        return 0;
    }
}