import java.lang.reflect.Array;

public class Loader
{
    public static void main(String[] args)
    {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        System.out.println(text);

        String subText = text.substring(text.indexOf("Вася"), text.indexOf("руб")).trim();
        int indexCh = subText.length()-1;
        while (indexCh >= 0) {
            if (((int) subText.charAt(indexCh) >= 48 && ((int) subText.charAt(indexCh) <= 57))) {
                indexCh--;
            }
            else {
                break;
            }
        }
        int sum = Integer.parseInt(subText.substring(indexCh).trim());
        subText = text.substring(text.indexOf("Маша"), text.lastIndexOf("руб")).trim();
        indexCh = subText.length()-1;
        while (indexCh >= 0) {
            if (((int) subText.charAt(indexCh) >= 48 && ((int) subText.charAt(indexCh) <= 57))) {
                indexCh--;
            }
            else {
                break;
            }
        }
        sum += Integer.parseInt(subText.substring(indexCh).trim());

        System.out.println("Сумма заработка Васи и Маши: " + sum);
    }
}