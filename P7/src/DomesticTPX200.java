public class DomesticTPX200 extends TPX_200 implements TPX
{
    private String EngineInjectionType = "Turbojet";
    private String SeatingCover = "Velvet";

    @Override
    public String toString()
    {
        return "Domestic TPX 200 :" + "\nPurpose : " + purpose + "\nSkeleton : " + constructSkeleton() +
                "\nEngines : " + placeEngines() + "\nSeats : " + placeSeats() +
                "\nEngine Injection Type : " + EngineInjectionType + "\nSeating Cover : " + SeatingCover + "\n";
    }
}
