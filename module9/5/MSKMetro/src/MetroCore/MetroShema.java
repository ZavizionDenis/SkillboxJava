package MetroCore;

import java.util.ArrayList;

public class MetroShema {

    private ArrayList<Line> lines;
    private ArrayList<Station> stations;

    public MetroShema() {
        lines = new ArrayList<>();
        stations = new ArrayList<>();
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public void setStations(ArrayList<Station> stations) {
        this.stations = stations;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "MetroShema{" +
                "lines=" + lines +
                '}';
    }
}