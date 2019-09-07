//Получилось учесть частные случаи слов содержащих в себе дефис или апостроф, например cherry-apple или cook’s

public class Loader
{
    public static void main (String []args)
    {
        String text = "What is it a strange word? Macaroon. It seems that it has appeared recently and belongs to these modern words. But it is not true. " +
                "The macaroon is a soft cake of rounded shape that people have tasted since the middle Ages. It has from 3 to 5 cm in diameter. " +
                "The macaroon recipe varies by the cook’s skills. Professionals cook more complicated recipes, but housewives can try to cook light variant of them. " +
                "Indeed, the preparation and cooking of macaroons is difficult even for professionals. " +
                "To make a traditional macaroon, you need almond powder, icing sugar, sugar and egg whites. " +
                "The amount of almond is equal to the amount of icing sugar. The macaroon has different flavors and colors. " +
                "Generally, the pink macaroon is made with strawberry or raspberry. The beige macaroon has vanilla flavor. " +
                "The brown macaroon is made of chocolate or coffee. The yellow macaroon is made with lemon. " +
                "Recently it has been cooked a special macaroon which has two flavors. The ‘body’ and the ‘heart’ are different. " +
                "For example, pear-orange and cherry-apple macaroons are very - popular.";

        text = text.replaceAll("(\\.|,|\\?|!|:|«|»|„|“|\\(|\\)|'|’\\s|\\s‘|-\\s)+","");
        String words[] = text.split("\\s+");
        System.out.println("Количество слов: " + words.length + "\n" +
                           "Cписок слов:\n==========================");
        for (String word : words) {
            System.out.println(word);
        }
    }
}
