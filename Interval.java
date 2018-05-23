public class Interval implements Comparable<Interval>
{
	int s;
	int e;

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
	
	public int getStart()
	{
		return s;
	}
	
	public int getEnd()
	{
		return e;
	}
	
	public String toString()
	{
		String str="["+ s + ", " + e + "]";
		return str;
	}
	
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

