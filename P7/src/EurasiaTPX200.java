public class EurasiaTPX200 extends TPX_200 implements TPX
{
    private String EngineInjectionType = "Turbofan";
    private String SeatingCover = "Linen";

    @Override
    public String toString()
    {
        return "Eurasia TPX 200 :" + "\nPurpose : " + purpose + "\nSkeleton : " + constructSkeleton() +
                "\nEngines : " + placeEngines() + "\nSeats : " + placeSeats() +
                "\nEngine Injection Type : " + EngineInjectionType + "\nSeating Cover : " + SeatingCover + "\n";
    }
}
