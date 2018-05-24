public class Interval implements Comparable<Interval>
{
	int s;
	int e;

	//Konstruktor zm Intervall zu erstellen
	//Überprüft ob start < ende, denn man kann nicht enden bevor man anfängt 
	public Interval(int start, int ende)
	{	
		if(start<=ende)
		{
			s=start;
			e=ende;
			
		}
		else
		{
			System.out.println("Start kann nicht größer als Ende sein!");
		}
	}
	
	//Gettermethode für Start
	public int getStart()
	{
		return s;
	}
	
	//Gettermethode für Ende 
	public int getEnd()
	{
		return e;
	}
	
	//Printmethode
	public String toString()
	{
		String str="["+ s + "," + e + "]";
		return str;
	}
	
	//Hilfsmethode zum Vergleichen von Intervall Elementen 
	//Notwending um sortieren zu können 
	public int compareTo(Interval other)
	{
		if(this.getEnd() == other.getEnd())
		{
			return 0;
		}
		else if(this.getEnd() > other.getEnd())
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}
	
}

