import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.util.Date;

public class Loader
{
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        airport.getTerminals().forEach(terminal -> terminal.getFlights().stream()
                .filter(Loader::isNeededFlight).forEach(System.out::println));
    }


    private static Boolean isNeededFlight (Flight flight) {
        final long TWO_HOURS = 2 * 3600 * 1000;
        Date currentTime = new Date(System.currentTimeMillis());
        Date finishDepartureTime = new Date(System.currentTimeMillis() + TWO_HOURS);
        return flight.getType().equals(Flight.Type.DEPARTURE) &
                (flight.getDate().after(currentTime) & flight.getDate().before(finishDepartureTime));

    }

}
