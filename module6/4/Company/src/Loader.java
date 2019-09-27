import EmployeeType.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Loader
{
    private static final int EMPLOYEE_COUNT = 270;

    public static void main(String[] args) {
        
        Company company = new Company(EMPLOYEE_COUNT);

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
                if (company.getEmployeesList().size() < EMPLOYEE_COUNT) {
                    company.newEmployee();
                }
                else {
                    System.out.println("Свободных вакансий в кoмпании нет.");
                }
            }

            if (inputCommand == 4) {
                if (company.getEmployeesList().size() > 0) {
                    int employeeNumber = new Random().nextInt(company.getEmployeesList().size());
                    EmployeeType employee = company.getEmployeesList().get(employeeNumber);
                    company.dismissEmployee(company.getEmployeesList().get(employeeNumber));
                    System.out.printf("Уволен: %s c зарплатой: %,d, его номер по табелю %d%n", employee.getTypeEmployee(), employee.getMonthSalary(), employeeNumber);
                }
                else {
                    System.out.println("В компании не осталось сотрудников.");
                }
            }
        }
    }
//=====================================================================================================================
    private static void printRequestMonthSalary (ArrayList <EmployeeType> arrayList) {
        for (EmployeeType monthSalary : arrayList) {
            System.out.println(monthSalary.getMonthSalary());
        }
    }
//=====================================================================================================================
    private static int requestCountRecords () {
        System.out.println("Укажите количество записий для вывода:");
        return new Scanner(System.in).nextInt();
    }
}