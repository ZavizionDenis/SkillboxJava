import EmployeeType.Employee;
import EmployeeType.EmployeeType;
import java.util.ArrayList;
import java.util.Collections;

public class Company {

    private ArrayList <EmployeeType> employeesList;

    public Company() {
        employeesList = new ArrayList<EmployeeType>();
    }

    public ArrayList <Employee> getTopSalaryStaff(int count) {
        Collections.sort(getEmployeesList(), Collections.reverseOrder());
        ArrayList <Employee> monthTopSalaryList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            monthTopSalaryList.add(getEmployeesList().get(i));
        }
        return monthTopSalaryList;
    }

    public ArrayList <Employee> getLowestSalaryStaff(int count) {
        Collections.sort(getEmployeesList());
        ArrayList <Employee> monthLowestSalaryList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            monthLowestSalaryList.add(getEmployeesList().get(i));
        }
        return monthLowestSalaryList;
    }

    public ArrayList<EmployeeType> getEmployeesList() {
        return employeesList;
    }

    public void hireEmployee(EmployeeType employee) {
        employeesList.add(employee);
    }

    public void dismissEmployee (int employeeNumber) {
        employeesList.remove(employeeNumber);
    }
}