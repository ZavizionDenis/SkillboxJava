import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main
{
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args)
    {
        ArrayList<Employee> staff = loadStaffFromFile();

        printStaff(staff);

        System.out.println();

        staff.stream().filter(Main::dateCompare)
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(System.out::println);
    }

    private static Boolean dateCompare (Employee e) {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2017,Calendar.JANUARY,1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2018,Calendar.JANUARY,1);
        Calendar thisDate = Calendar.getInstance();
        thisDate.setTime(e.getWorkStart());

        return thisDate.after(startDate) && thisDate.before(endDate);
    }

    private static void printStaff (ArrayList <Employee> staff) {
        for (Employee employee : staff) {
            System.out.println(employee);
        }
    }

    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}