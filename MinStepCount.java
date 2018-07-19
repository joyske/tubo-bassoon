public class MinStepCount
{
	//zweidimensionales Feld für die dynm. Programmierung
	int[][] C;
	public MinStepCount(boolean[] parcours, int r)
	{
		//initialisieren des Feldes mit Schrittzahlen und Länge des Parcours
		C = new int[parcours.length+3][r+1];
		//starten im negativen Bereich (siehe Aufgabenstellung, Beispiel)
		for(int i=-3; i < 0; i++)
		{
			for(int v=0; v < r+1; v++)
			{
				set(i,v,Integer.MAX_VALUE);
			}
		}
		for(int i=0; i < parcours.length; i++)
		{
			for(int v=0; v < r+1; v++)
			{
				//Basisfall, i und v sind 0
				if(i==0 && v==0)
				{
					set(i,v,0);
				}
				//befinden wir uns an einem Hindernis oder..
				//..ist i negativ, setzen wir unendlich in die "Tabelle"
				else if(parcours[i] == false || i < 0)
				{
					set(i,v,Integer.MAX_VALUE);
				}
				else
				{
					int w = v;
					if(Math.max(0,v-1) <= w || w <= Math.min(r,v+1))
					{
						set(i,v,(Math.min(get(i-v,w), w))+1);
					}
				}
			}
		}
	}
	
	//setzt Werte in die Tabelle
	public void set(int i, int v, int value)
	{
		C[i+3][v] = value;
	}
	
	//holt Werte aus der Tabelle
	public int get(int i, int v)
	{
		return C[i+3][v];
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