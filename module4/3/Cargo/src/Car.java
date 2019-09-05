public class Car
{
    private int carMark;

    //Конструктор грузовиков, на входе номер шрузовика из цикла
    public Car(int carNumber) {
        carMark = carNumber;
    }

    //Возвращаем маркировку грузовика
    public int getСarMark() {
        return carMark;
    }
}
