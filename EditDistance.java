public class EditDistance
{
	public static int distance(String a, String b)
	{
		int n=a.length();
		int m=b.length();
		int[][] d = new int[n + 1][m + 1];
		
		for(int i = 0; i <= n; i++)
		{
			d[i][0]=i;
		}
		for(int j = 0; j <= m; j++)
		{
			d[0][j]=j;
		}
		for (int i = 0; i < n; i++) 
		{
			char c1 = a.charAt(i);
			for (int j = 0; j < m; j++)
			{
					char c2 = b.charAt(j);
					
					if (c1 == c2)
					{
						d[i + 1][j + 1] = d[i][j];
					}
					else
					{
						int ersetzen = d[i][j] + 1;
						int einfuegen = d[i][j + 1] + 1;
						int loeschen = d[i + 1][j] + 1;
 
						int min = ersetzen > einfuegen ? einfuegen : ersetzen;
						min = loeschen > min ? min : loeschen;
						d[i + 1][j + 1] = min;
					}
			}
		}
 
		return d[n][m];
	}


	public static void main(String[]args)
	{
		if(args.length==2)
		{
			System.out.println("minimale Editierdistanz fuer die Transformation der ersten Eingabe:" + distance(args[0],args[1]));
		}
		else
		{
			System.out.println("Bitte genau zwei Eingaben!");
		}
	}
}
