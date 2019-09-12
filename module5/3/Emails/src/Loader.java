import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader
{
    private static HashSet <String> emails = new HashSet<>();
    private static final String VALIDATOR = "(?<comm>ADD)\\s*" +
                                            "(?<email>[a-zA-Z0-9]{1,}" + "((\\.|_|-){0,1}[a-zA-Z0-9]{1,})*" + "@" +
                                            "[a-zA-Z0-9]{1,}" + "((\\.|_|-){0,1}[a-zA-Z0-9]{1,})*" + "\\.[a-zA-Z]{2,})$";

    public static void main(String[] args) {

        Pattern patEmail = Pattern.compile(VALIDATOR);

        for (; ;) {
            System.out.println(Help.HELP_COMMANDS.getDescription() + "Введите команду:");
            String command = new Scanner(System.in).nextLine().trim();
            Matcher matcher = patEmail.matcher(command);

            if (command.startsWith("EXIT")) {
                break;
            }

            else if (command.startsWith("LIST")) {
                printEmails();
            }

            else if (command.startsWith("ADD")) {
                addEmailToList(getEmail(matcher));
            }
            else {
                System.out.println("Не корректная команда.");
            }
        }

    }
//=====================================================================================================================
    private static void printEmails() {
        if (emails.size() > 0) {
            for (String email : emails) {
                System.out.println(email);
            }
        }
        else {
            System.out.println("Список email адресов пуст.");
        }
    }

    private static void addEmailToList(String email) {
        if (email.equals("")) {
            System.out.println("Email адрес не указан, или не корректный.\n" + Help.HELP_EMAIL_VALID.getDescription());
        }
        else {
            emails.add(email);
        }
    }

    private static String getEmail (Matcher matcher) {
        String email = "";
        if (matcher.matches()) {
            email = matcher.group("email");
        }
        return email;
    }
}