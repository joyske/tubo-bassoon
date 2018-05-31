import java.util.*;  
public class LSC {
	
	public static int[][] LCSLength(String X, String Y) {
		int m = X.length(); 
		int n = Y.length(); 
		//Ergebnisarray mit extra Platz fuer die Befuellung mit 0
		int [][] C = new int [m+1][n+1];
		
		//X und Y werden gleichzeitig durchlaufen, verglichen
		//...und die Stellen wo es Uebereinstimmungen gibt werden 
		//zur Ermittlung der laengsten gemeinsamen Teilfolge 'makiert'
		for (int i = 0; i<=m; i++) {
			for (int j = 0; j<=n; j++) {
				CalcLength(X,Y,C,i,j); 
			}
		}
		//Berechnungstabelle 
		//Stelle C[m][n] = Laenge der laengsten gemeinsamen Teilfolge 
		return C; 
	}
	
	//Ermittlung der laenge der laengsten Teilfolge 
	public static void CalcLength(String X, String Y, int[][] C, int i, int j) {
		//Erste Reihe und erste Spalte werden mit 0 befüllt 
		if (i == 0 || j == 0) {
			C[i][j] = 0; 	
		}
		//Wenn es ein Match am selben Index gibt, wird die stelle makiert 
		//indem man den Wert an der Stelle um 1 erhoet	
		else if (X.charAt(i-1)==Y.charAt(j-1)) {
			C[i][j] = C[i-1][j-1] + 1;	
		} 
		else {
			C[i][j] = Math.max(C[i-1][j], C[i][j-1]); 	
		}
	}
	
	//printet die laengste Teilfolge auf die Konsole 
	public static void printLCS(String X, String Y, int l) {
		int m = X.length(); 
		int n = Y.length();
		int[][] L = new int[m+1][n+1]; 
		L = LCSLength(X,Y);
		int index = L[m][n]; 
		int temp = index; 
		//speichert die gewuenschte laengste Buchstabenfolge  
		char[] lcs = new char[index+1];  
		
		//Buchstaben werden einer nach dem anderen in lcs gespeichert  
		//von der rechten untersten Ecke an
		int i = m, j = n; 
		while(i>0 && j>0) {
			
			//Wenn Buchstabe in X[aktuell betrachtet] = Y[aktuell betrachtet] 	
			if (X.charAt(i-1) == Y.charAt(j-1)) {
				//...wird er in lcs aufgenommen
				lcs[index] = X.charAt(i-1);
				//und man schaut sich die Buchstaben davor an 
				i--; 
				j--; 
				index--; 
			}
			//Wenn Buchstaben nicht gleich sind 
			//bestimmen wir den groeßeren der beiden 
			//und gehen in dessen Richtung weiter 
			else if(L[i-1][j] > L[i][j-1]) {
				i--; 
			}
			else {
				j--; 
			}
		}
		
		//Fuer Eingabe<=40 wird zusaetzlich eine Berechnungstabelle ausgegeben
		if(l<=40) {
			//Spalte 
			for (int y = 0; y<n+1; y++) {
				if (y == 0) {
					System.out.print("    ");
				}
				else if (y<n+1) {
						System.out.print(Y.charAt(y-1)+" ");
				}
				else {
						return; 
				}
			}
		
			System.out.println(); 
		
			//Zeile 
			for (int x = 0; x<m+1; x++) {
				if (x == 0) {
					System.out.print("  ");
				}
				else if (x<m+1) {
						System.out.print(X.charAt(x-1)+" ");
				}
				else {
						return; 
				} 
					
				//Berechnungstabelle
				for (int b = 0; b<n+1; b++) {
					System.out.print(L[x][b]+"|");
				}
				System.out.println();
			}
		}
		
		System.out.println("Die Laenge der laengsten Teilfolge ist: " +temp);
		System.out.print("Eine der moeglichen laengsten Teilfolgen von " +X+ " und " +Y+ " ist: "); 
		for (int c = 0; c <=temp; c++) {
			System.out.print(lcs[c]); 
		}
		System.out.println(); 
		System.out.print("Das Programm braucht "+measure(X,Y)+" Millisekunden!");
	}
	
	//zufaellige Buchstabenfolge wird erstellt 
	public static String randStr(int n, Random r) {
		String alphabet =
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder res = new StringBuilder(n);
		while(--n >= 0) {
			res.append(alphabet.charAt(r.nextInt(alphabet.length())));
		}
		return res.toString();
	}
	
	//Messung der Laufzeit 
	public static long measure(String X, String Y) {
		long tStart, tEnd, msecs; 
		tStart = System.currentTimeMillis();
		LCSLength(X,Y);
		tEnd = System.currentTimeMillis();
		msecs = tEnd - tStart; 
		return msecs; 
	}
	
	public static void main(String[] args) { 
		if(args.length == 1) {
			try {
				Random r = new Random();
				int n = Integer.parseInt(args[0]);
				String X = randStr(n, r);
				String Y = randStr(n, r);
				printLCS(X,Y,n); 		
			}
			//Fehler wenn keine oder mehr als 1 Zahl eingegeben wird
			catch(NumberFormatException e) {
				System.out.println("Nur eine Zahl eingeben!");
			}
			//Fehler wenn zu große Zahl eingegeben wird 
			catch (OutOfMemoryError e) {
				System.out.println("Kleinere Zahl bitte!");
			}
		}
		//Fehler wenn nichts eingegeben wird 
		else {
			System.out.println("Gib eine Zahl ein!");
		}
	}
} 
