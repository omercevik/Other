public class OtherFactory extends AbstractFactory
{
    @Override
    public TPX getTPX(String TPX_type)
    {
        if (TPX_type == null)
            return null;
        else if (TPX_type.equalsIgnoreCase("TPX100"))
            return new OtherTPX100();
        else if (TPX_type.equalsIgnoreCase("TPX200"))
            return new OtherTPX200();
        else if (TPX_type.equalsIgnoreCase("TPX300"))
            return new OtherTPX300();
        return null;
    }
}
