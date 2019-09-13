import java.util.*;

public class NiceNumArray
{
    private static final int NUM_CODE_COUNT = 1000;
    private static final int MILLISEC_TO_SEC = 1000;
    private ArrayList<String> carNumberArrayList = new ArrayList<>();
    private HashSet <String> carNumberHashSet = new HashSet<>();
    private TreeSet<String> carNumberTreeSet = new TreeSet<>();

    public NiceNumArray ()
    {
        String regionCodes [] = {"01", "02", "102", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "113", "14", "15", "16", "116", "716", "17",
                "18", "19", "21", "121", "22", "23", "93", "123", "24", "84", "88", "124", "25", "125", "26", "126", "27", "28", "29", "30", "31", "32", "33",
                "34", "134", "35", "36", "136", "37", "38", "85", "138", "39", "91", "40", "41", "42", "142", "43", "44", "45", "46", "47", "147", "48", "49",
                "50", "90", "150", "190", "750", "51", "52", "152", "53", "54", "154", "55", "56", "57", "58", "59", "81", "159", "60", "61", "161", "62", "63",
                "163", "763", "64", "164", "65", "66", "96", "196", "67", "68", "69", "70", "71", "72", "73", "173", "74", "174", "75", "80", "76", "77", "97",
                "99", "177", "197", "199", "777", "799", "78", "98", "178", "198", "79", "82", "83", "86", "186", "87", "89", "92", "94", "20", "95"};

        String charCodes [] = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};

        long startTime = System.currentTimeMillis();

        for (String regionCode : regionCodes) {
            for (String charCode : charCodes) {
                for (int numCode = 1; numCode < NUM_CODE_COUNT; numCode++) {
                    carNumberArrayList.add(String.format("%1s%03d%1s%1s%s", charCode, numCode, charCode, charCode, regionCode));
                }
            }
        }
        carNumberHashSet.addAll(carNumberArrayList);
        carNumberTreeSet.addAll(carNumberArrayList);
        Collections.sort(carNumberArrayList);
        long duration = (System.currentTimeMillis() - startTime) / MILLISEC_TO_SEC;

        System.out.println("Сгенерированно блатных номеров: " + carNumberArrayList.size()+ "\nВремя генерации: " + duration + " сек");

    }
//=====================================================================================================================
    //Проверяем номер по листу и возвращаем результат Метод поиска contains реализует внутри себя последовательный перебор
    public boolean GetContains(String number) {
        return carNumberArrayList.contains(number);
    }

    //Проверяем номер по листу и возвращаем результат. Бинарный поиск.
    public boolean GetBinarySearch (String number) {
        return Collections.binarySearch(carNumberArrayList, number) > -1 ;
    }

    //Проверяем номер по листу и возвращаем результат. Поик через построение HashSet.
    public boolean GetHashSetSearch (String number) {
        return carNumberHashSet.contains(number) ;
    }

    //Проверяем номер по листу и возвращаем результат. Поик через построение TreeSet
    public boolean GetTreeSetSearch (String number) {
        return carNumberTreeSet.contains(number) ;
    }
}