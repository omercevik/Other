public class EurasiaTPX300 extends TPX_300 implements TPX
{
    private String EngineInjectionType = "Turbofan";
    private String SeatingCover = "Linen";

    @Override
    public String toString()
    {
        return "Eurasia TPX 300 :" + "\nPurpose : " + purpose + "\nSkeleton : " + constructSkeleton() +
                "\nEngines : " + placeEngines() + "\nSeats : " + placeSeats() +
                "\nEngine Injection Type : " + EngineInjectionType + "\nSeating Cover : " + SeatingCover + "\n";
    }
}
