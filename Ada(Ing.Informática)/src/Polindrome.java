import java.util.Arrays;
import java.util.HashMap;

public class Polindrome {
	static final int MATRIX = 1;// if 1 shows a matrix of polindromes
	static final int VECTOR = 1;// if 1 shows vector of number of polindromes there is between 0 to n characters
								// of a string introduced.

	@SuppressWarnings("all")
	public static void Matriz(String ss) {
		if (ss.length() == 0 || ss == null)
			System.out.println(0);
		int[][] memory = new int[ss.length()][ss.length()];
		memory = transformation(memory, ss);
		int[] CounterString = new int[ss.length()];
		int n = ss.length();
		
		// Matrix construction:
		if (MATRIX == 1) {
			System.out.println("Matriz transformada es: ");
			for (int i = 0; i < ss.length(); i++) {
				for (int k = 0; k < ss.length(); k++) {
					if (k < i)
						System.out.print(0 + "|");
					else
						System.out.print(memory[i][k] + "|");
				}
				System.out.println("//");
			}
		}
		
		int counter = 1;
		System.out.println("La cadena se divide en: ");
		String[] letters = new String[ss.length()];
		for (int i = 0; i < n; i++) {
			if (memory[0][i] == 1) {
				CounterString[i] = 1;
				letters[i] = writer(ss,0,i);
			}
			else {
				CounterString[i] = Integer.MAX_VALUE;
				for (int j = 0; j < i; j++) {
					if (memory[j + 1][i] == 1 && 1 + CounterString[j] < CounterString[i]) {
						CounterString[i] = 1 + CounterString[j];
						if(j+1==i) {
							letters[i] = (letters[i-1] + "+" + writer(ss,j+1,i));
							counter++;
						}else letters[i] = ( letters [i-counter] +"+"+writer(ss,j+1,i));
						
					}
				}
			}
		}
//		for ( int i= 0;i<letters.length;i++) {
//			System.out.println(letters[i] +" -- ");
//		}
		System.out.println(letters[ss.length()-1]);
		if (VECTOR == 1) {
			System.out.println("El vector de divisones es el siguiente: ");
			for (int i = 0; i < CounterString.length; i++)
				if(i+1==CounterString.length)System.out.print(CounterString[i]);
				else System.out.print(CounterString[i] + "->");
			System.out.println();
		}
		System.out.println("El número mínimo de palíndromos es: " + CounterString[ss.length() - 1]);
	}

	private static String writer(String ss,int i,int j) {
		String ans = "";
		for(int k = i;k<=j;k++) {
		ans +=ss.charAt(k);
		}	
		return ans;
		
	}


	// Método que combprueba si el substring de ss que empiezaen f y acaba en l es
	// un políndromo.
	private static boolean PolindromeChecker(String ss, int f, int l) {
		boolean acr = true;
		while (f < l && acr) {
			acr = (Character.toUpperCase(ss.charAt(f)) == Character.toUpperCase(ss.charAt(l))) ? true : false;
			f++;
			l--;
		}
		return acr;
	}

	// Método que hace una matriz de los políndromos para todos los casos de
	// sub-string del string ss.
	private static int[][] transformation(int[][] M, String ss) {
		int len = ss.length();
		for (int i = 0; i < len; i++) {
			for (int q = i; q < len; q++) {
				if (PolindromeChecker(ss, i, q) || q == i)
					M[i][q] = 1;
			}
		}
		return M;
	}

	public static void main(String[] args) {
		String ss = "anaseesyou";
		Matriz(ss);
	}
}
