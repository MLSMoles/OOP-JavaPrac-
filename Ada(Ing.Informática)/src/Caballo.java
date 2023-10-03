import java.util.LinkedList;
import java.util.List;
import java.io.File; // Import the File class
import java.io.FileWriter;
import java.io.IOException; // Import the IOException class to handle errors

public class Caballo {
	private int n; // tamaño del tablero
	private static int[][] tablero;
	private static int num = 0;
	private static List<int[][]> ans = new LinkedList<>();
	private static int number = 0;
	// Se pueden añadir propiedades adicionales a la clase, pero NO eliminar ninguna
	// de las existentes.

	public int getN() {
		return n;
	}

	public Caballo(int tam) {
		n = tam;
		tablero = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				tablero[i][j] = 0;
			}

	}

// (x,y) son las coordenadas desde donde
// empieza a moverse el caballo.
//@pre: (0<=x)&&(x<n)&&(0<=y)&&(y<n)
//Devuelve true si se puede poner
	private static boolean PossibleToPut(int i, int j) {
		int len1 = tablero.length;
		int len2 = tablero[0].length;
		return (0 <= i && i < len1 && 0 <= j && j < len2 && tablero[i][j] == 0);
	}

	private static int[][] copy(int[][] l) {
		int[][] ans = new int[l.length][l.length];
		for (int i = 0; i < l.length; i++) {
			for (int j = 0; j < l.length; j++) {
				ans[i][j] = l[i][j];
			}
		}
		return ans;
	}
	private static void string(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				if (m[i][j] <= 9)
					System.out.print(m[i][j] + " ");
				else
					System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("---------");
	}

	public static void main(String[] args) {
		Caballo c = new Caballo(5);
		resolverTodos(1, 3);
		System.out.println(ans.isEmpty());
		System.out.println(number);
		writer(ans);
	}

	public static void writer(List<int[][]> list) {
		try {
			File myObj = new File("Mat.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		try {
			FileWriter myWriter = new FileWriter("Mat.txt");
			for (int[][] mat : list) {
				for (int i = 0; i < mat.length; i++) {
					for (int j = 0; j < mat.length; j++) {
						if(mat[i][j] <10)	myWriter.write(mat[i][j] + " | ");
					}
					myWriter.write(System.lineSeparator());
				}
				myWriter.write("_____________________");
				myWriter.write(System.lineSeparator());
			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}
	
//devuelve en una lista TODOS los tableros solucion.
	public static List<int[][]> resolverTodos(int x, int y) {

		if (x > -1 && y > -1 && x < tablero.length && y < tablero.length) {

			num++;
			tablero[x][y] = num;
			int len = tablero.length;
			if (num == Math.pow(len, 2)) {
				int[][] aux = copy(tablero);
				ans.add(aux);
				number++;

			}
			if (PossibleToPut(x - 2, y - 1))
				resolverTodos(x - 2, y - 1);

			if (PossibleToPut(x - 2, y + 1))
				resolverTodos(x - 2, y + 1);

			if (PossibleToPut(x - 1, y + 2))
				resolverTodos(x - 1, y + 2);

			if (PossibleToPut(x + 1, y + 2))
				resolverTodos(x + 1, y + 2);

			if (PossibleToPut(x + 2, y + 1))
				resolverTodos(x + 2, y + 1);

			if (PossibleToPut(x + 2, y - 1))
				resolverTodos(x + 2, y - 1);

			if (PossibleToPut(x + 1, y - 2))
				resolverTodos(x + 1, y - 2);

			if (PossibleToPut(x - 1, y - 2))
				resolverTodos(x - 1, y - 2);
			tablero[x][y] = 0;
			num--;
			return ans;
		} else
			return ans;
	}

	

} // fin de class Caballo
