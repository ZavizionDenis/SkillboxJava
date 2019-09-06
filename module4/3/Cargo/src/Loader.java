import java.util.Scanner;

public class Loader
{
    private static final int MAX_CASE_IN_CARGO = 12;
    private static final int MAX_BOX_IN_CASE = 27;

    public static void main (String [] args)
    {
        //Запрашиваем кол-во ящиков
        System.out.println("Введите количество ящиков:");
       int boxCount = new Scanner(System.in).nextInt();

        /**Расчитываем кол-во контейнеров для ящиков
         * Приводим количество ящиков к вещественному числу, иначе при делении получим целое число без дробной части. Результат от деления обрабатываем через метод
         * Ceil класса Math, он возвращает ближайшее большее целое число относительно входного аргумента, т.е. при 280 мы получаем результат деления 10.37,
         * 10 полностью загруженных контейнеров и 1 не полностью и того 11, что нам и дает Ceil.
         * */
       int caseCount = (int) (Math.ceil((double) boxCount / MAX_BOX_IN_CASE));
        System.out.println("Всего контейнеров: " + caseCount);

       int cargoCarCount = (int) (Math.ceil((double) caseCount / MAX_CASE_IN_CARGO));
        System.out.println("Всего грузовиков: " + cargoCarCount);

        int nextCaseNumber = 1;
        int nextBoxNumber = 1;
        for (int carNumber = 1; carNumber <= cargoCarCount; carNumber++) {
            System.out.println("Грузовик №: " + carNumber);

           int caseInCargoCar = 0;

            for (int caseNumber = nextCaseNumber; caseNumber <= caseCount; caseNumber++) {
                System.out.println('\t' + "Контейнер №: " + caseNumber);
                caseInCargoCar++;
                nextBoxNumber = putBoxInCase(nextBoxNumber, boxCount);


                /**Если грузовик полон, то записываем в переменную для сквозной нумерации значение следующего контейнера, чтоб нумерация началась с него.
                 * Переходим к следующему грузовику.
                */
                if (caseInCargoCar == MAX_CASE_IN_CARGO) {
                    nextCaseNumber = caseNumber + 1;
                    break;
                }
            }
        }
    }

// ===================================== Набиваем очередной контейнер ящиками ========================================
    private static int putBoxInCase (int nextBoxNumber, int boxCount) {
        int boxInCargoCase = 0;

        for (int boxNumber = nextBoxNumber; boxNumber <= boxCount; boxNumber++) {
            System.out.println("\t\t" + "Ящик №: " + boxNumber);
            boxInCargoCase++;

            /**Если контейнер полон, то записываем в переменную для сквозной нумерации значение следующего ящика, чтоб нумерация началась с него.
             * Переходим к следующему контейнеру.
             */
            if (boxInCargoCase == MAX_BOX_IN_CASE) {
                nextBoxNumber = boxNumber + 1;
                break;
            }
        }
        return nextBoxNumber;
    }
//--------------------------------------------------------------------------------------------------------------------
}