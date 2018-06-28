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
		//das Array darf noch nicht voll sein
		if(arr.length != heapSize)
		{
			//Anzahl der im Heap gespeicherten Elemente wird erhöht
			heapSize++;
			//fügen neuen Wert hinter dem zuletzt eingefügten ein
			int i = heapSize-1;
			arr[i] = key;
			//ist der am Ende des Arrays eingefügte Wert, größer als der Wert seines Elternknotens,
			//wird getauscht, dies geschieht solange bis wir die Heap-Eigenschaft wieder herstellen
			//konnten
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
			//ist das Array voll, kann kein weiteres Element eingefügt werden -> Fehlermeldung
			System.out.println("Element kann nicht eingefuegt werden! Der Heap ist voll.");
		}
	}
	
	public int extractMax()
	{
		//in einem Max-Heap ist das größte Element immer das erste
		int max = arr[0];
		//setzen an die erste Stelle, das Element was am Ende des Arrays steht
		arr[0] = arr[heapSize-1];
		//Heapsize muss verkleinert werden, da ein Element entfernt wurde
		heapSize--;
		//heapify stellt die Heap-Eigenschaft für die Wurzel und somit für den 
		//kompletten "Baum" wieder her
		heapify(0);
		return max;
	}
	
	public void printHeap()
	{
		if(heapSize > 0)
		{
			//erstes Element wird, unabhängig vom Rest, zuerst ausgegeben
			System.out.println(arr[0]);
			for(int i=1; i<heapSize; i++)
			{
				//das Element am weitesten links im Baum ist immer am Index (2^i)-1
				int p = (int) Math.pow(2,i)-1;
				//starten bei p und laufen bis 2*p, da dies immer der Index des 
				//letzten Elements der Reihe im Baum ist, außer j ist kleiner als die
				//Heapsize, dann beenden wir die print-Methode früher
				for(int j=p; j<=2*p && j<heapSize; j++)
				{
					System.out.print(arr[j] + " ");
					//Zeilenumbruch wenn 2*p erreicht ist
					if(j == 2*p)
					{
						System.out.println();
					}
				}
			}
		}
		else
		{
			//wenn der Heap kein Element enthält, geben wir einen Fehler aus
			System.out.println("Keine Elemente zum printen vorhanden!");
		}
	}
}