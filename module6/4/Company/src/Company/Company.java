package Company;

import EmployeeType.EmployeeType;
import EmployeeType.Operator;
import EmployeeType.SalesManager;
import EmployeeType.TopManager;

import java.util.*;

import Help.Help;

public class Company {

    private ArrayList <EmployeeType> employeesList;
    private final int OVER_INCOME = 10_000_000;

    private int operatorBaseSalary = (int) (30_000 + 10_000 * Math.random());
    private int salesManagerBaseSalary = (int) (50_000 + 10_000 * Math.random());
    private int topManagerBaseSalary = (int) (100_000 + 50_000 * Math.random());

    public Company(final int EMPLOYEE_COUNT) {
        generateCompany(EMPLOYEE_COUNT);
    }
//====================================================================================================================
    private void generateCompany (final int EMPLOYEE_COUNT) {
        employeesList = new ArrayList<EmployeeType>();
        System.out.println("Общее количество сотрудников в компании: " + EMPLOYEE_COUNT);
        final int OPERATOR_COUNT = (int) Math.ceil(EMPLOYEE_COUNT / 1.3);
        System.out.println("Количество операторов в компании: " + OPERATOR_COUNT);
        final int SALES_MANAGER_COUNT = (int) Math.ceil(OPERATOR_COUNT / 4);
        System.out.println("Количество менеджеров по продажам в компании: " + SALES_MANAGER_COUNT);
        final int TOP_MANAGER_COUNT = EMPLOYEE_COUNT - (OPERATOR_COUNT + SALES_MANAGER_COUNT);
        System.out.println("Количество топ менеджеров в компании: " + TOP_MANAGER_COUNT);

        System.out.println("Базовая зарплата оператора: " + operatorBaseSalary);
        System.out.println("Базовая зарплата менеджера по продажам: " + salesManagerBaseSalary);
        System.out.println("Базовая зарплата топ менеджера: " + topManagerBaseSalary);

        for (int i = 1; i <= EMPLOYEE_COUNT; i++) {

            if (i <= TOP_MANAGER_COUNT) {
                hireEmployee(new TopManager(topManagerBaseSalary));
            }

            if (i > TOP_MANAGER_COUNT && i <= SALES_MANAGER_COUNT + TOP_MANAGER_COUNT) {
                hireEmployee(new SalesManager(salesManagerBaseSalary));

            }

            if (i > SALES_MANAGER_COUNT + TOP_MANAGER_COUNT) {
                hireEmployee(new Operator(operatorBaseSalary));
            }
        }
        if (getIncome() >= OVER_INCOME) {
            System.out.printf("Доход компании: %,d превысил границу сверх дохода: %,d, выдаем топ менеджерам премию на яхты/машины/прочее\n\n", getIncome(), OVER_INCOME);
        }
        else {
            System.out.printf("Доход компании: %,d не достигли границы сверх дохода: %,d, топ менеджерам придется питаться макарошками, они рыдают и раздают всем лещей\n\n", getIncome(), OVER_INCOME);
        }
    }
//====================================================================================================================
    public ArrayList <EmployeeType> getTopSalaryStaff(int count) {
        count = checkCountRequest(count);
        getSortEmployeesList();
        Collections.reverse(employeesList);
        return new ArrayList<>(employeesList.subList(0, count));
    }
//====================================================================================================================
    public ArrayList <EmployeeType> getLowestSalaryStaff(int count) {
        count = checkCountRequest(count);
        getSortEmployeesList();
        return new ArrayList<>(employeesList.subList(0, count));
    }
//====================================================================================================================
    public void newEmployee () {
        System.out.println(Help.HELP_EMPLOYEE_TYPE.getDescription() + "Укажите должность нанимаемого работника: ");
        int employeeType = new Scanner(System.in).nextInt();

        if (employeeType == 1) {
                hireEmployee(new TopManager(topManagerBaseSalary));
        }

        if (employeeType == 2) {
                hireEmployee(new SalesManager(salesManagerBaseSalary));
        }

        if (employeeType == 3) {
                hireEmployee(new Operator(operatorBaseSalary));
        }
    }
//====================================================================================================================
    public ArrayList<EmployeeType> getEmployeesList() {
        return employeesList;
    }

    private void hireEmployee(EmployeeType employee) {
        employeesList.add(employee);
    }

    public void dismissEmployee (EmployeeType employee) {
        employeesList.remove(employee);
    }

    public int getOVER_INCOME() {
        return OVER_INCOME;
    }

    public int getIncome () {
        int companyIncome = 0;
        for (EmployeeType employee : employeesList) {
            if (employee instanceof SalesManager) {
                companyIncome += ((SalesManager) employee).getSalesManagerDealSum();
            }
        }
        return companyIncome;
    }

    private int checkCountRequest (int count) {
        if (count > employeesList.size()) {
            System.out.println("Запрашиваемое кол-во для вывода превышает общее кол-во сотрудников. Будут выведены все сотрудники.");
            count = employeesList.size();
        }
        return count;
    }

    private void getSortEmployeesList () {
        Company company = this;
        employeesList.sort(Comparator.comparingInt(o -> o.getMonthSalary(company)));
    }
//====================================================================================================================
}