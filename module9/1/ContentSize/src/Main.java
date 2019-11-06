import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        System.out.println("ввведите путь к папке:");
        String srcPath = new Scanner(System.in).nextLine();
        Path scanFolder = Paths.get(srcPath);

        if (Files.isDirectory(scanFolder) && scanFolder.getFileName() != null) {
            MyFileVisitor fileVisitor = new MyFileVisitor();
            try {
                Files.walkFileTree(scanFolder, fileVisitor);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(getFormatSize(fileVisitor.getSize()));
        }
        else {
            System.out.println("Не корректный путь к папке.");
        }
    }

    private static String getFormatSize(Long size) {
        final int K_BYTES = 1000;
        String [] categorys = {"Т0.000_000_000_001", "Г0.000_000_001", "М0.000_001", "К0.001"};
        if (size < K_BYTES) {
            return String.format("Размер: %,d Байт(а)", size);
        }
        else {
            double splitter = 0.0;
            String category = "";
            for (int i = 0; i < categorys.length; i++) {
                splitter = Double.parseDouble(categorys[i].replaceAll("[^\\d\\.]+", ""));
                if (size * splitter > 1) {
                    category = categorys[i].replaceAll("[\\d_\\.]+", "");
                    break;
                }
            }
            double normalizeSize = size * splitter;
            return String.format("Размер: %.2f %sбайт(а) или %,d Байт(а)", normalizeSize, category, size);
        }
    }
}
