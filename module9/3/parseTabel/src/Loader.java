import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Loader
{
    private static HashMap<String, MCCCodeBase> mccCodeBases;
    private static int errorCount;

    public static void main(String[] args) {
        mccCodeBases = new HashMap<>(MCCCodeBase.getMCCCodeBase());
        List <Trasaction> trasactionList = loadMovementList();
        System.out.printf("Общая сумма поступлений: %,.2f рублей.%n", getTotalMoneyIn(trasactionList));
        System.out.printf("Общая сумма расходов: %,.2f рублей.%n", getTotalMoneyOut(trasactionList));
        System.out.print("Отчет по расходам:\n========================\n");
        getReportMoneyOut(trasactionList).forEach(((String s, Double aDouble) -> System.out.println(s + " " + aDouble)));
        System.out.print("========================\n");
        System.out.println("Ошибок определения статьи расходов: " + errorCount);
    }

    private static List <Trasaction> loadMovementList () {
        final String LINE_VALID = "^(?<type>[А-Яа-я]*\\s*счёт)," +
                "(?<number>\\d{20})," +
                "(?<currency>\\w{3})," +
                "(?<operationDate>\\d{2}\\.\\d{2}\\.\\d{2})," +
                "(?<referTrans>.+)," +
                "(?<operationDesc>.+)," +
                "(?<moneyIn>\"\\d+,\\d+\"|\\d+)," +
                "(?<moneyOut>\"\\d+,\\d+\"|\\d+)$";
        String srcPath = "resource/movementList.csv";
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(srcPath));
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + srcPath);
            System.exit(1);
        }
        ArrayList <Trasaction> trasactionList = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            Matcher matcher = Pattern.compile(LINE_VALID).matcher(lines.get(i));
            if (matcher.matches()) {
                trasactionList.add(parseLineToTrasaction(matcher));
            }
            else {
                System.out.printf("Запись номер: %d из файла не распознана!%n", i + 1);
            }
        }
        return trasactionList;
    }

    private static Trasaction parseLineToTrasaction (Matcher matcher) {
        String type = matcher.group("type");
        String number = matcher.group("number");
        String currency = matcher.group("currency");
        Date operationDate = null;
        try {
            operationDate = (new SimpleDateFormat("dd.MM.yy")).parse(matcher.group("operationDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String referTrans = matcher.group("referTrans");
        String operationDesc = matcher.group("operationDesc");
        double moneyIn = Double.parseDouble(matcher.group("moneyIn").replaceAll("[^\\d,]","").replace(",","."));
        double moneyOut = Double.parseDouble(matcher.group("moneyOut").replaceAll("[^\\d,]","").replace(",","."));
        return new Trasaction(type, number, currency, operationDate, referTrans, operationDesc, moneyIn, moneyOut);
    }

    private static double getTotalMoneyIn(List<Trasaction> transactionList) {
        return transactionList.stream().mapToDouble(Trasaction::getMoneyIn).sum();
    }

    private static double getTotalMoneyOut(List<Trasaction> transactionList) {
        return transactionList.stream().mapToDouble(Trasaction::getMoneyOut).sum();
    }

    private static TreeMap <String, Double> getReportMoneyOut (List <Trasaction> transactionList) {
        List <Trasaction> outOperationList = transactionList.stream()
                .filter(trasaction -> trasaction.getMoneyOut() > 0)
                .collect(Collectors.toList());
        TreeMap <String, Double> reportMoneyOut = new TreeMap<>();
        for (Trasaction trasaction : outOperationList) {
            Double money = trasaction.getMoneyOut();
            String description = parseDescription(trasaction.getOperationDesc());
            if (description.contains("Ошибка определения статьи расходов")) {
                errorCount += 1;
            }
            Double moneyOut = reportMoneyOut.get(description);
            if (moneyOut == null) {
                moneyOut = 0.0;
            }
            reportMoneyOut.put(description, moneyOut + money);
        }
        return reportMoneyOut;
    }

    private static String parseDescription (String description) {
        final String VALIDATOR = "\\s{2,}(?<desc>.+)\\s{2,}\\d{2}\\.\\d{2}\\.\\d{2}.+\\s+(?<mcc>MCC\\d{4})";
        Matcher matcher = Pattern.compile(VALIDATOR).matcher(description);
        return matcher.find() ? getMCCName(matcher) + " " + getDescName(matcher) : "Ошибка определения статьи расходов";
    }

    private static String getDescName (Matcher matcher) {
        String desc = matcher.group("desc");
        if (desc.lastIndexOf("/") > 0) {
            desc = desc.substring(desc.lastIndexOf("/") + 1);
        }
        if (desc.lastIndexOf("\\") > 0) {
            desc = desc.substring(desc.lastIndexOf("\\") + 1);
        }
        return desc.trim();
    }

    private static String getMCCName (Matcher matcher) {
        String mccCode = matcher.group("mcc").replaceAll("[^\\d]","").trim();
        return mccCodeBases.containsKey(mccCode) ? String.format("(MCC%s - %s)", mccCode, mccCodeBases.get(mccCode).getMccName()) : "(МСС код операции не найден в базе)";
    }
}