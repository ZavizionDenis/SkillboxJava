import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.util.Date;

public class Loader
{
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        airport.getTerminals().stream()
                .flatMap(terminal -> terminal.getFlights().stream()
                        .filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE))
                        .filter(Loader::isDepartureWithin2Hours))
                .forEach(System.out::println);
    }


    private static Boolean isDepartureWithin2Hours (Flight flight) {
        final long TWO_HOURS = 2 * 3600 * 1000;
        Date currentTime = new Date();
        Date finishDepartureTime = new Date(System.currentTimeMillis() + TWO_HOURS);
        return flight.getDate().after(currentTime) & flight.getDate().before(finishDepartureTime);

    }

}
