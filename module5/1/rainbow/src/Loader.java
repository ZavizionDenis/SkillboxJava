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
    }
}
