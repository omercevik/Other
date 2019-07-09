public class TPX_300 implements TPX
{
    String purpose = "Transatlantic flights";

    @Override
    public String constructSkeleton()
    {
        return "Titanium alloy";
    }

    @Override
    public String placeEngines()
    {
        return "Quadro jet engines";
    }

    @Override
    public int placeSeats()
    {
        return 250;
    }

    @Override
    public String toString()
    {
        return "TPX 300 :" + "\nPurpose : " + purpose + "\nSkeleton : " + constructSkeleton() +
                "\nEngines : " + placeEngines() + "\nSeats : " + placeSeats() + "\n";
    }
}
