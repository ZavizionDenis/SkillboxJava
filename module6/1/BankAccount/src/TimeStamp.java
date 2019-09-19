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
}