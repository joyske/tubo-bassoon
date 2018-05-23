public class Job implements Comparable<Job>
{
    int h;
    int dl;

    public Job(int duration, int deadline)
    {   
       
        if(duration<=deadline)
        {
          h=duration;
          dl=deadline;
            
        }
        else
        {
			System.out.println("Dauer muss kleiner als Deadline sein");
            
		}
    }
    
    public int getDuration()
    {
        return h;
    }
    public int getDeadline()
    {
        return dl;
    }
    public String toString()
    {
        String str="[" + h + "," + dl + "]";
        return str;
    }
	
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
