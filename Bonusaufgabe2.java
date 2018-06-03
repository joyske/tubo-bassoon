
public class Bonuspunkte
{
	public static int bonus(int BP,int[]b,int[]p)
	{
		int n=p.length;
		int[][]A=new int[n+1][BP+1];
		for(int j=0;j<n;j++)
		{
			A[0][j]=0;
			
		}
		for(int i=1;i<=n;i++)
		{
			for(int w=1;w<=BP;w++)
			{
				A[i][w]=A[i-1][w];
				if(i-b[i-1]>=0)	
				{
					if(A[i][w]>p[i]+A[i-1][w-b[i]])
					{
					A[i][w] = p[i]+A[i-1][w-b[i]];
					}
				}
				
			}
		}
		return A[n][BP];
	}
	
	public static void main(String[]args)
	{
		int[]p={10,15,20,40};
		int []b={15,20,30,40};
		int BP=50;
		System.out.println(bonus(BP,b,p));
	}
	
}
