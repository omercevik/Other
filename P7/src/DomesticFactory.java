public class DomesticFactory extends AbstractFactory
{
    @Override
    public TPX getTPX(String TPX_type)
    {
        if (TPX_type == null)
            return null;
        else if (TPX_type.equalsIgnoreCase("TPX100"))
            return new DomesticTPX100();
        else if (TPX_type.equalsIgnoreCase("TPX200"))
            return new DomesticTPX200();
        else if (TPX_type.equalsIgnoreCase("TPX300"))
            return new DomesticTPX300();
        return null;
    }
}
