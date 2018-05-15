public class Muenzwechselproblem
{
	public static void main(String[]args)
	{
		if(args.length==2)
		{
			int b=Integer.parseInt(args[1]);
			
			if(args[0].equals("Euro"))
			{
				int[] w = {200,100,50,20,10,5,2,1};
				int[]result=new int[8];
				result=change(b,w);
				show(result);

			}
			else if(args[0].equals("Alternative"))
			{
				int[] w ={200,100,50,20,10,5,4,2,1};
				int[]result=new int[9];
				result=change(b,w);
				show(result);
			}
			else
			{
				System.out.println("Bitte Euro oder Alternative eingeben");
			}
		}
		else
		{
			System.out.println("Bitte genau zwei Eingaben!");
		}
	}
	
	public static int[]change(int b,int[]w)
	{
		int[]result=new int[w.length];
		for(int i=0;i<w.length;i++)
		{
			while(w[i]<=b)
			{
				result[i]++;
				b-=w[i];
			}
		}
		return result;
	}
	
	public static void show(int[]arr)
	{
		System.out.print( "{");
		for(int x : arr)
		{
			System.out.print( x + ",");
		}
		System.out.print( "}");
	}
}