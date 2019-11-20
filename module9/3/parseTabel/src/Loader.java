import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Loader
{
    private static HashMap<String, MCCCodeBase> mccCodeBases;

    public static void main(String[] args) {
        mccCodeBases = MCCCodeBase.getMCCCodeBase();
        List <Transaction> trasactionList = loadMovementList();
        System.out.printf("Общая сумма поступлений: %,.2f рублей.%n", getTotalMoneyIn(trasactionList));
        System.out.printf("Общая сумма расходов: %,.2f рублей.%n", getTotalMoneyOut(trasactionList));
        System.out.print("Отчет по расходам:\n========================\n");
        getReportMoneyOut(trasactionList);
        System.out.print("========================\n");
    }

    private static List <Transaction> loadMovementList () {
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
        ArrayList <Transaction> trasactionList = new ArrayList<>();
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

    private static Transaction parseLineToTrasaction (Matcher matcher) {
        String operationDesc = matcher.group("operationDesc");
        double moneyIn = stringToDouble(matcher.group("moneyIn"));
        double moneyOut = stringToDouble(matcher.group("moneyOut"));
        return new Transaction(operationDesc, moneyIn, moneyOut);
    }

    private static double stringToDouble (String string) {
        return Double.parseDouble(string.replaceAll("[^\\d,]","").replace(",","."));
    }

    private static double getTotalMoneyIn(List<Transaction> transactionList) {
        return transactionList.stream().mapToDouble(Transaction::getMoneyIn).sum();
    }

    private static double getTotalMoneyOut(List<Transaction> transactionList) {
        return transactionList.stream().mapToDouble(Transaction::getMoneyOut).sum();
    }

    private static void getReportMoneyOut (List <Transaction> transactionList) {
        transactionList.stream()
                .collect(Collectors.groupingBy(transaction -> parseDescription(transaction.getOperationDesc()),
                        Collectors.mapping(Summary::fromTransaction,
                                Collectors.reducing(Summary::merge))))
                .forEach((s, summary) -> System.out.println(s + "\t\t" + summary.get().getIncome() + "\t" + summary.get().getWithdraw()));
    }

    private static String parseDescription (String description) {
        final String VALIDATOR = "\\s{2,}(?<desc>.+)\\s{2,}\\d{2}\\.\\d{2}\\.\\d{2}.+\\s+(?<mcc>MCC\\d{4})";
        Matcher matcher = Pattern.compile(VALIDATOR).matcher(description);
        return matcher.find() ? getMCCName(matcher) + " " + getDescName(matcher) : "Ошибка определения статьи расходов";
    }

    private static String getDescName (Matcher matcher) {
        String desc = matcher.group("desc");
        return desc.replaceFirst(".*(\\/|\\\\)","").trim();
    }

    private static String getMCCName (Matcher matcher) {
        String mccCode = matcher.group("mcc").replaceAll("[^\\d]","").trim();
        return Optional.ofNullable(mccCodeBases.get(mccCode))
                .map(mccCodeBase -> String.format("(MCC%s - %s)", mccCode, mccCodeBases.get(mccCode).getMccName()))
                .orElse("(МСС код операции не найден в базе)");
    }
}