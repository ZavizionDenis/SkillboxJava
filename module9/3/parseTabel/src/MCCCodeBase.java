import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class MCCCodeBase
{
    private String mccName;

    private static HashMap<String, MCCCodeBase> mccCodeBases;

    private MCCCodeBase (String mccName) {
        this.mccName = mccName;
    }

    public static HashMap<String, MCCCodeBase> getMCCCodeBase () {
        if (mccCodeBases == null) {
            mccCodeBases = parseMCCcodeFile();
        }
        return mccCodeBases;
    }

    private static HashMap<String, MCCCodeBase> parseMCCcodeFile () {
        String srcFile = "resource/mcc-codes.csv";
        mccCodeBases = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(srcFile));
            for (String line : lines) {
                String[] fragmentsLine = line.split(";");
                mccCodeBases.put(fragmentsLine[0], new MCCCodeBase(fragmentsLine[1]));
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла базы МСС кодов: " + e.getMessage());
        }
        return mccCodeBases;
    }

    public String getMccName() {
        return mccName;
    }
}