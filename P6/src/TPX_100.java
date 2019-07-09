public class TPX_100 implements TPX
{
    @Override
    public String constructSkeleton()
    {
        return "Aluminum alloy";
    }

    @Override
    public String placeEngines() { return "Single jet engine"; }

    @Override
    public int placeSeats() { return 50; }

    @Override
    public String toString()
    {
        String purpose = "Domestic flights";
        return "TPX 100 :" + "\nPurpose : " + purpose + "\nSkeleton : " + constructSkeleton() +
                "\nEngines : " + placeEngines() + "\nSeats : " + placeSeats() + "\n";
    }
}
