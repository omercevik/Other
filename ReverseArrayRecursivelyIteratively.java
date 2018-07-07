public class ReverseArrayRecursivelyIteratively
{
	public static void initArray(int [] array)
	{
		for (int i = 0; i < array.length; ++i)
		{
			array[i] = i+1;
		}
	}

	public static int[] ReverseArrayIteratively(int[] array)
	{
		for (int i = 0; i < array.length/2; ++i)
		{
			int temp = array[i];
			array[i] = array[array.length-i-1];
			array[array.length-i-1] = temp;
		}
		return array;
	}

	public static void printArray(int[] array)
	{
		for (int i : array)
		{
			System.out.printf("%d ",i);
		}
		System.out.println("\n");
	}

	public static int[] ReverseArrayRecursively(int[] array, int size, int index)
	{
		if ( size != array.length/2 )
		{
			int temp = array[index];
			array[index] = array[array.length-size-1];
			array[array.length-size-1] = temp;
			return ReverseArrayRecursively(array,size+1,index+1);
		}
		return array;
	}

	public static void main(String[] args)
	{
		int [] array = new int[15];

		initArray(array);
		System.out.println("Array initialized: ");
		printArray(array);

		array = ReverseArrayIteratively(array);
		System.out.println("Iteratively reverse array called: ");
		printArray(array);

		array = ReverseArrayRecursively(array,0,0);
		System.out.println("Recursively reverse array called: ");
		printArray(array);
	}
}