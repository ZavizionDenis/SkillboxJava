import java.util.Scanner;

public class Loader
{
    public static void main(String [] args) {

        System.out.println("Введите ФИО: ");
        String fio = new Scanner(System.in).nextLine().trim();

        if (fio.isEmpty()) {
            System.out.println("Пустая строка");
        }
        else {
            String fioElements[] = fio.split("\\s");
            System.out.println(fioElements.length);
            if (fioElements.length == 3) {
                System.out.println("Фамилия: " + fioElements[0] + "\n" +
                        "Имя: " + fioElements[1] + "\n" +
                        "Отчество: " + fioElements[2]);
            } else if (fioElements.length == 2) {
                System.out.println("Фамилия: " + fioElements[0] + "\n" +
                        "Имя: " + fioElements[1]);
            } else {
                System.out.println("Фамилия: " + fioElements[0]);
            }
        }
    }
}
