public class Sequence
{
	public static int longestSeq(int[] arr, int current, int max, int oldmax)
	{
		if(current == 0)
		{
			if(max > oldmax)
			{
				return max;
			}
			else
			{
				return oldmax;
			}
		}
		else
		{
			if( (arr[current-1] % arr[current]) == 0)
			{
				return longestSeq(arr, current-1, max+1, oldmax);
			}
			else
			{
				if(max > oldmax)
				{
					return longestSeq(arr, current-1, 0, max);
				}
				else
				{
					return longestSeq(arr, current-1, 0, oldmax);
				}
			}
		}
	}
	
	public static int longestSeq(int[] arr)
	{
		int current = arr.length-1;
		int max = 0;
		int oldmax = 0;
		return longestSeq(arr, current, max, oldmax);
	}
	
	public static void main(String[] args)
	{
		int[] arr = {96, 48, 24, 12, 6, 4, 2, 1};
		System.out.println("Die laengste Sequenz betraegt: " + longestSeq(arr) + " (laengste Sequenz am Ende)");
		int[] arr2 = {20, 18, 9, 2};
		System.out.println("Die laengste Sequenz betraegt: " + longestSeq(arr2) + " (laengste Sequenz in der Mitte)");
		int[] arr3 = {12, 6, 4, 2, 1};
		System.out.println("Die laengste Sequenz betraegt: " + longestSeq(arr3) + " (laengste Sequenz am Anfang)");
	}
}