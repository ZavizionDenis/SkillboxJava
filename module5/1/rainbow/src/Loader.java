public class Loader
{
    public static void main (String [] args)
    {
        String[] rainbowColors = {"Red", "Orange", "Yellow", "Green", "Cyan", "Blue", "Violet"};
        for (String rainbowColor : rainbowColors) {
            System.out.println(rainbowColor);
        }
        for (int rainbowColorIndex = rainbowColors.length - 1; rainbowColorIndex >= 0; rainbowColorIndex--) {
            System.out.println(rainbowColors[rainbowColorIndex]);
        }

        //=================================== Переворачиваем массив ===================================================
        for (int rainbowColorIndex = 0; rainbowColorIndex <= rainbowColors.length / 2; rainbowColorIndex++) {
            String str1 = rainbowColors[rainbowColorIndex];
            rainbowColors[rainbowColorIndex] = rainbowColors[(rainbowColors.length - 1) - rainbowColorIndex]; // Круглые скобки для удобства чтения
            rainbowColors[(rainbowColors.length - 1) - rainbowColorIndex] = str1;
        }

        for (int rainbowColorIndex = 0; rainbowColorIndex < rainbowColors.length; rainbowColorIndex++) {
            System.out.println(rainbowColors[rainbowColorIndex]);
        }
    }
}
