import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader
{
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(text);

        int sum = 0;
        while (matcher.find()) {
            sum += Integer.parseInt(matcher.group(0));
        }
        System.out.println("Общая сумма заработка: " + sum);
    }
}