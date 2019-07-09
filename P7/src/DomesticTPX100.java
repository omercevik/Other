public class DomesticTPX100 extends TPX_100 implements TPX
{
    private String EngineInjectionType = "Turbojet";
    private String SeatingCover = "Velvet";

    @Override
    public String toString()
    {
        return "Domestic TPX 100 :" + "\nPurpose : " + purpose + "\nSkeleton : " + constructSkeleton() +
                "\nEngines : " + placeEngines() + "\nSeats : " + placeSeats() +
                "\nEngine Injection Type : " + EngineInjectionType + "\nSeating Cover : " + SeatingCover + "\n";
    }
}
