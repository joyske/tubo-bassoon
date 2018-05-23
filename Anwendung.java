import java.util.*;
import java.io.*;

public class Anwendung
{
    public static ArrayList<Interval> intervalScheduling(ArrayList<Interval> intervals)
    {
        ArrayList<Interval>result=new ArrayList<Interval>();
        if(intervals.isEmpty())
        {
            System.out.println("Es gibt kein Interval!");
        }
        else
        {
            int n = intervals.size();
			result.add(intervals.get(0));
            int j=0;
            for(int i=1;i<n;i++)
            {
                if(intervals.get(i).getStart()>=intervals.get(j).getEnd())
                {
                    result.add(intervals.get(i));
                    j=i;
                }
            }
        }
        return result;
        
    }
    public static void main(String[]args) 
    {
		if(args.length == 1)
		{
			String data = args[0];
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
					}
					catch(NumberFormatException e)
					{
						System.out.println("Ungueltige Eingabe! Bitte so: 'Zahl1,Zahl2'");
						return;
					}
					zeile = file.readLine();
				}
				sort(teste);
				System.out.println(intervalScheduling(teste));
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
			System.out.println("Bitte den Dateinamen bzw. den Pfad zu der Datei angeben!");
		}
	}
		
	public static void sort(ArrayList<Interval> list) 
	{
		sort(list, 0, list.size() - 1);
	}

	public static void sort(ArrayList<Interval> list, int from, int to) 
	{
		if (from < to) 
		{
			int pivot = from;
			int left = from + 1;
			int right = to;
			int pivotValue = list.get(pivot).getEnd();
			while (left <= right) 
			{
				// left <= to -> limit protection
				while (left <= to && pivotValue >= list.get(left).getEnd()) 
				{
					left++;
				}
				// right > from -> limit protection
				while (right > from && pivotValue < list.get(right).getEnd()) 
				{
					right--;
				}
				if (left < right) {
					Collections.swap(list, left, right);
				}
			}
			Collections.swap(list, pivot, left - 1);
			sort(list, from, right - 1); // <-- pivot was wrong!
			sort(list, right + 1, to);   // <-- pivot was wrong!
		}
	}
}
