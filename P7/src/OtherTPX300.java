public class OtherTPX300 extends TPX_300 implements TPX
{
    private String EngineInjectionType = "Geared turbofan";
    private String SeatingCover = "Leather";

    @Override
    public String toString()
    {
        return "Other TPX 300 :" + "\nPurpose : " + purpose + "\nSkeleton : " + constructSkeleton() +
                "\nEngines : " + placeEngines() + "\nSeats : " + placeSeats() +
                "\nEngine Injection Type : " + EngineInjectionType + "\nSeating Cover : " + SeatingCover + "\n";
    }
}
