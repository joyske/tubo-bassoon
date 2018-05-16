public class Muenzwechselproblem
{
	public static void main(String[]args)
	{
		//es müssen genau 2 Eingaben eingelesen werden können... 
		if(args.length==2)
		{
			//zweite Eingabe ist unsere Geldsumme die wir gewechselt haben wollen  
			int b=Integer.parseInt(args[1]);
			
			//erste Eingabe ist unsere Währung, Euro 
			if(args[0].equals("Euro"))
			{
				int[] w = {200,100,50,20,10,5,2,1};
				int[]result=new int[8];
				result=change(b,w);
				show(result);

			}
			//... oder 'Alternative'
			else if(args[0].equals("Alternative"))
			{
				int[] w ={200,100,50,20,10,5,4,2,1};
				int[]result=new int[9];
				result=change(b,w);
				show(result);
			}
			//wenn keines der beiden übergeben wird, gibt es eine Fehlermeldung  
			else
			{
				System.out.println("Bitte Euro oder Alternative eingeben");
			}
		}
		//....ansonsten gibt es eine Fehlermeldung  
		else
		{
			System.out.println("Bitte genau zwei Eingaben!");
		}
	}
	
	public static int[]change(int b,int[]w)
	{
		//es wird ein Array von der Größe der Währung erstellt: 8 für Euro, 9 für Alternative  
		int[]result=new int[w.length];
		//wir iterieren durch w...
		for(int i=0;i<w.length;i++)
		{
			 		
			while(w[i]<=b)
			{
				//...und wenn wir einen Wert in w finden, der kleiner ist als unsere Geldsumme 
				//wird an genau den gleichen Index in result gezählt wie oft dieser Wert in der Geldsumme hineinpasst
				result[i]++;
				//das geschieht indem wir den Wert immer wieder von unserer Geldsumme abziehen 
				b-=w[i];
			}
		}
		//am Ende ist result gefüllt mit Zahlen, die angeben welche Scheine/Münzen (erkennt man am Index) 
		//wie oft (erkennt man an der Zahl) in unsere Geldsumme hineinpassen 
		return result;
	}
	
	//zeigt die Werte in einem Array auf der Konsole 
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
