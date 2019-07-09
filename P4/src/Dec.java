public class Dec implements Suit
{
    private int Flamethrower;
    private int AutoRifle;
    private int RocketLauncher;
    private int Laser;

    Dec(int Flamethrower, int AutoRifle, int RocketLauncher, int Laser)
    {
        this.Flamethrower = Flamethrower;
        this.AutoRifle = AutoRifle;
        this.RocketLauncher = RocketLauncher;
        this.Laser = Laser;
    }

    @Override
    public float suitWeight()
    {
        return 25f;
    }

    @Override
    public float accessoriesWeight()
    {
        float suitWeight = 25f;
        return Flamethrower*FlamethrowerWeight + AutoRifle*AutoRifleWeight + RocketLauncher*RocketLauncherWeight + Laser*LaserWeight + suitWeight;
    }

    @Override
    public float cost()
    {
        float suitCost = 500f;
        return Flamethrower*FlamethrowerCost + AutoRifle*AutoRifleCost + RocketLauncher*RocketLauncherCost + Laser*LaserCost + suitCost;
    }

    @Override
    public String toString()
    {
        return "Dec Suit :" + "\n" + "Total cost is " + cost() + "\n" + "Suit weight is " + suitWeight() + "\n" + "Total weight is " + accessoriesWeight() + "\n";
    }
}
