package MetroCore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinesColorName {

    private static Map<String, String> colorMap;

    private LinesColorName() {
    }

    public static String getcolorName (String colorCode) {
        if (colorMap == null) {
            colorMap = loadColorMap();
        }
        return colorMap.get(colorCode);
    }

    private static HashMap<String, String> loadColorMap () {
        String srcFile = "res/ColorNames.txt";
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(srcFile));
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла ColorNames.txt. Цвета линий будут отсутсвовать");
        }
        HashMap<String, String> map = new HashMap<>();
        for (String line : lines) {
            String[] string = line.split("=");
            map.put(string[1], string[0]);
        }
        return map;
    }
}
