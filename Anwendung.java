import java.util.*;
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
        Interval eins=new Interval(1,13);
        Interval zwei=new Interval(2,4);
        Interval drei=new Interval(10,12);
        Interval vier=new Interval(8,9);
        Interval fuenf=new Interval(5,7);
		Interval[] arr = {eins, zwei, drei, vier, fuenf};
		ArrayList<Interval> teste = new ArrayList<Interval>();
		for(Interval a : arr)
		{
			teste.add(a);
		}
		sort(teste);
        System.out.println(intervalScheduling(teste));
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
