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
		for(int i=n-2; i >= 0; i--)
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
		return D[0][0];
	}
	
	public static int Min(int n, int[] b, int[] p, int B)
	{
		int[][] A = new int[n+1][B+1];
		for(int j=1; j < B+1; j++)
		{
			A[0][j] = Integer.MAX_VALUE;
		}
		for(int i=0; i < n+1; i++)
		{
			A[i][0] = 0;
		}
		for(int i=1; i < n; i++)
		{
			for(int j=1; j < B+1; j++)
			{
				A[i][j] = A[i-1][j];
				if(j-b[i] >= 0)
				{
					if(A[i][j] > p[i] + A[i-1][j-b[i]])
					{
						A[i][j] = p[i] + A[i-1][j-b[i]];
					}
				}
			}
		}
		return A[n][B];
	}
	
	public static int[][] gCount(int[] D, int p)
	{
		int n = D.length;
		int[][] M = new int[n+1][p+1];
		for(int z=1; z < p; z++)
		{
			M[0][z] = Integer.MAX_VALUE;
		}
		for(int i=0; i < n; i++)
		{
			M[i][0] = 0;
		}
		for(int i=1; i < n; i++)
		{
			for(int z=1; z < p; z++)
			{
				M[i][z] = M[i-1][z];
				if(z-D[i] >= 0)
				{
					if(M[i][z] > 1 + M[i-1][z-D[i]])
					{
						M[i][z] = 1 + M[i-1][z-D[i]];
					}
				}
			}
		}
		return M;
	}
	
	public static void main(String[] args)
	{
		int p = 10;
		int[] arr = {2,3,4,5,6};
		System.out.println(elec(arr));
		System.out.println(elec2(arr));
		//int[] p ={2,4,6,8,10};
		int[] b ={1,2,3,4,5};
		int n = b.length;
		//System.out.println(Min(p.length,b,p,10));
		int[][] result = new int[n+1][p+1];
		result = gCount(b, 10);
		for(int x=0; x<n+1;x++)
		{
			for(int y=0; y<p;y++)
			{
				System.out.print(result[x][y]+"|");
			}
			System.out.println();
		}
	}
}