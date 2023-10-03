package Tema6;

public class Ej3 {
	private static boolean[][] l;
	private static int[][] tablero;
	//dentro del tablero aparecen obstáculos
	private static boolean method(int fila,int columna) {
		int pos = next(fila,columna);
		if(pos==l.length && fila+1<l.length) method(fila+1,0); // se toda la fila está llena de obstáculos.
		if(pos==l.length && fila-1==l.length) return true; // solución encotnrada
		int counter =0;
		boolean ans = false;
		while(pos+counter<tablero.length && !ans) {
			if(posib(fila,pos+counter)) {
				int aux = next(fila,pos+counter);
				if(aux==l.length) method(fila+1,0);
				else method(fila,pos+counter);
			}			
			counter++;
		}
		
		return ans;
	}
	
	// devuelve true si se puede poner segun fila
	private static boolean posib(int fila,int columna) {
		int counter = fila;
		boolean ans = false;
		while(counter>-1 && !ans) {
			ans = l[counter][columna] != false;
			ans = tablero[counter][columna] == 1;
		}
		counter = columna;
		if(!ans) {
			while(counter>-1 && ans){
				ans = l[fila][counter] != false;
				ans = tablero[fila][counter] == 1;
			}
		}
		
		return !ans;
	}
	
	private static int next(int fila, int pos) {
		int  ans = pos;
		while(l[fila][ans] && fila <l.length) ans++;
		return ans;
	}
}
