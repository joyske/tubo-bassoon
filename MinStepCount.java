public class MinStepCount
{
	//zweidimensionales Feld für die dynm. Programmierung
	int[][] C;
	public MinStepCount(boolean[] parcours, int r)
	{
		//initialisieren des Feldes mit Schrittzahlen und Länge des Parcours
		C = new int[r+1][parcours.length];
		for(int v=0; v < r+1; v++)
		{
			//starten im negativen Bereich (siehe Aufgabenstellung, Beispiel)
			for(int i=-2; i < parcours.length-2; i++)
			{
				//Basisfall, i und v sind 0
				if(i==0 && v==0)
				{
					set(i,v,0);
				}
				//befinden wir uns an einem Hindernis oder..
				//..ist i negativ, setzen wir unendlich in die "Tabelle"
				if(parcours[i+2] == false || i < 0)
				{
					set(i,v,Integer.MAX_VALUE);
				}
				else
				{
					
				}
			}
		}
	}
	
	//setzt Werte in die Tabelle
	public void set(int i, int v, int value)
	{
		C[v][i+2] = value;
	}
	
	//holt Werte aus der Tabelle
	public int get(int i, int v)
	{
		return C[v][i+2];
	}
	
	//NUR ZU TESTZWECKEN, NICHT RELEVANT
	public void show()
	{
		for(int i=0; i < C.length; i++)
		{
			System.out.print(C[i][0] + " ");
			for(int j=0; j < C[i].length; j++)
			{
				System.out.print(C[i][j] + " ");
			}
			System.out.println();
		}
	}
}