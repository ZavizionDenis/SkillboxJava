import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    private static long size;
    public static void main(String[] args) {
        System.out.println("ввведите путь к папке:");
        String srcPath = new Scanner(System.in).nextLine();
        File scanFolder = new File(srcPath);
        if (!scanFolder.exists()) {
            System.out.println("Указанная папка не найдена.");
            System.exit(1);
        }
        else if (scanFolder.isFile()) {
            System.out.println("Введеный путь указывает на файл.");
            System.exit(2);
        }
        else {
            contentSize(scanFolder);
        }
        System.out.print(getFormatSize(size));
    }

    private static void contentSize (File nextFolder) {
        File [] contentList = nextFolder.listFiles();
        Arrays.stream(contentList).forEach(file -> {
            if (file.isFile()) {
                size += file.length();
            } else {
                contentSize(file);
            }
        });
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
