public class Main
{
    public static void main(String[] args)
    {
        TPXFactory TPX = new TPXFactory();

        TPX TPX100 = TPX.getTPX("TPX100");
        System.out.println(TPX100);

        TPX TPX200 = TPX.getTPX("TPX200");
        System.out.println(TPX200);

        TPX TPX300 = TPX.getTPX("TPX300");
        System.out.println(TPX300);
    }
}
