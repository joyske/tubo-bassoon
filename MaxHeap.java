public class MaxHeap
{
	//Attribute
	int[] arr;
	int heapSize;
	
	//Konstruktor initialisiert die heapSize mit 0, da noch kein Element 
	//auf dem Heap liegt und legt ein Feld an
	public MaxHeap(int n)
	{
		heapSize = 0;
		arr = new int[n];
	}
	
	//gibt den Index des linken Kindes für den Knoten am Index i an
	public int left(int i)
	{
		return 2*i;
	}
	
	//gibt den Index des rechten Kindes für den Knoten am Index i an
	public int right(int i)
	{
		return 2*i+1;
	}
	
	//gibt den Index des Elternknotens für den Knoten am Index i an
	public int parent(int i)
	{
		if(i == 0)
		{
			//der Knoten am Index i ist die Wurzel und kann daher keinen Elternknoten haben
			throw new IllegalArgumentException();
		}
		else
		{
			return (i-1)/2;
		}
	}
	
	//Methode zur Herstellung der Heap-Eigenschaft
	public void heapify(int i)
	{
		//Variablen für linkes und rechtes Kind des Knotens i
		int l = left(i);
		int r = right(i);
		int max;
		//wenn linkes Kind von i im Heap liegt und der Wert an dieser Stelle größer ist
		//als der des Knotens i, setzen wir max auf den Wer des linken Kindes
		if(l < heapSize && arr[l] > arr[i])
		{
			max = l;
		}
		//ansonsten bleibt i der größte Wert
		else
		{
			max = i;
		}
		//gleiches gilt für das rechte Kind, da auch hier die Heap-Eigenschaft ansonsten verletzt würde
		if(r < heapSize && arr[r] > arr[max])
		{
			max = r;
		}
		//ist der Index des größten, gefundenen Wertes verschieden vom betrachteten Index,
		//so tauschen wir die beiden Werte und erhalten für diesen Teilbaum die Heap-Eigenschaft
		if(max != i)
		{
			int temp = arr[i];
			arr[i] = arr[max];
			arr[max] = temp;
			//anschließend laufen wir rekursiv weiter, da max auch größer als sein Elternknoten sein könnte
			heapify(max);
		}
	}
	
	public void insert(int key)
	{
		if(arr.length != heapSize)
		{
			heapSize++;
			int i = heapSize-1;
			arr[i] = key;
			while(i > 0)
			{
				if(arr[parent(i)] < arr[i])
				{
					int temp = arr[parent(i)];
					arr[parent(i)] = arr[i];
					arr[i] = temp;
				}
				i = parent(i);
			}
		}
		else
		{
			System.out.println("Element kann nicht eingefuegt werden! Der Heap ist voll.");
		}
	}
	
	public int extractMax()
	{
		int max = arr[0];
		arr[0] = arr[heapSize-1];
		heapSize--;
		heapify(0);
		return max;
	}
	
	public void printHeap()
	{
		System.out.println(arr[0]);
		for(int i=1; i<heapSize; i++)
		{
			int p = (int) Math.pow(2,i)-1;
			for(int j=p; j<=2*p && j<heapSize; j++)
			{
				System.out.print(arr[j] + " ");
				if(j == 2*p)
				{
					System.out.println();
				}
			}
		}
	}
}