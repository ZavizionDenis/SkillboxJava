public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.count += 7843;
        System.out.println(sumDigits(container.count));

    }

    public static Integer sumDigits(Integer number)
    {
        //@TODO: write code here
        String str = number.toString();
        int sum = 0;
        for (int indexChar = 0 ; indexChar < str.length(); indexChar++) {
            sum += Integer.parseInt(String.valueOf(str.charAt(indexChar)));
        }
        return sum;
    }
}
