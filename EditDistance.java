import java.util.*;  

public class EditDistance {
	public static int [][] distance(String a, String b) {
		int n=a.length();
		int m=b.length();
		int[][] d = new int[n + 1][m + 1];
		
		for(int i = 0; i <= n; i++) {
			d[i][0]=i;
		}
		for(int j = 0; j <= m; j++) {
			d[0][j]=j;
		}
		for (int i = 0; i < n; i++) {
			char c1 = a.charAt(i);
			for (int j = 0; j < m; j++) {
					char c2 = b.charAt(j);
					
					if (c1 == c2) {
						d[i + 1][j + 1] = d[i][j];
					}
					else {
						int ersetzen = d[i][j] + 1;
						int einfuegen = d[i + 1][j] + 1;
						int loeschen = d[i][j + 1] + 1;
 
						int min = ersetzen > einfuegen ? einfuegen : ersetzen;
						min = loeschen > min ? min : loeschen;
						d[i + 1][j + 1] = min;
					}
			}
		}
		return d;
	}
	
	public static void printEditOperations(String A, String B, int mod) {
		int m = A.length(); 
		int n = B.length();
		int[][] D = new int[m+1][n+1]; 
		D = distance(A,B);
		StringBuilder sbA = new StringBuilder(A); 
		int [] cost = new int[m+1]; 
		String [] operand = new String [m+1]; 
		
		if (mod == 0) {
			//Spalte 
			for (int i = 0; i <= n; i++) {
				if (i == 0) {
					System.out.print("    ");
				}
				else if (i<n+1) {
						System.out.print(B.charAt(i-1)+" ");
				}
				else {
						return; 
				}
			}
			
			System.out.println(); 
			
			//Zeile 
			for (int j = 0; j <= m; j++) {
				if (j == 0) {
					System.out.print("  ");
				}
				else if (j<m+1) {
						System.out.print(A.charAt(j-1)+" ");
				}
				else {
						return; 
				}
				
				//Berechnungstabelle
				for (int d = 0; d<n+1; d++) {
					System.out.print(D[j][d]+"|");
				}
				System.out.println();
			}
			System.out.println("die minimale Editierdistanz fuer " +A +" und " +B +" ist : " +D[m][n]); 
		}
		/**else {
			//wird durchlaufen die Berechnungstabelle rueckwaerts 
			//um unsere Schritte zurueck zu verfolgen	
			while (m>0 && n>0) {
				//wenn nichts veraendert wurde, gehen wir 1 diagonall hoch
				if (D[m][n] == D[m-1][n-1]) {
					operand[m-1] = "an Position"; 
					cost[m-1] = 0; 
					m--; 
					n--;
					System.out.println( A.charAt(m-1)+ "  " + operand[m-1] + m-1 +" --> " +sbA.reverse()); 
				}
				//wenn geloescht wurde, gehen wir 1 nach Links 
				else if (D[m][n] == D[m-1][n]+1) {
					operand[m-1] = "loesche"; 
					cost[m-1] = 1;
					sbA.deleteCharAt(m-1);					
					n--;
					System.out.println( operand[m-1] + A.charAt(m-1)+ "an Position" + m-1 +" ein " +" --> " +sbA.reverse());
				}
				//wenn eingefuegt wurde, gehen wir 1 hoch   
				else if (D[m][n] == D[m][n-1]+1) {
					operand[m-1] = "fuege"; 
					cost[m-1] = 1;
					sbA.insert(m, B.charAt(n-1)); 
					m--;
					System.out.println( operand[m-1] + B.charAt(n-1)+ "and Position" + m-1 +" ein " +" --> " +sbA.reverse());
				}
				//wenn ersetzt wurde, gehen wir 1 diagonall hoch
				else if (D[m][n] == D[m-1][n-1]+1) {
					operand [m-1] = "ersetze"; 
					cost[m-1] = 1;
					sbA.setCharAt(m-1, B.charAt(n-1)); 
					m--; 
					n--;
					System.out.println( operand[m-1] +"  " + A.charAt(m-1)+ " durch " + B.charAt(n-1)+ " an Position " + m-1 +" --> " +sbA.reverse());
				}
				
			}
		}*/
		
		
	}


	public static void main(String[]args) {
		if(args.length > 1 && args.length <= 3) {
			String A = args[0]; 
			String B = args[1]; 
			int mod; 
			
			if (args.length == 3 && args[2].equals("-o")) {
				mod = 1; 
			}
			else if (args.length == 3){
				System.out.println("Fuer eine detalliertere Ausgabe fuege noch '-o' hinter dem zweiten String ein!");
				return; 
			}
			else {
				mod = 0; 
			}
			
			printEditOperations(A, B, mod);
		}
		else {
			System.out.println("Bitte zwei Strings eingeben!");
			System.out.println("Fuer eine detalliertere Ausgabe fuege noch '-o' hinter dem zweiten String ein.");
		}
	}
}
