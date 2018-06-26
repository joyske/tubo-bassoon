public class MaxHeap
{
	int[] arr;
	int heapSize;
	
	public MaxHeap(int n)
	{
		heapSize = 0;
		arr = new int[n];
	}
	
	public int left(int i)
	{
		return 2*i;
	}
	
	public int right(int i)
	{
		return 2*i+1;
	}
	
	public int parent(int i)
	{
		if(i == 0)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			return (i-1)/2;
		}
	}
	
	public void heapify(int i)
	{
		int l = left(i);
		int r = right(i);
		int max;
		if(l < heapSize && arr[l] > arr[i])
		{
			max = l;
		}
		else
		{
			max = i;
		}
		if(r < heapSize && arr[r] > arr[max])
		{
			max = r;
		}
		if(max != i)
		{
			int temp = arr[i];
			arr[i] = arr[max];
			arr[max] = temp;
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
	
	public static void main(String[] args)
	{
		try
		{
			int n = Integer.parseInt(args[0]);
			MaxHeap heap = new MaxHeap(n);
			heap.insert(16);
			heap.insert(14);
			heap.insert(10);
			heap.insert(8);
			heap.insert(7);
			heap.insert(9);
			heap.insert(3);
			heap.insert(2);
			heap.insert(4);
			heap.insert(1);
			heap.printHeap();
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Kein Vaterknoten!");
		}
	}
}