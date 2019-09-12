import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader
{
    private static final String PHONE_NUM_VALID = "^[0-9]{11,15}$";
    private static final String NAME_VALID = "^[a-zA-Zа-яА-Я\\s]{2,}$";
    private static HashMap <String, String> phoneBook = new HashMap<>();
    private static Pattern chekPhoneNumber = Pattern.compile(PHONE_NUM_VALID);
    private static Pattern chekName = Pattern.compile(NAME_VALID);

    public static void main(String[] args) {

        for (; ;) {
            System.out.println("Введите ФИО/номер телефона/комманду LIST: ");
            String inputAny = new Scanner(System.in).nextLine();

            String phoneNumber = inputAny.replaceAll("[^0-9]+", "").trim();
            Boolean isPhoneNumber = chekPhoneNumber.matcher(phoneNumber).matches();

            String name = inputAny.replaceAll("[^a-zA-Zа-яА-Я\\s]+", "").trim();
            Boolean isName = chekName.matcher(name).matches();

            if (inputAny.trim().equals("LIST")) {
                printPhoneBook();
            }

            else if ((isPhoneNumber && isName) || (isPhoneNumber && !isName)) {
                getInfoByPhone(phoneNumber, name);
            }

            else if (!isPhoneNumber && isName) {
                getInfoByName(name);
            }
            else {
                System.out.println("Не корректный номер телефона (номер телефона нужно вводить в федеральном формате, длинна от 11 до 15 цифр), или" +
                                   "имя владельца (любые ЛАТ. и КИР. буквы длинной от 2 символов.)");
            }
        }
    }
//=====================================================================================================================
    private static void printPhoneBook () {
        if (phoneBook.size() > 0) {
            ArrayList<String> sortList = new ArrayList<>();
            System.out.println("Список телефонной книги:");
            for (String record : phoneBook.keySet()) {
                sortList.add(phoneBook.get(record) + record);
            }
            Collections.sort(sortList);
            for (String element : sortList) {
                String phoneNumber = element.replaceAll("[^0-9]+", "");
                String personName = element.replaceAll("[^a-zA-Zа-яА-Я\\s]+", "");
                System.out.println("Номер телефона:" + phoneNumber + " Владелец: " + personName);
            }
        }
        else {
            System.out.println("Телефонная книга пуста.");
        }
    }

    private static void getInfoByPhone (String phoneNumber, String name) {
        if (phoneBook.get(phoneNumber) != null) {
            System.out.println("Номер телефона: " + phoneNumber + " Владелец номера телефона: " + phoneBook.get(phoneNumber));
        }
        else {
            System.out.println("Номер телефона: " + phoneNumber + " с владельцем " + name + " не найден в базе и будет добавлен.");
            while (!chekName.matcher(name).matches()) {
                System.out.println("Введите имя владельца:");
                name = new Scanner(System.in).nextLine().trim();
            }
            addToPhoneBook(phoneNumber, name);
        }
    }

    private static void getInfoByName (String name) {
        String phoneNumber = "";
        if (phoneBook.containsValue(name)) {
            for (String record : phoneBook.keySet()) {
                if (phoneBook.get(record).equals(name)) {
                    System.out.println("Номер телефона: " + record + " Владелец номера телефона: " + name);
                }
            }
        }
        else {
            System.out.println("Владелец: " + name + " не найден в базе и будет добавлен.");
            for (; ;) {
                System.out.println("Введите номер телефона:");
                phoneNumber = new Scanner(System.in).nextLine().trim();
                if (chekPhoneNumber.matcher(phoneNumber).matches() && !phoneBook.containsKey(phoneNumber)) {
                    addToPhoneBook(phoneNumber, name);
                    break;
                }
                else {
                    System.out.println("Не корректный номер телефона, или телефон принадлежит другому владельцу.");
                }
            }
        }
    }

    private static void addToPhoneBook (String phoneNumber, String name) {
        phoneBook.put(phoneNumber, name);
    }
}