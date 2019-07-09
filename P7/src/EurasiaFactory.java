public class EurasiaFactory extends AbstractFactory
{
    @Override
    public TPX getTPX(String TPX_type)
    {
        if (TPX_type == null)
            return null;
        else if (TPX_type.equalsIgnoreCase("TPX100"))
            return new EurasiaTPX100();
        else if (TPX_type.equalsIgnoreCase("TPX200"))
            return new EurasiaTPX200();
        else if (TPX_type.equalsIgnoreCase("TPX300"))
            return new EurasiaTPX300();
        return null;
    }
}
