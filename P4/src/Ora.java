public class Ora implements Suit
{
    private int Flamethrower;
    private int AutoRifle;
    private int RocketLauncher;
    private int Laser;

    Ora(int Flamethrower, int AutoRifle, int RocketLauncher, int Laser)
    {
        this.Flamethrower = Flamethrower;
        this.AutoRifle = AutoRifle;
        this.RocketLauncher = RocketLauncher;
        this.Laser = Laser;
    }

    @Override
    public float suitWeight()
    {
        return 30f;
    }

    @Override
    public float accessoriesWeight()
    {
        float suitWeight = 30f;
        return Flamethrower*FlamethrowerWeight + AutoRifle*AutoRifleWeight + RocketLauncher*RocketLauncherWeight + Laser*LaserWeight + suitWeight;
    }

    @Override
    public float cost()
    {
        float suitCost = 1500f;
        return Flamethrower*FlamethrowerCost + AutoRifle*AutoRifleCost + RocketLauncher*RocketLauncherCost + Laser*LaserCost + suitCost;
    }

    @Override
    public String toString()
    {
        return "Ora Suit :" + "\n" + "Total cost is " + cost() + "\n" + "Suit weight is " + suitWeight()  + "\n" + "Total weight is " + accessoriesWeight() + "\n";
    }
}
