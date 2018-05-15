
import java.util.*;

public class Sortierung3
{	
	public static void insertionSort(int[] array)
	{
		int key, i;
		//Feld wird ab Index 1 durchgelaufen, da mit j-1 verglichen wird
		for(int j = 1; j < array.length; j++)
		{
			key = array[j];
			i = j-1;
			assert i > 0;
			//Feld wird solange durchlaufen bis alle Werte <= key links von diesem eingefügt wurden
			while(i >= 0 && array[i] > key)
			{
				array[i+1] = array[i];
				i--;
			}
			//key wird in Feld eingefügt
			array[i+1] = key;
		}
	}
	
	public static boolean isSorted(int[] array)
	{
		for(int i = 0; i < array.length-1; i++)
		{
			//überprüft ob alle Werte im Feld sortiert sind
			if(array[i+1] < array[i])
			{
				return false;
			}
		}
		return true;
	}
	
	public static void measure(int[] array, int modus)
	{
		long tStart, tEnd, msecs;
		//Modus 0 = Laufzeit von InsertionSort soll gemessen werden
		if(modus == 0)
		{
			//Start der Messung der Laufzeit
			tStart = System.currentTimeMillis();
			insertionSort(array);
			//Ende der Messung
			tEnd = System.currentTimeMillis();
		}
		else if(modus==1)
		{
			//Modus 1 = Laufzeit von MergeSort soll gemessen werden
			tStart = System.currentTimeMillis();
			mergeSort(array);
			tEnd = System.currentTimeMillis();
		}
		else
		{
			tStart = System.currentTimeMillis();
			quickSort(array);
			tEnd = System.currentTimeMillis();
			
		}
		if(array.length <= 100)
		{
			//jede Zahl wird ausgegeben (bei einer Arraygröße von 100 oder weniger)
			for(int z : array)
			{
				System.out.print(z + " ");
			}
			System.out.println();
		}
		System.out.println("Laufzeit: " + (msecs = tEnd - tStart));
	}
	
	public static void sorted(int[] array)
	{
		//zeigt an ob Feld sortiert wurde
		if(isSorted(array))
		{
			System.out.println("Feld sortiert!");
		}
		else
		{
			System.out.println("Feld NICHT sortiert!");
		}		
	}
	
	public static void makeRandomArray(int[] array, int modus)
	{
		//Hilfe zur Erstellung von zufälligen Zahlen
		java.util.Random numberGenerator = new java.util.Random();
		//fügt Zufallszahlen nacheinander in Feld ein
		for(int i = 0; i < array.length; i++)
		{
			array[i] = numberGenerator.nextInt();
		}
		//sortiert und misst Laufzeit für den Array bei angegebenen Modus (0 = InsertionSort, sonst = MergeSort)
		measure(array, modus);
		//gibt einen kurzen Text aus, ob Feld sortiert ist
		sorted(array);
	}
	
	public static void main(String[] args)
	{
		try
		{
			//prüft ob es weniger als 2 oder mehr als 3 Eingaben gibt
			if(args.length >= 2 && args.length <= 3)
			{
				int a = Integer.parseInt(args[0]);
				//assert args[0] > 0: "Du musst die Größe des Feldes eingeben!";
				//a gibt die Länge des Arrays an, diese darf weder 0 noch negativ sein
				if(a <= 0)
				{
					System.out.println("Bitte eine Zahl groeßer als 0 eingeben!");
					//return beendet an dieser Stelle das Programm
					return;
				}
				//die zweite Eingabe soll immer ein String, mit den Werten 'insert' oder 'merge', sein
				String b = args[1];
				int[] array = new int[a];
				//bei zwei Eingaben wird Array mit Zufallszahlen erstellt
				if(args.length == 2)
				{
					if(args[1].equals("insert"))
					{
						//erzeugt mit zufälligen Zahlen gefüllten Array und sortiert direkt mit InsertionSort (Modus = 0)
						makeRandomArray(array, 0);
					}
					else if(args[1].equals("merge"))
					{
						//erzeugt mit zufälligen Zahlen gefüllten Array und sortiert direkt mit MergeSort (Modus != 0)
						makeRandomArray(array, 1);
					}else if(args[1].equals("quick"))
					{
						//erzeugt mit zufälligen Zahlen gefüllten Array und sortiert direkt mit MergeSort (Modus != 0)
						makeRandomArray(array, 2);
					}
					
					else
					{
						//bei anderen Eingaben als 'insert' oder 'merge' geben wir den Fehler aus
						System.out.println("Bitte 'insert' oder 'merge' eingeben!");
						return;
					}
				}
				//bei einer dritten Eingabe überprüfen wir ob es sich um die Wörter 'auf', 'ab' oder 'rand' handelt
				if(args.length == 3)
				{
					String c = args[2];
					switch(c)
					{
						case "auf" :
							//füllen einen Array mit aufsteigend sortierten Zahlen
							for(int i = 0; i < array.length; i++)
							{
								//Werte von vorne nach hinten einfügen
								array[i] = i+1;
							}
							//führen je nach Eingabe InsertionSort oder MergeSort auf den Array aus
							if(args[1].equals("insert"))
							{
								//sortiert und misst die Laufzeit für InsertionSort
								measure(array, 0);
							}
							else if(args[1].equals("merge"))
							{
								//sortiert und misst die Laufzeit für MergeSort
								measure(array, 1);
							}
							sorted(array);
							break;
						case "ab" : 
							//füllen einen Array mit absteigend sortierten Zahlen
							for(int j = 0; j < array.length; j++)
							{
								//Werte von hinten nach vorne einfügen
								array[j] = a;
								a--;
							}
							//führen je nach Eingabe InsertionSort oder MergeSort auf den Array aus
							if(args[1].equals("insert"))
							{
								measure(array, 0);
							}
							else if(args[1].equals("merge"))
							{
								measure(array, 1);
							}
							sorted(array);
							break;
						case "rand" :
							//füllen einen Array mit zufälligen Zahlen
							if(args[1].equals("insert"))
							{
								makeRandomArray(array, 0);
							}
							else if(args[1].equals("merge"))
							{
								makeRandomArray(array, 1);
							}
							else if(args[1].equals("quick"))
							{
								makeRandomArray(array,2);
							}
							break;
						//wurde etwas anderes als 'auf', 'ab', 'rand' angegeben, erzeugen wir einen Fehler
						default: System.out.println("Entweder 'auf', 'ab' oder 'rand' als Parameter verwenden!");
						//c kann nur drei gültige Werte haben
						assert false : c;
					}
				}
			}
			else
			{
				System.out.println("Bitte mindestens zwei, hoechstens drei Parameter eingeben!");
				return;
			}
		}
		//bei fehlerhafter erster Eingabe (keine Zahl), wird der Fehler gefangen und eine Meldung ausgegeben
		catch(NumberFormatException e)
		{
			System.out.println("Bitte zuerst eine Zahl eingeben!");
		}
	}
	
	public static void mergeSort(int[] array)
	{
		int[] tmpArray = new int[array.length];
		mergeSort(array, tmpArray, 0, array.length-1);
		assert isSorted(array);
	}
		
	public static void mergeSort(int[] array, int[]tmpArray, int left, int right)
	{
		if(left < right)
		{
			//Feld wird in zwei Hälften geteilt
			int middle = (left+right)/2;
			//linke Seite wird sortiert
			mergeSort(array, tmpArray, left, middle);
			//rechte Seite wird sortiert
			mergeSort(array, tmpArray, middle+1, right);
			//Hälfte zusammenfügen und sortieren
			merge(array, tmpArray, left, middle, right);
		}
	}
		
	public static void merge(int[] array, int[]tmpArray, int left, int middle, int right)
	{
		//Array in TmpArray kopieren
		for (int i = left; i <= right; i++) {
            tmpArray[i] = array[i];
        }
		//Hilfsvariablen die für den merge Vorgang relevant sind
		int i = left;
		int j = middle + 1;
		int k = left;
		//Hälften durchlaufen
		while (i <= middle && j <= right) 
		{
			//kopieren der kleinsten Zahl aus rechter oder linker Hälfte zurück in Array
			if (tmpArray[i] <= tmpArray[j]) 
			{
				array[k] = tmpArray[i];
				i++;
			} 
			else 
			{
				array[k] = tmpArray[j];
				j++;
			}
			k++;
		}
		//kopiert den Rest der linken Hälfte in Array(rechte seiten bereits richtig sortiert)
		while (i <= middle) 
		{
			array[k] = tmpArray[i];
			k++;
			i++;
		}
	}
	public static void quickSort(int[] array)
	{
		
		Quicksort(array, 0, array.length-1);
		assert isSorted(array);
	}
	
	public static void Quicksort(int[]arr, int l, int r)
	{
		if(l<r)
		{
			int i=l;
			int j=r;
			int pivot=arr[(l+r)/2];
			
			while(i<=j)
			{
				while(arr[i]<pivot)
				{
					i=i+1;
				}
				while(arr[j]>pivot)
				{
					j=j-1;
					
				}
				if(i<=j)
				{
					int tmp=arr[i];
					arr[i]=arr[j];
					arr[j]=tmp;
					i=i+1;
					j=j-1;
					
				}
			}
			Quicksort(arr,l,j);
			Quicksort(arr,i,r);
		}
			
	}
}

   
