import java.util.Scanner;

public class Loader
{
    public static void main(String[] args) {
        System.out.println("Введите номер телефона:");
        String phoneNumber = new Scanner(System.in).nextLine();

        phoneNumber = phoneNumber.replaceAll("\\D+","");
        System.out.println(phoneNumber);
    }
}
