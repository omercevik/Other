class FactoryProducer
{
    static AbstractFactory getFactory(int marketType)
    {
        switch (marketType)
        {
            case 0: return new DomesticFactory();

            case 1: return new EurasiaFactory();

            case 2: return new OtherFactory();

            default: return null;
        }
    }
}
