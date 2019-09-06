public class Loader
{
    public static void main (String [] args)
    {
        for (int chCode = 'A'; chCode <= 'z'; chCode++)
        {
            System.out.println("Код симвла: " + chCode + " Символ кода: "+ (char) chCode);
        }
    }
}
