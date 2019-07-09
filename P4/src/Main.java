public class Main
{
    public static void main(String[] args)
    {
        SuitFactory suitFactory = new SuitFactory();

        Suit decSuit = suitFactory.getSuit("dec",1,2,1,0);

        System.out.println(decSuit);

        Suit oraSuit = suitFactory.getSuit("ora",0,0,0,0);

        System.out.println(oraSuit);

        Suit torSuit = suitFactory.getSuit("tor",1,1,1,1);

        System.out.println(torSuit);
    }
}
