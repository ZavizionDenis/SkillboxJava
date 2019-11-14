import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MCCCodeBase
{
    private String mccCode;
    private String mccName;
    private String mccGroup;

    private static HashMap<String, MCCCodeBase> mccCodeBases;

    private MCCCodeBase (String mccCode, String mccName, String mccGroup) {
        this.mccCode = mccCode;
        this.mccName = mccName;
        this.mccGroup = mccGroup;
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
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(srcFile));
        } catch (IOException e) {
            System.out.println("Ошибка чтения базы МСС кодов.");
        }
        for (String line : lines) {
            String[] fragmentsLine = line.split(";");
            if (fragmentsLine.length == 3) {
                mccCodeBases.put(fragmentsLine[0], new MCCCodeBase(fragmentsLine[0], fragmentsLine[1], fragmentsLine[2]));
            }
            else {
                mccCodeBases.put(fragmentsLine[0], new MCCCodeBase(fragmentsLine[0], fragmentsLine[1], "Прочие"));
            }
        }
        return mccCodeBases;
    }

    public String getMccName() {
        return mccName;
    }
}
