package Tema6;

public class Ej1 {

	private static int caballo(int[][] tablero, int i, int j, int passed) {
		int turns = 0;
		passed++;
		tablero[i][j] = 1;
		while (turns < 8 && passed < 64) {
			if (i - 2 > 0 && j - 1 > 0 && tablero[i - 2][j - 1] != 1)
				caballo(tablero, i - 2, j - 1, passed);
			if (i - 2 > 0 && j + 1 < tablero.length && tablero[i - 2][+1] != 1)
				caballo(tablero, i - 2, j + 1, passed);
			if (i-1 > 0 && j + 2 < tablero.length && tablero[i-1][j+2] != 1)
				caballo(tablero, i - 1, j + 2, passed);
			if (i + 1 <tablero.length && j + 1 <tablero.length && tablero[i+1][j+1] != 1)
				caballo(tablero, i +1, j + 1, passed);
			if (i + 2 <tablero.length && j + 1 <tablero.length && tablero[i+2][j+1] != 1)
				caballo(tablero, i +2, j + 1, passed);
			if (i + 2 <tablero.length && j - 1 > 0 && tablero[i+2][j-1] != 1)
				caballo(tablero, i + 2, j - 1, passed);
			if (i - 1 > 0 && j - 2 > 0 && tablero[i-1][j-2] != 1)
				caballo(tablero, i - 1, j - 2, passed);
			if (i + 1 < tablero.length && j - 2 > 0 && tablero[i+1][j-2] != 1)
				caballo(tablero, i + 1, j - 2, passed);
		}
		if(passed != 64) {
			tablero[i][j] = 0;
			passed--;
		}
		return passed;
	}
}
