import EmployeeType.Employee;
import EmployeeType.Operator;
import EmployeeType.SalesManager;
import EmployeeType.TopManager;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader
{
    private static final int EMPLOYEE_COUNT = 270;
    private static int operatorBaseSalary = (int) (30_000 + 10_000 * Math.random());
    private static int salesManagerBaseSalary = (int) (50_000 + 10_000 * Math.random());
    private static int topManagerBaseSalary = (int) (100_000 + 50_000 * Math.random());
    private static Company company;

    public static void main(String[] args) {

        company = generateCompany();
        commands();

    }
//=====================================================================================================================
    private static void commands () {

        for (; ;) {
            System.out.println(Help.HELP_COMMANDS.getDescription() + "Введите команду:");
            int inputCommand = new Scanner(System.in).nextInt();

            if (inputCommand == 0) {
                break;
            }

            if (inputCommand == 1) {
                int count = requestCountRecords();
                if (count > company.getEmployeesList().size()) {
                    System.out.println("Ошибка. Указанное количество больше количества сотрудников.");
                }
                else {
                    printRequestMonthSalary(company.getTopSalaryStaff(count));
                }
            }

            if (inputCommand == 2) {
                int count = requestCountRecords();
                if (count > company.getEmployeesList().size()) {
                    System.out.println("Ошибка. Указанное количество больше количества сотрудников.");
                }
                else {
                    printRequestMonthSalary(company.getLowestSalaryStaff(count));
                }
            }

            if (inputCommand == 3) {
                newEmployee();
            }

            if (inputCommand == 4) {
                System.out.println("Введите номер сотрудника для увольнения: ");
                int employeeNumber = new Scanner(System.in).nextInt();
                if (employeeNumber >= 0 && employeeNumber < company.getEmployeesList().size()) {
                    company.dismissEmployee(employeeNumber);
                }
                else {
                    System.out.println("Такого сотрудника нет.");
                }
            }
        }
    }
//=====================================================================================================================
    private static void newEmployee () {
        if (company.getEmployeesList().size() < EMPLOYEE_COUNT) {
            System.out.println(Help.HELP_EMPLOYEE_TYPE.getDescription() + "Укажите должность нанимаемого работника: ");
            int employeeType = new Scanner(System.in).nextInt();

            if (employeeType == 1) {
                company.hireEmployee(new TopManager(topManagerBaseSalary));
            }

            if (employeeType == 2) {
                int salesManagerDealSum = (int) (150_000 + 200_000 * Math.random());
                company.hireEmployee(new SalesManager(salesManagerBaseSalary, salesManagerDealSum));
            }

            if (employeeType == 3) {
                company.hireEmployee(new Operator(operatorBaseSalary));
            }
        }
        else {
            System.out.println("Свободных вакансий в кoмпании нет.");
        }
    }
//=====================================================================================================================
    private static void printRequestMonthSalary (ArrayList <Employee> arrayList) {
        for (Employee monthSalary : arrayList) {
            System.out.println(monthSalary.getMonthSalary());
        }
    }
//=====================================================================================================================
    private static int requestCountRecords () {
        System.out.println("Укажите количество записий для вывода:");
        return new Scanner(System.in).nextInt();
    }
//=====================================================================================================================
    private static Company generateCompany () {
        System.out.println("Общее количество сотрудников в компании: " + EMPLOYEE_COUNT);
        final int OPERATOR_COUNT = (int) Math.ceil(EMPLOYEE_COUNT / 1.3);
        System.out.println("Количество операторов в компании: " + OPERATOR_COUNT);
        final int SALES_MANAGER_COUNT = (int) Math.ceil(OPERATOR_COUNT / 4);
        System.out.println("Количество менеджеров по продажам в компании: " + SALES_MANAGER_COUNT);
        final int TOP_MANAGER_COUNT = EMPLOYEE_COUNT - (OPERATOR_COUNT + SALES_MANAGER_COUNT);
        System.out.println("Количество топ менеджеров в компании: " + TOP_MANAGER_COUNT);
        final int OVER_INCOME = 10_000_000;

        System.out.println("Базовая зарплата оператора: " + operatorBaseSalary);
        System.out.println("Базовая зарплата менеджера по продажам: " + salesManagerBaseSalary);
        System.out.println("Базовая зарплата топ менеджера: " + topManagerBaseSalary);

        Company company = new Company();
        int companyIncome = 0;

        for (int i = 1; i <= EMPLOYEE_COUNT; i++) {

            if (i <= TOP_MANAGER_COUNT) {
                company.hireEmployee(new TopManager(topManagerBaseSalary));
            }

            if (i > TOP_MANAGER_COUNT && i <= SALES_MANAGER_COUNT + TOP_MANAGER_COUNT) {
                int salesManagerDealSum = (int) (150_000 + 200_000 * Math.random());
                companyIncome += salesManagerDealSum;
                company.hireEmployee(new SalesManager(salesManagerBaseSalary, salesManagerDealSum));
            }

            if (i > SALES_MANAGER_COUNT + TOP_MANAGER_COUNT) {
                company.hireEmployee(new Operator(operatorBaseSalary));
            }

        }

        boolean isOverIncome = companyIncome >= OVER_INCOME;
        if (isOverIncome) {
            System.out.printf("Доход компании: %,d превысил границу сверх дохода: %,d, выдаем топ менеджерам премию на яхты/машины/прочее\n\n", companyIncome, OVER_INCOME);
        }
        else {
            System.out.printf("Доход компании: %,d не достигли границы сверх дохода: %,d, топ менеджерам придется питаться макарошками, они рыдают и раздают всем лещей\n\n", companyIncome, OVER_INCOME);
        }
        TopManager.setIsOverIncome(isOverIncome);

        return company;
    }
//=====================================================================================================================
}