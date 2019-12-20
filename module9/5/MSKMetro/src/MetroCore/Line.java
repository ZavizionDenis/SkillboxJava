package MetroCore;

import java.util.ArrayList;

public class Line {
    private String number;
    private String name;
    private String color;
    private ArrayList<Station> stations;

    public Line(String number, String name, String color) {
        this.number = number;
        this.name = name;
        this.color = color;
        stations = new ArrayList<>();
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public void addStationToLine (Station station) {
        stations.add(station);
    }

    @Override
    public String toString() {
        return number + "-" + name + "-" + color + "=>" + stations + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        return number.equals(line.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }
}