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
	public static int[]latenessScheduling(ArrayList<Job>jobs)
	{
		int[]result=new int[jobs.size()];
		int late = 0;
		if(jobs.isEmpty())
		{
			System.out.println("Es gibt kein Interval!");
		}
		else
		{
			int n=jobs.size();
			int f = 0;
			int z=0;
			for(int i=0;i<n;i++)
			{
				result[i]=z;
				z=z+jobs.get(i).getDuration();
			}
			for(int j=0; j < result.length; j++)
			{
				f += jobs.get(j).getDuration();
				late = Math.max(0, f - jobs.get(j).getDeadline());
			}
			System.out.println(late);
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
			ArrayList<Job>teste2=new ArrayList<Job>();
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
						Job ijobb=new Job(start,ende);
						teste.add(ivall);
						teste2.add(ijobb);
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
					System.out.print("Sortiert: "); Collections.sort(teste); System.out.println(teste);
					System.out.println("Berechnetes Intervallscheduling: " + intervalScheduling(teste));
				}
				else if(mod.equals("Lateness"))
				{
					System.out.println("Bearbeite Datei '" + data + "'.");
					System.out.println("Es wurden " + c + " Zeilen mit folgendem Inhalt gelesen: " + teste);
					System.out.print("Sortiert: "); Collections.sort(teste2); System.out.println(teste2);
					int[]arr=latenessScheduling(teste2);
					System.out.print("Berechnetes Jobscheduling: [");
					for(int x : arr)
					{
						System.out.print( x + ", ");
					}
					System.out.print("]");
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
			catch (IllegalArgumentException ill)
			{
				System.out.println("Erste Zahl muss kleiner als zweite sein!");
			}
		}
		else
		{
			System.out.println("Bitte zuerst 'Interval' oder 'Lateness' eingeben, dann den Dateinamen bzw. den Pfad zu der Datei angeben!");
		}
	}
		
	
}
