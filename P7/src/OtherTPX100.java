public class OtherTPX100 extends TPX_100 implements TPX
{
    private String EngineInjectionType = "Geared turbofan";
    private String SeatingCover = "Leather";

    @Override
    public String toString()
    {
        return "Other TPX 100 :" + "\nPurpose : " + purpose + "\nSkeleton : " + constructSkeleton() +
                "\nEngines : " + placeEngines() + "\nSeats : " + placeSeats() +
                "\nEngine Injection Type : " + EngineInjectionType + "\nSeating Cover : " + SeatingCover + "\n";
    }
}
