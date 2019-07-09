public class Main
{
    public static void main(String[] args)
    {
        AbstractFactory TPX = FactoryProducer.getFactory(0);

        TPX TPX100 = TPX.getTPX("TPX100");
        System.out.println(TPX100);

        TPX TPX200 = TPX.getTPX("TPX200");
        System.out.println(TPX200);

        TPX TPX300 = TPX.getTPX("TPX300");
        System.out.println(TPX300);


        TPX = FactoryProducer.getFactory(1);

        TPX100 = TPX.getTPX("TPX100");
        System.out.println(TPX100);

        TPX200 = TPX.getTPX("TPX200");
        System.out.println(TPX200);

        TPX300 = TPX.getTPX("TPX300");
        System.out.println(TPX300);


        TPX = FactoryProducer.getFactory(2);

        TPX100 = TPX.getTPX("TPX100");
        System.out.println(TPX100);

        TPX200 = TPX.getTPX("TPX200");
        System.out.println(TPX200);

        TPX300 = TPX.getTPX("TPX300");
        System.out.println(TPX300);
    }
}
