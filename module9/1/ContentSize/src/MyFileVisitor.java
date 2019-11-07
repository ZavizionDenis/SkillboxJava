import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor extends SimpleFileVisitor <Path>
{
    private long size;

    public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
        size += fileAttributes.size();
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult visitFileFailed(Path path, IOException e) throws IOException {
        System.out.println("Ошибка доступа к " + path + " Причина: " + e.getMessage());
        return FileVisitResult.SKIP_SUBTREE;
    }
    public long getSize() {
        return size;
    }
}
