package EmployeeType;

abstract public class EmployeeType implements Comparable <EmployeeType>
{
    protected int baseSalary;
    protected int monthSalary;

    public EmployeeType(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    abstract public int getMonthSalary();

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