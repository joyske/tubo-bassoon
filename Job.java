public class Job implements Comparable<Job>
{
    int h;
    int dl;

    public Job(int duration, int deadline)
    { 
    	h=duration;
	dl=deadline;      
    }
    
    //Gettermethode für Dauer
    public int getDuration()
    {
        return h;
    }
    //Gettermethode für Deadline 
    public int getDeadline()
    {
        return dl;
    }
    //Methode zum ausprinten 
    public String toString()
    {
        String str="[" + h + "," + dl + "]";
        return str;
    }
	//Hilfsmethode zum Vergleichen von Job Elementen 
	//Notwending um sortieren zu können 
	public int compareTo(Job other)
	{
		if(this.getDeadline() == other.getDeadline())
		{
			return 0;
		}
		else if(this.getDeadline() > other.getDeadline())
		{
			return 1;
		}
		else
		{
			return -1;
		}
    }
}
