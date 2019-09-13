import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader
{
    private static final String CAR_NUM_VALID = "^[АВЕКМНОРСТУХ]{1}[0-9]{3}[АВЕКМНОРСТУХ]{2}[0-9]{2,3}$";
    private static final String CODE_TO_EXIT = "0";

    private static NiceNumArray carNumberArrayList = new NiceNumArray();

    public static void main(String[] args) {

        Pattern chekCarNumber = Pattern.compile(CAR_NUM_VALID);

        for (; ;) {
            System.out.println("Введите номер для поиска, чтобы выйти наберите 0 и нажмите Enter:");
            String number = new Scanner(System.in).nextLine().toUpperCase().trim();

            Matcher matcher = chekCarNumber.matcher(number);

            if (number.equals(CODE_TO_EXIT)) {
                break;
            }

            else if (matcher.matches()) {
                incrementalSearch(number);
                binarySearch(number);
                hashSetSearch(number);
                treeSetSearch(number);
            }

            else {
                System.out.println("Вы не верно ввели номер, он должен соответсвовать формату Х000ХХRR(RRR), где:" +
                        "\nX и XX - допустимые РУС буквы \"А\", \"В\", \"Е\", \"К\", \"М\", \"Н\", \"О\", \"Р\", \"С\", \"Т\", \"У\", \"Х\"" +
                        "\n000 - любые три цифры" +
                        "\nRR(RRR) - цифровой двух или трех значный код региона РФ\n");
            }
        }
    }
//=====================================================================================================================
    private static void incrementalSearch (String number) {
        long  startTime = System.currentTimeMillis();
        Boolean isFind = carNumberArrayList.GetContains(number);
        resultSearch(isFind, startTime);
    }

    private static void binarySearch (String number) {
        long startTime = System.currentTimeMillis();
        Boolean isFind = carNumberArrayList.GetBinarySearch(number);
        resultSearch(isFind, startTime);
    }

    private static void hashSetSearch (String number) {
        long startTime = System.currentTimeMillis();
        Boolean isFind = carNumberArrayList.GetHashSetSearch(number);
        resultSearch(isFind, startTime);
    }

    private static void treeSetSearch (String number) {
        long startTime = System.currentTimeMillis();
        Boolean isFind = carNumberArrayList.GetTreeSetSearch(number);
        resultSearch(isFind, startTime);
    }

    private static void resultSearch (Boolean isFind, long startTime) {
        if (isFind) {
            long duration = (System.currentTimeMillis() - startTime);
            System.out.println("true" + " (" + duration + " ms)");
        }
        else {
            long duration = (System.currentTimeMillis() - startTime);
            System.out.println("false" + " (" + duration + " ms)");
        }
    }
}