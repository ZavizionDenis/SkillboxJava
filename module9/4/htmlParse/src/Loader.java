import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;

public class Loader {
    private static final String IMAGE_VALID = ".+\\.(?i)(png|jpg|gif|ico)$";
    static private String[] tags = {"img", "meta", "link"};
    static private String[] attributes = {"src", "content", "href"};
    static private String address = "https://lenta.ru";

    public static void main(String[] args) {
        Path outputPath = Paths.get("downloads/");
        if (!Files.exists(outputPath)) {
            try {
                Files.createDirectory(outputPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        getUrlsList().forEach(System.out::println);
        downloadFiles(getUrlsList(), outputPath);
    }

    private static HashSet<String> getUrlsList () {
        HashSet<String> urls = new HashSet<>();
        try {
            Document doc = Jsoup.parse(new URL(address), 10000);

            Arrays.stream(tags).forEach(tag -> {
                Elements elements = doc.select(tag);
                elements.forEach(element -> Arrays.stream(attributes)
                        .filter(attribute -> !element.attr(attribute).isEmpty() && element.attr(attribute).matches(IMAGE_VALID))
                        .forEach(attribute -> urls.add(element.attr(attribute))));
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }

    private static void downloadFiles (HashSet<String> urls, Path outputPath) {
        for (String url : urls) {
            Path fileName = Paths.get(url).getFileName();
            try {
                ReadableByteChannel inputByteStream = Channels.newChannel(new URL(url).openStream());
                FileOutputStream fileOutStream = new FileOutputStream(outputPath.toString() + "/" + fileName.toString());
                fileOutStream.getChannel().transferFrom(inputByteStream, 0, Long.MAX_VALUE);
                fileOutStream.close();
                inputByteStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}