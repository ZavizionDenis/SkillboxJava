import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Loader
{
    public static void main (String [] args)
    {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy - E",Locale.ENGLISH);
        LocalDate birthDate = LocalDate.of(1984, Month.FEBRUARY,10);
        LocalDate currentDate = LocalDate.now();

        int yearOld = currentDate.getYear() - birthDate.getYear();
        for (int birthDayCount = 0; birthDayCount <= yearOld; birthDayCount++)
        {
            LocalDate nextBirthDate = birthDate.plusYears(birthDayCount);
            System.out.println(birthDayCount + " - " + dateFormat.format(nextBirthDate));
        }
    }
}
