import java.util.*;

public class LCS
{
	public static String randStr(int n, Random r)
	{
		String alphabet =
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder res = new StringBuilder(n);
		while(--n >= 0)
		{
			res.append(alphabet.charAt(r.nextInt(alphabet.length())));
		}
		return res.toString();
	}
	
	public static int[][] longestSequence(String X, String Y)
	{
		int m = X.length();
		int n = Y.length();
		int[][] C = new int[m][n];
		System.out.print("Die laengste Teilfolge lautet: ");
		for(int i=0; i<m; i++){ C[i][0] = 0; }
		for(int j=0; j<n; j++){ C[0][j] = 0; }
		for(int i=1; i<m; i++)
		{
			for(int j=1; j<n; j++)
			{
				computeLength(X,Y,C,i,j);
			}
		}
		return C;
	}
	
	public static void computeLength(String X, String Y, int[][] C, int i, int j)
	{
		if(X.charAt(i) == Y.charAt(j))
		{
			C[i][j] = C[i-1][j-1]+1;
			System.out.print(X.charAt(i));
		}
		else
		{
			if(C[i-1][j] >= C[i][j-1])
			{
				C[i][j] = C[i-1][j];
			}
			else
			{
				C[i][j] = C[i][j-1];
			}
		}
	}
	
	public static void main(String[] args)
	{
		if(args.length == 1)
		{
			try
			{
				Random r = new Random();
				int n = Integer.parseInt(args[0]);
				String seq1 = randStr(n, r);
				String seq2 = randStr(n, r);
				System.out.println(seq1 + " " + seq2); //NUR ZUM TESTEN
				longestSequence(seq1,seq2);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Bitte nur eine Zahl eingeben!");
			}
		}
		else
		{
			System.out.println("Bitte einen Parameter eingeben!");
		}
	}
}