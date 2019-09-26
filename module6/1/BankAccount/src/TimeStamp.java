import java.time.LocalDate;

public class TimeStamp {

    private static LocalDate timeStamp;

    private TimeStamp() {
    }

    public static LocalDate getTimeStamp () {
        if (timeStamp == null) {
            timeStamp = LocalDate.now();
        }
        return timeStamp;
    }

    public static void addDays(int days) {
        timeStamp = timeStamp.plusDays(days);
    }
}