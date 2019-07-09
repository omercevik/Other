public interface Suit
{
    float FlamethrowerWeight = 2f;
    float AutoRifleWeight = 1.5f;
    float RocketLauncherWeight = 7.5f;
    float LaserWeight = 5.5f;

    float FlamethrowerCost = 50f;
    float AutoRifleCost = 30f;
    float RocketLauncherCost = 150f;
    float LaserCost = 200f;

    float suitWeight();
    float accessoriesWeight();
    float cost();
}
