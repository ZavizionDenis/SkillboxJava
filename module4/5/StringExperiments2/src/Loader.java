public class Loader
{
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        text = text.replaceAll("[^0-9\\s]+","").trim();
        String textFragments[] = text.split("\\s+");

        int sum = 0;
        for (String textFragment : textFragments) {
            sum += Integer.parseInt(textFragment);
        }
        System.out.println("Общая сумма заработка: " + sum);
    }
}