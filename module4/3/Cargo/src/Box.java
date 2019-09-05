public class Box
{
    private int boxMark;

    //Конструктор для ящиков на входе номер ящика по порядку из цикла
    public Box(int boxNumber) {
        boxMark = boxNumber;
    }

    //Возвращаем маркировку ящика
    public int getBoxMark() {
        return boxMark;
    }
}