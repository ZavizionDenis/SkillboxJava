import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Loader {
    public static void main(String[] args) throws IOException {

        System.out.println("Введите путь к исходной папке:");
        Path srcFolder = getPath();

        if (!Files.isDirectory(srcFolder)) {
            System.out.println("Указанный путь не являются папкой.");
            System.exit(1);
        }

        System.out.println("Введите путь к папке назначения:");
        Path destFolder = getPath();

        if (!Files.exists(destFolder)) {
            System.out.println("Папка назначения: " + destFolder.getFileName() + " не существует и будет создана.");
            Files.createDirectory(destFolder);
        }

        if (Files.isSameFile(srcFolder, destFolder)) {
            System.out.println(" исходная папка и папка назначения совпадают.");
            System.exit(1);
        }

        Path newDestFolder = Paths.get(destFolder.toString(), srcFolder.getFileName().toString());

        try {
            Files.walkFileTree(srcFolder, new MyFileVisitor(srcFolder, newDestFolder));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//---------------------------------------------------------------------------------------------------------------------
    private static Path getPath() {
        String path = new Scanner(System.in).nextLine();
        return Paths.get(path);
    }
}