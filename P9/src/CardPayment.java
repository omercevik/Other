public class CardPayment implements TurboPayment
{
    @Override
    public int payInTurbo(String turboCardNo, float turboAmount, String destinationTurboOfCourse, String installmentsButInTurbo)
    {
        System.out.println("TurboCardNo : " + turboCardNo);
        System.out.println("TurboAmount : " + turboAmount);
        System.out.println("DestinationTurbo : " + destinationTurboOfCourse);
        System.out.println("InstallmentsButInTurbo : " + installmentsButInTurbo);
        return 100;
    }
}