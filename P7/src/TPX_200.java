public class TPX_200 implements TPX
{
    String purpose = "Domestic and short international flights";

    @Override
    public String constructSkeleton()
    {
        return "Nickel alloy";
    }

    @Override
    public String placeEngines()
    {
        return "Twin jet engines";
    }

    @Override
    public int placeSeats()
    {
        return 100;
    }

    @Override
    public String toString()
    {
        return "TPX 200 :" + "\nPurpose : " + purpose + "\nSkeleton : " + constructSkeleton() +
                "\nEngines : " + placeEngines() + "\nSeats : " + placeSeats() + "\n";
    }
}
