package MetroCore;

import java.util.ArrayList;

public class Station {
    private String name;
    private Line line;
    private ArrayList<String> connectionLinks;

    public Station(String name, Line line, ArrayList<String> connectionLinks) {
        this.name = name;
        this.line = line;
        this.connectionLinks = connectionLinks;
    }

    public String getName() {
        return name;
    }

    public Line getLine() {
        return line;
    }

    public ArrayList<String> getConnectionLinks() {
        return connectionLinks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Station station = (Station) o;

        if (!name.equals(station.name)) return false;
        return line.equals(station.line);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + line.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name + " линия №: " + line.getNumber() + "(" + connectionLinks + ")" +"\n";
    }
}