import java.util.*;  
public class LSC {
	
	public static int[][] LCSLength(String X, String Y) {
		int m = X.length(); 
		int n = Y.length(); 
		int [][] C = new int [m+1][n+1];
		
		for (int i = 0; i<=m; i++) {
			for (int j = 0; j<=n; j++) {
				CalcLength(X,Y,C,i,j); 
			}
		}
		return C; 
	}
	
	public static void CalcLength(String X, String Y, int[][] C, int i, int j) {
		if (i == 0 || j == 0) {
			C[i][j] = 0; 	
		}
		else if (X.charAt(i-1)==Y.charAt(j-1)) {
			C[i][j] = C[i-1][j-1] + 1;	
		}
		else {
			C[i][j] = Math.max(C[i-1][j], C[i][j-1]); 	
		}
	}
	
	public static String randStr(int n, Random r) {
		String alphabet =
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder res = new StringBuilder(n);
		while(--n >= 0) {
			res.append(alphabet.charAt(r.nextInt(alphabet.length())));
		}
		return res.toString();
	}
	
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
				int[][] result = new int[X.length()+1][Y.length()+1]; 
				result = LCSLength(X,Y);
				int[] lastRow = new int[Y.length()+1]; 
				int index = 0; 
				int var = 0; 
				int[] indexOf = new int[result[X.length()][Y.length()]];
				char[] ggTL = new char[indexOf.length]; 
				
				if(n<=40) {
					//Spalte 
					for (int j = 0; j<Y.length()+1; j++) {
						if (j == 0) {
							System.out.print("    ");
						}
						else if (j<Y.length()+1) {
							System.out.print(Y.charAt(j-1)+" ");
						}
						else {
							return; 
						}
					}
		
					System.out.println(); 
		
					//Zeile 
					for (int i = 0; i<X.length()+1; i++) {
						if (i == 0) {
							System.out.print("  ");
						}
						else if (i<X.length()+1) {
							System.out.print(X.charAt(i-1)+" ");
						}
						else {
							return; 
						} 
					
						//Berechnungstabelle
						for (int j = 0; j<Y.length()+1; j++) {
							System.out.print(result[i][j]+"|");
						}
						System.out.println(); 
					}
				}
				else{
					System.out.println("Erste Folge: "); 
					for (int c = 0; c<X.length(); c++) {
						System.out.print(X.charAt(c));
					}
					
					System.out.println(); 
					
					System.out.println("Zweite Folge: ");
					for (int d = 0; d<Y.length(); d++) {
						System.out.print(Y.charAt(d)); 
					}
					System.out.println();
				}
				
				System.out.println(); 
				
				//Laengste gemeinsam Teilfolge 
				for (int j = 0; j<result[X.length()].length; j++) {
					lastRow[j] = result[X.length()][j]; 
				}
		
				for (int i = 1; i<lastRow.length; i++) {
					if (lastRow[i]>lastRow[i-1]) {
						index = i-1;
						indexOf[var] = index;
						var++; 
					}
				}
		
				for (int x = 0; x<indexOf.length; x++) {
					ggTL[x] = Y.charAt(indexOf[x]); 
				}
				
				System.out.println("Die Laenge der laengsten Teilfolge ist: " +result[X.length()][Y.length()]);
				System.out.println();
				System.out.print("Eine laengste gemeinsame Teilfolge ist: ");
				for (char a : ggTL) {
					System.out.print(a +" "); 
				}
				System.out.println(); 
				System.out.print("Das Programm braucht "+measure(X,Y)+" Millisekunden!");
			}
			catch(NumberFormatException e) {
				System.out.println("Nur eine Zahl eingeben!");
			}
			catch (OutOfMemoryError e) {
            			System.out.println("Kleinere Zahl bitte!");
			}
		}
		else {
			System.out.println("Gib eine Zahl ein!");
		}
		
	}
} 
