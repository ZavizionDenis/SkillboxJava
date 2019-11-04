import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteCalculatorTest extends TestCase
{
    StationIndex stationIndex;
    RouteCalculator calculator;

    @Override
    protected void setUp() throws Exception {

        stationIndex = new StationIndex();
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

        List<Line> lines = Arrays.asList(green, purple, blue);
        lines.stream()
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
    }
//=====================================================================================================================
    public void testGetEmptyStation () {
         Station actual = stationIndex.getStation("Ижевская");
         assertNull(actual);
    }
//---------------------------------------------------------------------------------------------------------------------
    private List <Station> getActualForRoutTests (String fromStation, String toStation) {
        return calculator.getShortestRoute(stationIndex.getStation(fromStation), stationIndex.getStation(toStation));
    }

    public void testGetShortestRouteSameLine () {
        List<Station> actual = getActualForRoutTests("Черная речка", "Сенная площадь");
        List<Station> expected = Stream.of("Черная речка", "Петроградская", "Горьковская", "Невский проспект", "Сенная площадь")
                .map(s -> stationIndex.getStation(s)).collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    public void testGetShortestRouteOneCross () {
        List<Station> actual = getActualForRoutTests("Приморская", "Горьковская");
        List<Station> expected = Stream.of("Приморская", "Василеостровская", "Гостиный двор", "Невский проспект", "Горьковская")
                .map(s -> stationIndex.getStation(s)).collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    public void testGetShortestRouteTwoCross () {
        List<Station> actual = getActualForRoutTests("Чкаловская","Василеостровская");
        List<Station> expected = Stream.of("Чкаловская", "Спортивная", "Адмиралтейская", "Садовая", "Сенная площадь", "Невский проспект", "Гостиный двор", "Василеостровская")
                .map(s -> stationIndex.getStation(s)).collect(Collectors.toList());
        assertEquals(expected, actual);
    }
//---------------------------------------------------------------------------------------------------------------------
    public void testCalculateDurationSameLine () {
        List <Station> route = Stream.of("Черная речка", "Петроградская", "Горьковская", "Невский проспект", "Сенная площадь")
                .map(s -> stationIndex.getStation(s)).collect(Collectors.toList());
        double actual = RouteCalculator.calculateDuration(route);
        assertEquals(10.0, actual);
    }

    public void testCalculateDurationOneCross () {
        List <Station> route = Stream.of("Приморская", "Василеостровская", "Гостиный двор", "Невский проспект", "Горьковская")
                .map(s -> stationIndex.getStation(s)).collect(Collectors.toList());
        double actual = RouteCalculator.calculateDuration(route);
        assertEquals(11.0, actual);
    }

    public void testCalculateDurationTwoCross () {
        List <Station> route = Stream.of("Чкаловская", "Спортивная", "Адмиралтейская", "Садовая", "Сенная площадь", "Невский проспект", "Гостиный двор", "Василеостровская")
                .map(s -> stationIndex.getStation(s)).collect(Collectors.toList());
        double actual = RouteCalculator.calculateDuration(route);
        assertEquals(19.5, actual);
    }
//---------------------------------------------------------------------------------------------------------------------
    private List <Station> getActualForRouteOnTheLineTests (String fromStation, String toStation) {
        return calculator.getGetRouteOnTheLine(stationIndex.getStation(fromStation), stationIndex.getStation(toStation));
    }

    public void testGetRouteOnTheLine () {
        List<Station> actual = getActualForRouteOnTheLineTests("Черная речка", "Сенная площадь");
        List<Station> expected = Stream.of("Черная речка", "Петроградская", "Горьковская", "Невский проспект", "Сенная площадь")
                .map(s -> stationIndex.getStation(s)).collect(Collectors.toList());
        assertEquals(expected, actual);
    }
    public void testGetRouteOnTheLineOneCross () {
        List<Station> actual = getActualForRouteOnTheLineTests("Приморская", "Горьковская");
        assertNull(actual);
    }

    public void testGetRouteOnTheLineTwoCross () {
        List<Station> actual = getActualForRouteOnTheLineTests("Чкаловская", "Василеостровская");
        assertNull(actual);
    }
//---------------------------------------------------------------------------------------------------------------------
    private List <Station> getActualForOneConnectionTests (String fromStation, String toStation) {
        return calculator.getGetRouteWithOneConnection(stationIndex.getStation(fromStation), stationIndex.getStation(toStation));
    }

    public void testGetRouteWithOneConnection () {
        List<Station> actual = getActualForOneConnectionTests("Приморская", "Горьковская");
        List<Station> expected = Stream.of("Приморская", "Василеостровская", "Гостиный двор", "Невский проспект", "Горьковская")
                .map(s -> stationIndex.getStation(s)).collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    public void testGetRouteWithOneConnectionSameLine () {
            List<Station> actual = getActualForOneConnectionTests("Черная речка", "Сенная площадь");
            assertNull(actual);
    }

    public void testGetRouteWithOneConnectionTwoCross () {
        List<Station> actual = getActualForOneConnectionTests("Чкаловская", "Василеостровская");
        assertEquals(0, actual.size());
    }
//---------------------------------------------------------------------------------------------------------------------
    private List <Station> getActualForTwoConnectionTests (String fromStation, String toStation) {
        return calculator.getGetRouteWithTwoConnections(stationIndex.getStation(fromStation), stationIndex.getStation(toStation));
    }

    public void testGetRouteWithTwoConnections () {
        List<Station> actual = getActualForTwoConnectionTests("Чкаловская", "Василеостровская");
        List <Station> expected = Stream.of("Чкаловская", "Спортивная", "Адмиралтейская", "Садовая", "Сенная площадь", "Невский проспект", "Гостиный двор", "Василеостровская")
                .map(s -> stationIndex.getStation(s)).collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    public void testGetRouteWithTwoConnectionsSameLine () {
        List<Station> actual = getActualForTwoConnectionTests("Черная речка", "Сенная площадь");
        assertNull(actual);
    }

    public void testGetRouteWithTwoConnectionsOneCross () {
        List<Station> actual = getActualForTwoConnectionTests("Приморская", "Горьковская");
        assertEquals(0, actual.size());
    }
//---------------------------------------------------------------------------------------------------------------------
    private boolean getActualForTestIsConnected (String fromStation, String toStation) {
        return calculator.getIsConnected(stationIndex.getStation(fromStation), stationIndex.getStation(toStation));
    }

    public void testIsConnectedOne () {
        Boolean actual = getActualForTestIsConnected("Невский проспект", "Гостиный двор");
        assertTrue(actual);
    }

    public void testIsConnectedTwo () {
        Boolean actual = getActualForTestIsConnected("Сенная площадь", "Садовая");
        assertTrue(actual);
    }

    public void testNotConnectedOne () {
        Boolean actual = getActualForTestIsConnected("Приморская", "Горьковская");
        assertFalse(actual);
    }

    public void testNotConnectedTwo () {
        Boolean actual = getActualForTestIsConnected("Невский проспект", "Приморская");
        assertFalse(actual);
    }
//---------------------------------------------------------------------------------------------------------------------
    private List <Station> getActualForRouteViaConnectedTests (String fromStation, String toStation) {
        return calculator.getGetRouteViaConnectedLine(stationIndex.getStation(fromStation), stationIndex.getStation(toStation));
    }

    public void testGetRouteViaConnectedLine () {
        List<Station> actual = getActualForRouteViaConnectedTests("Гостиный двор", "Садовая");
        List <Station> expected = Stream.of("Невский проспект", "Сенная площадь").map(s -> stationIndex.getStation(s)).collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    public void testGetRouteNotConnectedLine () {
        List<Station> actual = getActualForRouteViaConnectedTests("Приморская", "Чкаловская");
        assertNull(actual);
    }

    public void testGetRouteConnectedOnSameLine () {
        List<Station> actual = getActualForRouteViaConnectedTests("Невский проспект", "Сенная площадь");
        assertNull(actual);
    }
//---------------------------------------------------------------------------------------------------------------------
    @Override
    protected void tearDown() throws Exception {
    }
}