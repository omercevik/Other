public class TPXFactory
{
    TPX getTPX(String TPX_type)
    {
        if (TPX_type == null)
            return null;
        else if (TPX_type.equalsIgnoreCase("TPX100"))
            return new TPX_100();
        else if (TPX_type.equalsIgnoreCase("TPX200"))
            return new TPX_200();
        else if (TPX_type.equalsIgnoreCase("TPX300"))
            return new TPX_300();
        return null;
    }
}
