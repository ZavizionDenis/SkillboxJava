import Company.Company;
import EmployeeType.*;
import Help.Help;

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
                printRequestMonthSalary(company.getTopSalaryStaff(count), company);
            }

            if (inputCommand == 2) {
                int count = requestCountRecords();
                printRequestMonthSalary(company.getLowestSalaryStaff(count), company);
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
                    company.dismissEmployee(employee);
                    System.out.printf("Уволен: %s c зарплатой: %,d, его номер по табелю %d%n", employee.getTypeEmployee(), employee.getMonthSalary(company), employeeNumber);
                }
                else {
                    System.out.println("В компании не осталось сотрудников.");
                }
            }

            if (inputCommand == 5) {
                script(company);
            }
        }
    }
//=====================================================================================================================
    private static void printRequestMonthSalary (ArrayList <EmployeeType> arrayList, Company company) {
        for (EmployeeType monthSalary : arrayList) {
            System.out.println(monthSalary.getMonthSalary(company));
        }
    }
//=====================================================================================================================
    private static int requestCountRecords () {
        System.out.println("Укажите количество записий для вывода:");
        return new Scanner(System.in).nextInt();
    }
//=====================================================================================================================
    private static void script (Company company) {
        int employeeCount = 70;
        if (company.getEmployeesList().size() > 0) {
            printRequestMonthSalary(company.getTopSalaryStaff(employeeCount), company);
            int dismissEmployeeCount = (int) Math.ceil(company.getEmployeesList().size() * 0.1);
            for (int i = 0; i < dismissEmployeeCount; i++) {
                int employeeNumber = new Random().nextInt(company.getEmployeesList().size());
                EmployeeType employee = company.getEmployeesList().get(employeeNumber);
                company.dismissEmployee(employee);
                System.out.printf("Уволен: %s c зарплатой: %,d, его номер по табелю %d%n", employee.getTypeEmployee(), employee.getMonthSalary(company), employeeNumber);
            }
            printRequestMonthSalary(company.getTopSalaryStaff(employeeCount), company);
        }
        else {
            System.out.println("В компании не осталось сотрудников.");
        }
    }
}