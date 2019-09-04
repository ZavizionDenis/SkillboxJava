
public class Cat
{
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;

    private static Integer count = 0; // Счетчик котов

    private static final int EYE_COUNT = 2;
    private static final double MIN_WEIGTH = 1000.0;
    private static final double MAX_WEIGTH = 9000.0;
    private Color catColor;

    private double eatAmount;
    private boolean isAlive;

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
//        minWeight = 1000.0;
//        maxWeight = 9000.0;

        minWeight = MIN_WEIGTH;
        maxWeight = MAX_WEIGTH;

        eatAmount = 0.0;
        isAlive = true;


        //При создании очередного кота считаем его
        count++;
    }

    public Cat(Double kitenWeight)
    {
        this();
        weight = kitenWeight;
        minWeight = 80.0;
    }

    public void meow()
    {
        weight = weight - 1;
        if (isAlive && getStatus().equals("Dead")) {
            count--; // уменьшаем кол-во котов при смерте
            isAlive = false;
        }
        System.out.println("Meow");
    }

    public void feed(Double amount)
    {
        weight = weight + amount;
        eatAmount += amount;

        if (isAlive && getStatus().equals("Exploded")) {
            count--; // уменьшаем кол-во котов при смерте
            isAlive = false;
        }

    }

    public void drink(Double amount)
    {
        weight = weight + amount;
        eatAmount += amount;

        if (isAlive && getStatus().equals("Exploded")) {
            count--; // уменьшаем кол-во котов при смерте
            isAlive = false;
        }
    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < minWeight) {
            return "Dead";
        }
        else if(weight > maxWeight) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }

    //Количество съеденой пищи
    public Double getEatAmount()
    {
            return eatAmount;
    }

    //Поход кота в туалет, это отнимает 50гр веса
    public void turd()
    {
        weight = weight - 50;
        System.out.println("kak-kak");
    }

    //возвращаем кол-во котов
    public static Integer getCount()
    {
        return count;
    }

    public void setColor (Color color) {
        this.catColor = color;
    }
    public Color getCatColor() {
        return catColor;
    }

    //Клонатор котов К3000
    public Cat getCatTwin()
    {
        Cat catTwin = new Cat();
        catTwin.weight = weight;
        catTwin.originWeight = originWeight;

        catTwin.minWeight = minWeight;
        catTwin.maxWeight = maxWeight;

        catTwin.eatAmount = eatAmount;
        catTwin.isAlive = isAlive;

        if (!catTwin.isAlive) {
            count--;
        }

        return catTwin;
    }
}