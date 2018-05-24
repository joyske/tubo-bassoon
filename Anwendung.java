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
			//erstes Intervall in Ergebnisliste einfügen, da durch Sortierung
			//dieses das früheste Ende hat
			result.add(intervals.get(0));
            int j=0;
            for(int i=1;i<n;i++)
            {
		//wenn der Startwert an Stelle i größer gleich dem Endwert des vorherigen Intervalls (auf Überschneidung prüfen)
                if(intervals.get(i).getStart()>=intervals.get(j).getEnd())
                {
		    //wird das Intervall an Stelle i der Liste hinzugefügt (und die Überschneidungen übersprungen) 
                    result.add(intervals.get(i));
                    j=i;
                }
            }
        }
	//Die Ergebnisliste enthält die maximale Anzahl an kompatiblen (sich nicht überschneidenden) Intervallen     
        return result;
        
    }
	public static int[]latenessScheduling(ArrayList<Job>jobs)
	{
		//Ergebnisarray von der Größe der JobsListe wird angelegt
		int[]result=new int[jobs.size()];
		int late = 0;
		//Wenn keine Jobs übergeben werden gibt es eine Fehlermeldung
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
				//Startzeitpunkt des Jobs an der Stelle i wird in unser Ergbenisarray eingefügt 
				result[i]=z;
				//und dann um die Dauer des Jobs erhöht 
				//um den Startzeitpunkt für den nächsten Job zu erhalten 
				z=z+jobs.get(i).getDuration();
			}
			for(int j=0; j < result.length; j++)
			{
				//die Dauer der Jobs wird aufaddiert und in f gespeichert 
				f += jobs.get(j).getDuration();
				//damit man die Deadline des an der Stelle j betrachteten Jobs abziehen kann 
				//ist der Job also schneller als oder innerhalb der Deadline erledigt
				//wird late auf 0 gesetzt um das Ergbenis nicht zu verfäschen 
				//andernfalls könnte nähmlich z.B. eine Verspätung von -1 eine Verspätung von 1 ausgleichen  
				late = Math.max(0, f - jobs.get(j).getDeadline());
			}
			//am Ende erhalten wir die maximale Verspätung 			
			System.out.println(late);
		}
		//Das Array mit den Startzeitpunkten der jeweiligen Jobs wird zurückgegeben  
		return result;
		
	}
    public static void main(String[]args) 
    {
		if(args.length == 2)
		{
			//Zeilencounter wird deklariert 
			int c = 0;
			String mod = args[0];
			String data = args[1];
			ArrayList<Interval> teste = new ArrayList<Interval>();
			ArrayList<Job>teste2=new ArrayList<Job>();
			try
			{
				//Übergebener File wird eingelesen 
				BufferedReader file = new BufferedReader( new FileReader( data ) );
				String zeile = file.readLine();
				while(zeile != null)
				{
					StringTokenizer st = new StringTokenizer(zeile,",");
					try
					{
						//und in Integer Daten umgewandelt 
						int start = Integer.parseInt(st.nextToken());
						int ende = Integer.parseInt(st.nextToken());
						//anschließend werden daraus Intervalle und Jobs gemacht 
						Interval ivall = new Interval(start, ende);
						Job ijobb=new Job(start,ende);
						//Und in Listen eingefügt 
						teste.add(ivall);
						teste2.add(ijobb);
						//Zeilencounter wird hochgesetzt 
						c++;
					}
					//Wenn die Eingabe nicht den Vorgaben enspricht, wird ein Fehler ausgegeben 
					catch(NumberFormatException e)
					{
						System.out.println("Ungueltige Eingabe! Bitte so: 'Zahl1,Zahl2'");
						return;
					}
					zeile = file.readLine();
				}
				//Wenn Interval eingegeben wird, wird ein Intervallscheduling ausgegeben 
				if(mod.equals("Interval"))
				{
					System.out.println("Bearbeite Datei '" + data + "'.");
					System.out.println("Es wurden " + c + " Zeilen mit folgendem Inhalt gelesen: " + teste);
					System.out.print("Sortiert: "); Collections.sort(teste); System.out.println(teste);
					System.out.println("Berechnetes Intervallscheduling: " + intervalScheduling(teste));
				}
				//Wenn Lateness eingegeben wird, wird ein Jobscheduling ausgegeben 
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
				//Wenn keines der beiden eingegeben wird, gibt es eine Fehlermeldung 
				else
				{
					System.out.println("Bitte als ersten Parameter 'Interval' oder 'Lateness' eingeben!");
				}
			}
			//Es gibt einene Fehler wenn die Datei im eingegebenen Pfad nicht gefunden werden konnte 
			catch (FileNotFoundException ex)
			{
				System.out.println("Die Datei '" + data +"' konnte nicht gefunden werden!");
				return;
			} 
			//Fehlermeldung für falsche Ein- oder Ausgaben 
			catch (IOException io)
			{
				System.out.print("Fehlerhafte Ein- oder Ausgabe!");
				return;
			}
			//Fehlermeldung für ungültige Werte 
			catch (IllegalArgumentException ill)
			{
				System.out.println("Erste Zahl muss kleiner als zweite sein!");
			}
		}
	    	//Fehlermeldung für falsche Reihenfolge der Eingabe
		else
		{
			System.out.println("Bitte zuerst 'Interval' oder 'Lateness' eingeben, dann den Dateinamen bzw. den Pfad zu der Datei angeben!");
		}
	}
		
	
}
