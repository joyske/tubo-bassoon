public class Bonuspunkte
{
	public static int bonus(int W,int[]p,int[]b)
	{
		int n=b.length;
		int[][]A=new int[n+1][W+1];
		
		for(int i=0;i<=n;i++)
		{
			for(int w=0;w<=W;w++)
			{
				if(i==0||w==0)
				{
					A[i][w]=0;
				}
				else if(p[i-1]<=w)
				{
					A[i][w] = Math.min(b[i-1] + A[i-1][w-p[i-1]],  A[i-1][w]);
				}
				else
				{
                   A[i][w] = A[i-1][w];
         
				}
			}
		}
		return A[n][W];
	}
	
	public static void main(String[]args)
	{
		int[]b={10,10,20,40};
		int []p={15,10,20,5};
		int W=50;
		System.out.println(bonus(W,p,b));
	}
	
}
