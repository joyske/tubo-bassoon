public class Application
{
	//Methode zum Erstellen des Hindernisparcours mit Länge n und k Hindernissen
	public static boolean[] generateParcours(int n, int k)
	{
		//Boolean Feld, welches die Länge n+1 erhält und komplett mit true Werten gefüllt wird
		boolean[] parcours = new boolean[n+1];
		for(int i=0; i < parcours.length; i++)
		{
			parcours[i] = true;
		}
		//Setzen der "Hindernisse", mit einer zufälligen Zahl
		for(int i=0; i < k; i++)
		{
			java.util.Random numberGenerator = new java.util.Random();
			//Zufallszahl wird nur bis zur Länge des Parcours gewählt
			int randomNumber = numberGenerator.nextInt(parcours.length);
			//sollte an dieser Stelle im Feld bereits ein "Hindernis" stehen..
			//..oder die Zufallszahl ist der Anfang bzw das Ende des Feldes..
			//lassen wir eine neue Zufallszahl erwürfeln
			while(parcours[randomNumber] == false || randomNumber == 0 || randomNumber == parcours.length-1)
			{
				randomNumber = numberGenerator.nextInt(parcours.length);
			}
			parcours[randomNumber] = false;
		}
		return parcours;
	}
	
	//Methode für die grafische Darstellung
	public static void show(boolean[] parcours)
	{
		//Start der Grafik
		System.out.print("|");
		for(int i=0; i < parcours.length; i++)
		{
			//ist der Wert an der Stelle true, ist dieses Feld "frei"
			if(parcours[i])
			{
				System.out.print("  ");
			}
			//ansonsten befindet sich hier ein Hindernis, welches mit "XX"
			//gekennzeichnet wird
			else
			{
				System.out.print("XX");
			}
		}
		//Ende der Grafik
		System.out.print("|");
	}
	
	public static void main(String[] args)
	{
		//es müssen mindestens 3 Parameter eingegeben werden
		if(args.length == 3)
		{
			//n bestimmt die Länge des Feldes
			int n = Integer.parseInt(args[0]);
			//k bestimmt die Anzahl der Hindernisse
			int k = Integer.parseInt(args[1]);
			//r bestimmt die Anzahl der Schritte
			int r = Integer.parseInt(args[2]);
			boolean[] parcours = generateParcours(n,k);
			//bei einer Länge kleiner/gleich 50 geben wir alles grafisch aus
			if(n+1 <= 50)
			{
				System.out.print("Parcours: ");
				show(parcours);
				System.out.println();
			}
			//Methode zur Berechnung des kürzesten Weges
			MinStepCount min = new MinStepCount(parcours, 3);
			//NUR ZU TESTZWECKEN
			min.show();
		}
		else
		{
			System.out.println("Bitte genau drei Parameter eingeben!");
		}
	}
}