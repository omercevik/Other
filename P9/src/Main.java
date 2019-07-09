public class Main
{
    public static void main(String[] args)
    {
        CardPayment cardPayment = new CardPayment();
        CardAdapter cardAdapter = new CardAdapter(new ModernCard());

        System.out.println("Return value : " + cardPayment.payInTurbo("0000-0000-0000-0001",150.0f,"A Bank","36"));
        System.out.println();
        System.out.println("Return value : " + cardAdapter.pay("0000-0000-0000-0002",250.0f,"B Bank","12"));
    }
}