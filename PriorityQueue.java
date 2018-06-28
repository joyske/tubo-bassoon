public class PriorityQueue
{
	public static void main(String[] args)
	{
		try
		{
			//es müssen mindestens zwei Eingaben gemacht werden, damit das Programm ordnungsgemäß läuft
			if(args.length == 2)
			{
				int n = Integer.parseInt(args[0]);
				//ist der erste Parameter 0, würde der Heap keine Elemente enthalten, daher eine Fehlermeldung
				if(n == 0)
				{
					System.out.println("Bitte eine Zahl groesser als 0 fuer den ersten Parameter eingeben!");
					return;
				}
				int k = Integer.parseInt(args[1]);
				MaxHeap heap = new MaxHeap(n+k);
				for(int i=0; i<n; i++)
				{
					java.util.Random numberGenerator = new java.util.Random();
					int randomNumber = numberGenerator.nextInt(100);
					heap.insert(randomNumber);
				}
				heap.printHeap();
				for(int i=0; i<k; i++)
				{
					java.util.Random numberGenerator = new java.util.Random();
					int randomNumber = numberGenerator.nextInt(100);
					if(randomNumber < 74)
					{
						System.out.println("\n" + "Der Job mit der Prioritaet: " + heap.extractMax() + " wird jetzt bearbeitet!" + "\n");
						heap.printHeap();
					}
					else
					{
						System.out.println("\n" + "Ein Job mit der Prioritaet: " + randomNumber + " wird jetzt eingefuegt!" + "\n");
						heap.insert(randomNumber);
						heap.printHeap();
					}
				}
			}
			else
			{
				System.out.println("Bitte zwei natuerliche Zahlen eingeben!");
			}
		}
		catch(NumberFormatException e)
		{
			System.out.println("Bitte zwei natuerliche Zahlen eingeben!");
		}
	}
}