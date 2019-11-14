import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor extends SimpleFileVisitor <Path>
{
    private Path srcPath;
    private Path destPath;

    MyFileVisitor(Path srcPath, Path destPath) {
        this.srcPath = srcPath;
        this.destPath = destPath;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
        Path newdestPath = destPath.resolve(srcPath.relativize(path));
        try {
            Files.copy(path, newdestPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            return showMessageAndSkip(path, e);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) {
        Path newdestPath = destPath.resolve(srcPath.relativize(path));
        if (!Files.exists(newdestPath)) {
            try {
                Files.copy(path, newdestPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                return showMessageAndSkip(path, e);
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path path, IOException e) {
        return showMessageAndSkip(path, e);
    }

    private FileVisitResult showMessageAndSkip(Path path, Exception e) {
        System.out.println("Не удалось скопировать: " + path + " " + e.toString());
        return FileVisitResult.SKIP_SUBTREE;
    }
}
