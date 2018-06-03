public class Election
{
	public static int elec(int[] arr)
	{
		int[][] D = new int[arr.length][2];
		int count = 1;
		D[arr.length-1][1] = arr[arr.length-1];
		D[arr.length-1][0] = arr[arr.length-1];
		for(int i=arr.length-2; i > 0; i--)
		{
			for(int j=1; j >= 0; j--)
			{
				if(j==0)
				{
					D[i][j] = D[i][1];
				}
				else
				{
					if(count != 2)
					{
						D[i][j] += arr[i] + D[i+1][0];
						count++;
					}
					else
					{
						D[i][j] = D[i+1][0];
						count=0;
					}
				}
			}
		}
		return D[1][1];
	}
	
	public static int elec2(int[] arr)
	{
		int n = arr.length;
		int[][] D = new int[n][2];
		int count = 1;
		D[n-1][0] = arr[n-1];
		D[n-1][1] = arr[n-1];
		for(int i=n-2; i > 0; i--)
		{
			if(count != 2)
			{
				D[i][1] += arr[i] + D[i+1][0];
				count++;
			}
			else
			{
				D[i][1] = D[i+1][0];
				count = 0;
			}
			D[i][0] = D[i][1];
		}
		return D[1][0];
	}
	
	public static void main(String[] args)
	{
		int[] arr = {0,2,3,4,5,6,7,8};
		System.out.println(elec(arr));
		System.out.println(elec2(arr));
	}
}