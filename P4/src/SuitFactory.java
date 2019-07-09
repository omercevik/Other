class SuitFactory
{
    Suit getSuit(String suitType, int Flamethrower, int AutoRifle, int RocketLauncher, int Laser)
    {
        if (suitType == null || Flamethrower < 0 || AutoRifle < 0 || RocketLauncher < 0 || Laser < 0)
            return null;

        if (suitType.equalsIgnoreCase("dec"))
            return new Dec(Flamethrower,AutoRifle,RocketLauncher,Laser);
        else if (suitType.equalsIgnoreCase("ora"))
            return new Ora(Flamethrower,AutoRifle,RocketLauncher,Laser);
        else if (suitType.equalsIgnoreCase("tor"))
            return new Tor(Flamethrower,AutoRifle,RocketLauncher,Laser);
        return null;
    }
}
