
public class Loader
{
    public static void main(String[] args)
    {
        Cat vasya = new Cat();
        vasya.setColor(Color.BLACK);
        Cat kuzya = new Cat();
        kuzya.setColor(Color.GREY);
        Cat masha = new Cat();
        Cat boris = new Cat();
        System.out.println("boris: " + boris.getWeight() + " " + boris.getStatus() + " " + boris.getEatAmount());
        Cat venya = new Cat();

        System.out.println("Кол-во котов: " + Cat.getCount());


        Cat murzik = new Cat(800.00);
        System.out.println("murzik: " + murzik.getWeight() + " " + murzik.getStatus());
        System.out.println("Кол-во котов: " + Cat.getCount());

        Cat borisTwin = boris.getCatTwin();
        System.out.println("borisTwin: " + borisTwin.getWeight() + " " + borisTwin.getStatus() + " " + borisTwin.getEatAmount());
        System.out.println("Кол-во котов: " + Cat.getCount());

        //Кормим Ваську 1 раз едой 100гр, возвращаем вес и статус
        System.out.println("vasya: " + vasya.getWeight() + " " + vasya.getStatus() + " цвет: " + vasya.getCatColor());
        vasya.feed(100.0);
        System.out.println("vasya: " + vasya.getWeight() + " " + vasya.getStatus() + "  Съедено " + vasya.getEatAmount());

        //Поим Кузю 1 раз водой 100гр, возвращаем вес и статус
        System.out.println("kuzya: " + kuzya.getWeight() + " " + kuzya.getStatus() + " цвет: " + kuzya.getCatColor());
        kuzya.drink(100.0);
        System.out.println("kuzya: " + kuzya.getWeight() + " " + kuzya.getStatus() + "  Съедено " + kuzya.getEatAmount());

        //Заставляем Машку мяукнуть 1 раз, проверяем вес и статус
        System.out.println("masha: " + masha.getWeight() + " " + masha.getStatus());
        masha.meow();
        System.out.println("masha: " + masha.getWeight() + " " + masha.getStatus() + "  Съедено " + masha.getEatAmount());

        //Машка какает 1 раз, поход в туалет отнимает 50гр веса
        System.out.println("masha: " + masha.getWeight() + " " + masha.getStatus());
        masha.turd();
        System.out.println("masha: " + masha.getWeight() + " " + masha.getStatus() + "  Съедено " + masha.getEatAmount());

        while (boris.getWeight() > 1000.0) {
            boris.meow();
            System.out.println("boris: " + boris.getWeight() + " " + boris.getStatus());
        }
        System.out.println("Кол-во котов: " + Cat.getCount());
        //Проверяем что клон Бориса чувствует себя хорошо
        System.out.println("borisTwin: " + borisTwin.getWeight() + " " + borisTwin.getStatus());

        // Второй клон, уже мертвого бориса
        Cat borisTwin2 = boris.getCatTwin();
        System.out.println("borisTwin: " + borisTwin2.getWeight() + " " + borisTwin2.getStatus());

        while (venya.getWeight() < 9000.0) {
            venya.feed(100.0);
            System.out.println("venya: " + venya.getWeight() + " " + venya.getStatus());
        }
        System.out.println("Кол-во котов: " + Cat.getCount());

    }
}