import java.io.IOException;
import java.util.Scanner;

public class Loader
{
    public static int boxCount; // Кол-во ящиков
    public static int caseCount; // Кол-во контейнеров
    public static int cargoCarCount; // Кол-во грузовиков
    public static short maxCaseInCargoCar; // Счетчик наполнения грузовика контейнерами
    public static short maxBoxInCargoCase; // Счетчик наполнения контейнера ящиками
    // Переменная для организации сквозной нумерации контейнеров
    public static int caseCountInCar = 1;
    public static int boxCountInCase = 1;

    public static void main (String [] args) throws IOException
    {
        //Запрашиваем кол-во ящиков
        System.out.println("Введите количество ящиков:");
        boxCount = new Scanner(System.in).nextInt();

        /**Расчитываем кол-во контейнеров для ящиков
         * Приводим количество ящиков к вещественному числу, иначе при делении получим целое число без дробной части. Результат от деления обрабатываем через метод
         * Ceil класса Math, он возвращает ближайшее большее целое число относительно входного аргумента, т.е. при 280 мы получаем результат деления 10.37,
         * 10 полностью загруженных контейнеров и 1 не полностью и того 11, что нам и дает Ceil.
         * */
        caseCount = (int) (Math.ceil((double) boxCount / 27));
        System.out.println("Всего контейнеров: " + caseCount);

        // Расчитываем кол-во грузовиков для контейнеров
        cargoCarCount = (int) (Math.ceil((double) caseCount / 12));
        System.out.println("Всего грузовиков: " + cargoCarCount);

        for (int carNumber = 1; carNumber <= cargoCarCount; carNumber++) { //Создаем грузовики через конструктор столько, сколько мы расчитали
            Car cargoCar = new Car(carNumber);
            System.out.println("Грузовик №: " + cargoCar.getСarMark()); // Выводим очередной грузовик.

            maxCaseInCargoCar = 0; //Счетчик заполняемости грузовика контейнерами

            for (int caseNumber = caseCountInCar; caseNumber <= caseCount; caseNumber++) { //Создаем контейнеры через конструктор столько, сколько мы расчитали
                Case cargoCase = new Case(caseNumber);
                System.out.println('\t' + "Контейнер №: " + cargoCase.getСaseMark()); //Выводим очередной контейнер внутри грузовика

                maxCaseInCargoCar++; //увеличиваем счетчик заполняемости на 1

                maxBoxInCargoCase = 0; //Счетчик заполняемости контейнера ящиками

                for (int boxNumber = boxCountInCase; boxNumber <= boxCount; boxNumber++) { //Создаем ящики через конструктор столько, сколько введено в консоле
                    Box cargoBox = new Box(boxNumber);
                    System.out.println("\t\t" + "Ящик №: " + cargoBox.getBoxMark()); //Выводим очередной ящик внутри контейнера

                    maxBoxInCargoCase++; //увеличиваем счетчик заполняемости на 1

                    /**Если контейнер полон, то записываем в переменную для сквозной нумерации значение следующего ящика, чтоб нумерация началась с него.
                     * Переходим к следующему контейнеру.
                     */
                    if (maxBoxInCargoCase == 27) {
                        boxCountInCase = boxNumber + 1;
                        break;
                    }
                }

                /**Если грузовик полон, то записываем в переменную для сквозной нумерации значение следующего контейнера, чтоб нумерация началась с него.
                 * Переходим к следующему грузовику.
                */
                if (maxCaseInCargoCar == 12) {
                    caseCountInCar = caseNumber + 1;
                    break;
                }
            }
        }
    }
}