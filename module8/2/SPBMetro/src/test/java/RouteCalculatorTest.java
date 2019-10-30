import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteCalculatorTest extends TestCase
{
    List<Station> route1;
    List<Station> route2;
    List<Station> route3;

    StationIndex stationIndex;
    RouteCalculator calculator;

    @Override
    protected void setUp() throws Exception {

        stationIndex = new StationIndex();
        Line [] lines = new Line [3];
        Line green = new Line(1, "Зеленая");
        Line purple = new Line(2, "Фиолетовая");
        Line blue = new Line(3, "Синяя");

        Stream.of("Приморская", "Василеостровская", "Гостиный двор")
                .map(s -> new Station(s, green))
                .forEach(green::addStation);

        Stream.of("Чкаловская", "Спортивная", "Адмиралтейская", "Садовая")
                .map(s -> new Station(s, purple))
                .forEach(purple::addStation);

        Stream.of("Черная речка", "Петроградская", "Горьковская", "Невский проспект", "Сенная площадь")
                .map(s -> new Station(s, blue))
                .forEach(blue::addStation);

        lines[0] = green;
        lines[1] = purple;
        lines[2] = blue;

        Arrays.stream(lines)
                .peek(stationIndex::addLine)
                .map(Line::getStations)
                .flatMap(Collection::stream)
                .forEach(stationIndex::addStation);

        List<Station> connectionStations = new ArrayList<>();
        String [] crossStationsName = {"Невский проспект", "Гостиный двор", "Сенная Площадь", "Садовая"};
        for (int i = 0; i < crossStationsName.length; i += 2) {
            int crossStationNumLine1 = stationIndex.getStation(crossStationsName[i]).getLine().getNumber();
            Station crossStation1 = stationIndex.getStation(crossStationsName[i], crossStationNumLine1);
            int crossStationNumLine2 = stationIndex.getStation(crossStationsName[i + 1]).getLine().getNumber();
            Station crossStation2 = stationIndex.getStation(crossStationsName[i + 1], crossStationNumLine2);
            connectionStations.add(crossStation1);
            connectionStations.add(crossStation2);
            stationIndex.addConnection(connectionStations);
            connectionStations.clear();
        }

        calculator = new RouteCalculator(stationIndex);

//==================================  for testCalculateDuration =======================================================
        route1 = Stream.of(new Station("Черная речка", blue),
                new Station("Петроградская", blue),
                new Station("Горьковская", blue),
                new Station("Невский проспект", blue),
                new Station("Сенная площадь", blue)).collect(Collectors.toList());

        route2 = Stream.of(new Station("Приморская", green),
                new Station("Василеостровская", green),
                new Station("Гостиный двор", green),
                new Station("Невский проспект", blue),
                new Station("Горьковская", blue)).collect(Collectors.toList());

        route3 = Stream.of(new Station("Чкаловская", purple),
                new Station("Спортивная", purple),
                new Station("Адмиралтейская", purple),
                new Station("Садовая", purple),
                new Station("Сенная площадь", blue),
                new Station("Невский проспект", blue),
                new Station("Гостиный двор", green),
                new Station("Василеостровская", green)).collect(Collectors.toList());
//---------------------------------------------------------------------------------------------------------------------

    }
//---------------------------------------------------------------------------------------------------------------------
    public void testGetShortestRoute () {
        String [] from = {"Черная речка", "Приморская", "Чкаловская"};
        String [] to = {"Сенная площадь", "Горьковская", "Василеостровская"};
        List <String []> routes = new ArrayList<>();
        routes.add(0, new String[]{"Черная речка", "Петроградская", "Горьковская", "Невский проспект", "Сенная площадь"});
        routes.add(1, new String[]{"Приморская", "Василеостровская", "Гостиный двор", "Невский проспект", "Горьковская"});
        routes.add(2, new String[]{"Чкаловская", "Спортивная", "Адмиралтейская", "Садовая", "Сенная площадь", "Невский проспект", "Гостиный двор", "Василеостровская"});
        for (int i = 0; i < from.length; i++) {
            List<String> actual = calculator.getShortestRoute(stationIndex.getStation(from[i]), stationIndex.getStation(to[i]))
                    .stream().map(Station::getName).collect(Collectors.toList());
            List <String> expected = Arrays.stream(routes.get(i)).collect(Collectors.toList());
            System.out.printf("Test name: %s -> Expected: %s\n Actual: %s\n", getName(), expected, actual);
            assertEquals(expected, actual);
        }
    }

    public void testCalculateDuration () {
        List [] routes = {route1, route2, route3};
        double [] expecteds = {10.0, 11.0, 19.5};
        for (int i = 0; i < routes.length; i++) {
            double actual = RouteCalculator.calculateDuration(routes[i]);
            System.out.printf("Test name: %s -> Expected: %.1f, Actual: %.1f\n", getName(), expecteds[i], actual);
            assertEquals(expecteds[i], actual);
        }
    }

    public void testGetRouteOnTheLine () {
        List<String> actual1 = calculator.getGetRouteOnTheLine(stationIndex.getStation("Черная речка"), stationIndex.getStation("Сенная площадь"))
                .stream().map(Station::getName).collect(Collectors.toList());
        List <String> expected1 = Stream.of("Черная речка", "Петроградская", "Горьковская", "Невский проспект", "Сенная площадь").collect(Collectors.toList());
        System.out.printf("Test name: %s -> Expected: %s\n Actual: %s\n", getName(), expected1, actual1);
        assertEquals(expected1, actual1);

        String [] stationFrom = {"Приморская", "Чкаловская"};
        String [] stationTo = {"Горьковская", "Василеостровская"};
        for (int i = 0; i< stationFrom.length; i++) {
            List<Station> actual2 = calculator.getGetRouteOnTheLine(stationIndex.getStation(stationFrom[i]), stationIndex.getStation(stationTo[i]));
            System.out.printf("Test name: %s -> Expected: %s\n Actual: %s\n", getName(), null, actual2);
            assertNull(actual2);
        }
    }

    public void testGetRouteWithOneConnection () {
        List<String> actual1 = calculator.getGetRouteWithOneConnection(stationIndex.getStation("Приморская"), stationIndex.getStation("Горьковская"))
                .stream().map(Station::getName).collect(Collectors.toList());
        List <String> expected1 = Stream.of("Приморская", "Василеостровская", "Гостиный двор", "Невский проспект", "Горьковская").collect(Collectors.toList());
        System.out.printf("Test name: %s -> Expected: %s\n Actual: %s\n", getName(), expected1, actual1);
        assertEquals(expected1, actual1);

        List<Station> actual2 = calculator.getGetRouteWithOneConnection(stationIndex.getStation("Черная речка"), stationIndex.getStation("Сенная площадь"));
        System.out.printf("Test name: %s -> Expected: %s\n Actual: %s\n", getName(), null, actual2);
        assertNull(actual2);

        List<Station> actual3 = calculator.getGetRouteWithOneConnection(stationIndex.getStation("Чкаловская"), stationIndex.getStation("Василеостровская"));
        System.out.printf("Test name: %s -> Expected: %d\n Actual: %d\n", getName(), 0, actual3.size());
        assertEquals(0,actual3.size());
    }

    public void testGetRouteWithTwoConnections () {
        List<String> actual1 = calculator.getGetRouteWithTwoConnections(stationIndex.getStation("Чкаловская"), stationIndex.getStation("Василеостровская"))
                .stream().map(Station::getName).collect(Collectors.toList());
        List <String> expected1 = Stream.of("Чкаловская", "Спортивная", "Адмиралтейская", "Садовая", "Сенная площадь", "Невский проспект", "Гостиный двор", "Василеостровская").collect(Collectors.toList());
        System.out.printf("Test name: %s -> Expected: %s\n Actual: %s\n", getName(), expected1, actual1);
        assertEquals(expected1, actual1);

        List<Station> actual2 = calculator.getGetRouteWithTwoConnections(stationIndex.getStation("Черная речка"), stationIndex.getStation("Сенная площадь"));
        System.out.printf("Test name: %s -> Expected: %s\n Actual: %s\n", getName(), null, actual2);
        assertNull(actual2);

        List<Station> actual3 = calculator.getGetRouteWithTwoConnections(stationIndex.getStation("Приморская"), stationIndex.getStation("Горьковская"));
        System.out.printf("Test name: %s -> Expected: %d\n Actual: %d\n", getName(), 0, actual3.size());
        assertEquals(0,actual3.size());
    }

    public void testIsConnected () {
        String [] stationFrom = {"Невский проспект", "Сенная площадь", "Приморская", "Невский проспект"};
        String [] stationTo = {"Гостиный двор", "Садовая", "Горьковская", "Приморская"};
        for (int i = 0; i< stationFrom.length; i++) {
            Boolean actual = calculator.getIsConnected(stationIndex.getStation(stationFrom[i]), stationIndex.getStation(stationTo[i]));
            if (i < 2) {
                System.out.printf("Test name: %s -> Expected: %b, Actual: %b\n", getName(), true, actual);
                assertTrue(actual);
            }
            else {
                System.out.printf("Test name: %s -> Expected: %b, Actual: %b\n", getName(), false, actual);
                assertFalse(actual);
            }
        }
    }

    public void testGetRouteViaConnectedLine () {
        List<String> actual1 = calculator.getGetRouteViaConnectedLine(stationIndex.getStation("Гостиный двор"), stationIndex.getStation("Садовая"))
                .stream().map(Station::getName).collect(Collectors.toList());
        List <String> expected1 = Stream.of("Невский проспект", "Сенная площадь").collect(Collectors.toList());
        System.out.printf("Test name: %s -> Expected: %s\n Actual: %s\n", getName(), expected1, actual1);
        assertEquals(expected1, actual1);

        String [] stationFrom = {"Невский проспект", "Приморская"};
        String [] stationTo = {"Сенная площадь", "Чкаловская"};
        for (int i = 0; i < stationFrom.length; i++) {
            List<Station> actual = calculator.getGetRouteViaConnectedLine(stationIndex.getStation(stationFrom[i]), stationIndex.getStation(stationTo[i]));
            System.out.printf("Test name: %s -> Expected: %s Actual: %s\n", getName(), null, actual);
            assertNull(actual);
        }
    }

    @Override
    protected void tearDown() throws Exception {
    }
}