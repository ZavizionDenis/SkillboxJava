<<<<<<< HEAD

=======
>>>>>>> 873a9d171a1c18e61906c17c8f889ab4c42e675f
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
<<<<<<< HEAD
}
=======

    public static void addDays(int days) {
        timeStamp = timeStamp.plusDays(days);
    }
}
>>>>>>> 873a9d171a1c18e61906c17c8f889ab4c42e675f
