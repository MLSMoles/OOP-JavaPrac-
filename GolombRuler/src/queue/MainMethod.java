package queue;
import java.util.Random;
import java.util.Scanner; 
public class MainMethod {
	
	static int[][] MatrixGenerator(int n) {
		Random random= new Random();
		int[][] m = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (i == j) {
					m[i][j] = 0;
				}else {
					int rand = random.nextInt(10);
					while (rand == 0) {
						rand = random.nextInt(10);
					}
					m[i][j] = rand;
					m[j][i] = rand;
				}
			}
		}
		return m;
	}
	
	static int[][] TriangMatrix(int n){
		Random random = new Random();
		int[][] m = new int[n][n];
		for(int i=0; i<n;i++) {
			for(int j = i; j<n;j++) {
				if(i==j) {
					m[i][j]=0;
				}else {
					int rand = 0;
					while(rand == 0) {
						rand = random.nextInt(10);
					}
					m[i][j] = rand;
					m[j][i] = 0;
				}
			}
		}
		return null;
	}
	
	static String toString(int[][] m) {
		char[] alp = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		int n = m.length;
		String p = "";
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0) {
					if (j == 0) {
						p += "  " + alp[j] + " ";
					} else {
						p += alp[j] + " ";
					}
				} else if (j == 0) {
					p += alp[j] + " " + m[i][j] + " ";
				} else {
					p += (m[i][j] + " ");
				}
			}
			System.out.println(p);
			p = "";
		}
		return p;
	}
	
	static String linkage(int[][] m) {
		int n = m.length;
		int fila = 0;
		int columna = 0;
		int max = 0;
		String p = "";
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < n; j++) {
				if (max < m[i][j]) {
					max = m[i][j];
					columna = j;
					fila = j;
					p += "(" + j + ", ";
				}

			}
		}
		return "";
	}
	
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Value of n: ");
		
		int n= sc.nextInt();
		
		int[][] m = new int[n][n];
		
		m=MatrixGenerator(n);
		int[][] mm = new int[n][n];
		mm = TriangMatrix(n);
		System.out.println(toString(m));
		System.out.println(toString(mm));
		
		
		
		//Matriz triangular superior 
//		int n=7;
//		int[][] m = new int[n][n];
//		int[][] realm= new int[n][n];
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<n;j++) {
//				if(j<=i) {
//					m[i][j]=0;
//				}else {
//					m[i][j]=random.nextInt(10);
//					
//				}
//			}
//		}

	}
}
