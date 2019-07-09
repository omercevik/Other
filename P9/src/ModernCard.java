public class ModernCard implements ModernPayment
{
    @Override
    public int pay(String cardNo, float amount, String destination, String installments)
    {
        System.out.println("CardNo : " + cardNo);
        System.out.println("Amount : " + amount);
        System.out.println("Destination : " + destination);
        System.out.println("Installments : " + installments);
        return 200;
    }
}