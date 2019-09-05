public class Case
{
    private static int caseMark;

    //Конструктор контейнеров, на входе номер контейнера из цикла
    public Case(int caseNumber) {
        caseMark = caseNumber;
    }

    //Возвращаем маркировку контейнера
    public int getСaseMark() {
        return caseMark;
    }
}
