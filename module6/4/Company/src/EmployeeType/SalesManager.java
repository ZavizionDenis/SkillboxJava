package EmployeeType;

public class SalesManager extends EmployeeType
{
    private static final double BONUS_RATE = 0.05;
    private int salesManagerDealSum;

    public SalesManager(int baseSalary, int salesManagerDealSum) {
        super(baseSalary);
        this.salesManagerDealSum = salesManagerDealSum;
    }

    @Override
    public int getMonthSalary() {
        this.monthSalary = (int) (baseSalary + salesManagerDealSum * BONUS_RATE);
        return super.getMonthSalary();
    }

    @Override
    public String getTypeEmployee() {
        return "Менеджер по продажам";
    }
}