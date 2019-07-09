public class CardAdapter implements ModernPayment
{
    private ModernPayment modernPayment;

    CardAdapter(ModernPayment modernPayment)
    {
        this.modernPayment = modernPayment;
    }

    @Override
    public int pay(String cardNo, float amount, String destination, String installments)
    {
        return modernPayment.pay(cardNo,amount,destination,installments);
    }
}