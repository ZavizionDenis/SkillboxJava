import MetroCore.Line;
import MetroCore.LinesColorName;
import MetroCore.MetroShema;
import MetroCore.Station;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.String.*;

public class Main {
    private static final String SRC_URL = "https://ru.wikipedia.org/wiki/Список_станций_Московского_метрополитена";
    private static MetroShema metroShema;

    public static void main(String[] args) {

        metroShema = new MetroShema();

        Elements stationsTable = getStationsTable(getPageForParse());
        metroShema.setStations(parseStation(stationsTable));

        addedLinesToMetroShema();
        exportToJason();

        HashMap <String, Integer> stationsCountOnLine = getStationsCountOnLine();
        stationsCountOnLine.keySet()
                .forEach(lineNumber -> System.out.printf("Линия №: %s Кол-во станций: %d%n", lineNumber, stationsCountOnLine.get(lineNumber)));
    }

    private static Document getPageForParse () {
        try {
            return Jsoup.connect(SRC_URL).maxBodySize(0).get();
        } catch (IOException e) {
            System.out.println("Не удалось получить страницу: " + SRC_URL);
            e.printStackTrace();
        }
        return null;
    }

    private static Elements getStationsTable (Document document) {
        Element stationTable = document.select("table").get(3);
        return stationTable.select("tbody").select("tr");
    }

    private static ArrayList<Station> parseStation (Elements trTags) {
        ArrayList <Station> stations = new ArrayList<>();
        trTags.stream().skip(1).forEach(trTag -> {
            Element tdTagLineOfStation = trTag.select("td").get(0);
            int linesInRowCount = tdTagLineOfStation.select("span").size() > 3 ? 2 : 1;
            for (int i = 0; i < linesInRowCount; i++) {
                Line line = parseLine(tdTagLineOfStation, i);
                String name;
                if (trTag.select("td").get(1).select("span").isEmpty()) {
                    name = trTag.select("td").get(1).select("a").html();
                } else {
                    name = trTag.select("td").get(1).select("span").select("a").html();
                }
                Element tdTagConnection = trTag.select("td").get(3);
                ArrayList<String> connectionLinks = new ArrayList<>();
                if (!tdTagConnection.select("span").isEmpty()) {
                    connectionLinks = parseConnectionLinks(tdTagConnection);
                }
                stations.add(new Station(name, line, connectionLinks));
            }
        });
        return stations;
    }

    private static Line parseLine (Element tdTag, int iteration) {
        int numberSpanTag = 0;
        Matcher matcher = Pattern.compile("#[\\dA-Z]{6}").matcher(tdTag.attr("style"));
        if (iteration != 0) {
            numberSpanTag = 2;
            matcher = Pattern.compile("to\\(#[\\dA-Z]{6}").matcher(tdTag.attr("style"));
        }
        String color;
        if (matcher.find()) {
            color = LinesColorName.getcolorName(matcher.group().replace("to(", ""));
        } else {
            color = "Нет";
        }

        Elements spanTags = tdTag.select("span");
        String number = "";
        if (spanTags.get(numberSpanTag).html().matches("^\\d+[a-zA-Zа-яА-Я]?$")) {
                number = spanTags.get(numberSpanTag).html();
        }

        String name = "";
        if (spanTags.get(numberSpanTag + 1).attr("title").matches("\\D+")) {
            name = spanTags.get(numberSpanTag + 1).attr("title");
        }
        return new Line(number, name, color);
    }

    private static ArrayList<String> parseConnectionLinks (Element tdTagConnection) {
        ArrayList<String> connectionLinks = new ArrayList<>();
        Elements spanTags = tdTagConnection.select("span");
        for (int i = 0; i < spanTags.size(); i += 2) {
            String number = "";
            if (spanTags.get(i).html().matches("^\\d+[a-zA-Zа-яА-Я]?$")) {
                number = spanTags.get(i).html();
            }
            String name = "";
            if (spanTags.get(i + 1).attr("title").matches("\\D+")) {
                String connectionURL = spanTags.get(i + 1).select("a").attr("href");
                try {
                    Document document = Jsoup.connect("https://ru.wikipedia.org" + connectionURL).maxBodySize(0).get();
                    name = document.select("h1").html().replaceAll("\\(.+\\)","").trim();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connectionLinks.add(number + "=>" + name);
        }
        return connectionLinks;
    }

    private static void addedLinesToMetroShema() {
        ArrayList<Line> metroLines = new ArrayList<>();
        for (Station station : metroShema.getStations()) {
            Line line = station.getLine();
            if (metroLines.contains(line)) {
                metroLines.stream()
                        .filter(metroLine -> metroLine.equals(line))
                        .forEach(metroLine -> metroLine.addStationToLine(station));
            } else {
                line.addStationToLine(station);
                metroLines.add(line);
            }
        }
        metroShema.setLines(metroLines);
    }

    private static void exportToJason() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Stations", getStationsObject());
        jsonObject.put("Connections", getConnectionsArray());
        jsonObject.put("lines", getLinesArray());

        try (FileWriter writer = new FileWriter("res/MetroShema.json")) {
            writer.write(jsonObject.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getStationsObject () {
        JSONObject jsonStations = new JSONObject();
        metroShema.getLines().forEach(line -> {
            ArrayList<String> stationsNameOnLine = (ArrayList<String>) line.getStations().stream()
                    .map(Station::getName).collect(Collectors.toList());
            jsonStations.put(line.getNumber(), stationsNameOnLine);
        });
        return jsonStations;
    }

    private static JSONArray getLinesArray () {
        JSONArray linesArray = new JSONArray();
        metroShema.getLines().forEach(line -> {
            JSONObject jsonLine = new JSONObject();
            jsonLine.put("number", line.getNumber());
            jsonLine.put("name", line.getName());
            jsonLine.put("color", line.getColor());
            linesArray.add(jsonLine);
        });
        return linesArray;
    }

    private static JSONArray getConnectionsArray () {
        JSONArray connectionsArray = new JSONArray();
        ArrayList<ArrayList<String>> addedStations = new ArrayList<>();
        for (Station station : metroShema.getStations()) {
            ArrayList <String> connectionLinks = station.getConnectionLinks();
            if (connectionLinks.isEmpty()) {
                continue;
            }

            connectionLinks.add(station.getLine().getNumber() + "=>" + station.getName());
            if (checkConnection(addedStations, connectionLinks)) {
                continue;
            }

            JSONArray connectionSubArray = new JSONArray();
            connectionLinks.forEach(connectionLink -> {
                JSONObject jsonConnection = new JSONObject();
                jsonConnection.put("Line", connectionLink.substring(0, connectionLink.indexOf("=>")));
                jsonConnection.put("Station", connectionLink.substring(connectionLink.indexOf("=>") + 2).trim());
                connectionSubArray.add(jsonConnection);
            });

            addedStations.add(connectionLinks);
            connectionsArray.add(connectionSubArray);
        }
        return connectionsArray;
    }

    private static Boolean checkConnection (List<ArrayList<String>> addedStations, List<String> connectionLinks) {
        boolean isadded = false;
        for (ArrayList<String> addedConnection : addedStations) {
            for (String connectionLink : connectionLinks) {
                if (addedConnection.contains(connectionLink) && addedConnection.size() == connectionLinks.size()) {
                    isadded = true;
                } else {
                    isadded = false;
                    break;
                }
            }
            if (isadded) {
                return true;
            }
        }
        return isadded;
    }

    private static HashMap<String, Integer> getStationsCountOnLine () {
        JSONParser parser = new JSONParser();
        JSONObject jsonData = new JSONObject();
        try {
            jsonData = (JSONObject) parser.parse(readJsonFile());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONObject stationsObject = (JSONObject) jsonData.get("Stations");
        HashMap <String , Integer> map = (HashMap<String, Integer>) stationsObject.keySet().stream()
                .collect(Collectors.toMap(lineNumber -> lineNumber, lineNumber -> {
                    JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumber);
                    return stationsArray.size();
                }));
        return map;
    }

    private static String readJsonFile () {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get("res/MetroShema.json"));
            lines.forEach(stringBuilder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}