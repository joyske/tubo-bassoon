import java.util.*;
import java.io.*;

public class Anwendung
{
    public static ArrayList<Interval> intervalScheduling(ArrayList<Interval> intervals)
    {
		//neue Liste für Ausgabe wird erstellt
        ArrayList<Interval>result=new ArrayList<Interval>();
		//Fehlermeldung falls die übergebene Liste leer ist
        if(intervals.isEmpty())
        {
            System.out.println("Es gibt kein Interval!");
        }
        else
        {
            int n = intervals.size();
			//erstes Intervall in Liste einfügen, da durch Sortierung
			//dieses das früheste Ende hat
			result.add(intervals.get(0));
            int j=0;
            for(int i=1;i<n;i++)
            {
				//wenn der Endwert kleiner gleich dem Startwert einses Intervalls davor(auf Überschneidung prüfen)
                if(intervals.get(i).getStart()>=intervals.get(j).getEnd())
                {
					//ist das der Fall wird das Element der Liste hinzugefügt
                    result.add(intervals.get(i));
                    j=i;
                }
            }
        }
        return result;
        
    }
    public static void main(String[]args) 
    {
		if(args.length == 2)
		{
			int c = 0;
			String mod = args[0];
			String data = args[1];
			ArrayList<Interval> teste = new ArrayList<Interval>();
			try
			{
				BufferedReader file = new BufferedReader( new FileReader( data ) );
				String zeile = file.readLine();
				while(zeile != null)
				{
					StringTokenizer st = new StringTokenizer(zeile,",");
					try
					{
						int start = Integer.parseInt(st.nextToken());
						int ende = Integer.parseInt(st.nextToken());
						Interval ivall = new Interval(start, ende);
						teste.add(ivall);
						c++;
					}
					catch(NumberFormatException e)
					{
						System.out.println("Ungueltige Eingabe! Bitte so: 'Zahl1,Zahl2'");
						return;
					}
					zeile = file.readLine();
				}
				if(mod.equals("Interval"))
				{
					System.out.println("Bearbeite Datei '" + data + "'.");
					System.out.println("Es wurden " + c + " Zeilen mit folgendem Inhalt gelesen: " + teste);
					System.out.print("Sortiert: "); sort(teste); System.out.println(teste);
					System.out.println("Berechnetes Intervallscheduling: " + intervalScheduling(teste));
				}
				else if(mod.equals("Lateness"))
				{
					
				}
				else
				{
					System.out.println("Bitte als ersten Parameter 'Interval' oder 'Lateness' eingeben!");
				}
			}
			catch (FileNotFoundException ex)
			{
				System.out.println("Die Datei '" + data +"' konnte nicht gefunden werden!");
				return;
			}
			catch (IOException io)
			{
				System.out.print("Fehlerhafte Ein- oder Ausgabe!");
				return;
			}
		}
		else
		{
			System.out.println("Bitte zuerst 'Interval' oder 'Lateness' eingeben, dann den Dateinamen bzw. den Pfad zu der Datei angeben!");
		}
	}
		
	public static void sort(ArrayList<Interval> list) 
	{
		sort(list, 0, list.size() - 1);
	}

	public static void sort(ArrayList<Interval> list, int from, int to) 
	{
		//Sortierung solange links kleiner rechts
		if (from < to) 
		{
			int pivot = from;
			int left = from + 1;
			int right = to;
			int pivotValue = list.get(pivot).getEnd();
			while (left <= right) 
			{
				// left <= to -> Abfrage zur Sicherheit
				while (left <= to && pivotValue >= list.get(left).getEnd()) 
				{
					left++;
				}
				// right > from -> Abfrage zur Sicherheit
				while (right > from && pivotValue < list.get(right).getEnd()) 
				{
					right--;
				}
				if (left < right) {
					//Elemente werden getauscht
					Collections.swap(list, left, right);
				}
			}
			//Pivot ändert sich
			Collections.swap(list, pivot, left - 1);
			sort(list, from, right - 1); // <-- Pivot war falsch!
			sort(list, right + 1, to);   // <-- Pivot war falsch!
		}
	}
}