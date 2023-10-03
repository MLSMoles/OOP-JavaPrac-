import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class CambioMonedas {
	private int[] sol;
	private int[] denom;
	private int[] cantidad;
	private int M;
	private int num_monedas; // El coste de la solución actual
	private static int value = 0;
	private static int index = 0;
	private static int auxiliarCounterMonedas = 0;
	private static int[] auxVector;

	/*
	 * Constructor de la clase
	 */
	public CambioMonedas(int[] readVector, int[] readVector2, int[] readVector3) {
		sol = new int[readVector.length];
		denom = readVector;
		cantidad = readVector2;
		M = readVector3[0];
		auxVector = new int[sol.length];
		num_monedas = Integer.MAX_VALUE; // Iniciamos el número de monedas a infinito para indicar que no tenemos aún
											// solución.
											// Si el problema no tiene solución, esta propiedad no debería actualizarse.
	}

	/*
	 * Dado un objeto instanciado de la clase CambioMonedas, este método haya la
	 * solución óptima.
	 */

	void vueltaAtras() {
		if (index + 1 < denom.length) {
			index++;
			vueltaAtras();
			index--;
		}
		if (M == value) {
			if (num_monedas == Integer.MAX_VALUE || num_monedas > auxiliarCounterMonedas) {
				num_monedas = 0;
				num_monedas += auxiliarCounterMonedas;
				sol = auxVector.clone();
			}
		} else {
			int monedas = 0;
			int counter = 0;
			while (monedas <= cantidad[index]) {
				if (M - value >= denom[index] && cantidad[index] > 0 && monedas <= cantidad[index]) {
					value += denom[index];
					auxVector[index] += 1;
					auxiliarCounterMonedas++;
					counter++;
				}
				if (M == value) {
					if (num_monedas == Integer.MAX_VALUE || num_monedas > auxiliarCounterMonedas) {
						num_monedas = 0;
						num_monedas += auxiliarCounterMonedas;
						sol = auxVector.clone();
					}
				}
				if (index + 1 < denom.length) {
					index++;
					vueltaAtras();
					index--;
				}
				monedas++;
			}
			value = value - (counter * denom[index]);
			auxiliarCounterMonedas -= counter;
			auxVector[index] = 0;
		}
	}

	public static String battle(String goodAmounts, String evilAmounts) {
	    String[] aux1 = goodAmounts.split(" ");
	    String[] aux2 = evilAmounts.split(" ");
	    int counter1 =0;
    for(int i=0;i<aux1.length;i++){
      switch(i){
          case(0): counter1 += Integer.parseInt(aux1[i]);
          break;
          case(1): counter1 += Integer.parseInt(aux1[i])*2;
          break;
          case(4): counter1 += Integer.parseInt(aux1[i])*4;
          break;
          case(5): counter1 += Integer.parseInt(aux1[i])*10;
          break;
          default: counter1 += Integer.parseInt(aux1[i])*3;
      }
	    }
	    for(int i=0;i<aux2.length;i++){
        switch(i){
          case(0): counter1 += Integer.parseInt(aux1[i]);
          break;
          case(5): counter1 -= Integer.parseInt(aux1[i])*3;
          break;
          case(6): counter1 -= Integer.parseInt(aux1[i])*5;
          break;
          case(7): counter1 -= Integer.parseInt(aux1[i])*10;
          break;
          default: counter1 -= Integer.parseInt(aux1[i])*2;
      }		    }
	    if(counter1>0) return "Battle Result: Good triumphs over Evil";
	    else if( counter1<0) return "Battle Result: Evil eradicates all trace of Good";
	    else return "Battle Result: No victor on this battle field";
	  }
	
	 public static String print(int n) {
		    String ans = "";
		    int aux = 1;
		      while(aux<n){
		        for(int i = 0;i!=n;i++){
		          ans +=" * ";
		          }
		        ans += ("\n");
		        aux +=2;
		    }
		    while(aux>0){
		      for(int i = 0;i!=n;i++){
		          ans +=" * ";
		          }
		        ans += ("\n");
		        aux -=2;
		    }
		    return ans;
			}
	 
		    
	 public static boolean scramble(String str1, String str2) {    
		    HashMap<Character,Integer> map = new HashMap<>();
		      boolean ans = true;
		      for(char c:str1.toCharArray()){
		    	  if(map.containsKey(c)) map.put(c,map.get(c) + 1);
		    	  else map.put(c, 1);
		      }
		      int index=0;
		      while(ans && index<str2.length()) {
		    	  char aux = str2.charAt(index);
		    	  if(map.containsKey(aux)){
		    		  int value = map.get(aux);
		    		  if(value>1) map.put(aux,value--);
		    	  } else ans = !ans;
		      }
		      return ans;
		    }
	 
	 
	  public static double findUniq(double arr[]) {
	      boolean ans = true;
	      double aux = 0;
	      int i=0;
	      while(i<arr.length && ans){
	        ans = arr[i] == arr[i++];
	        aux = arr[i++];
	        i +=arr[i];
	      }
	      return aux;
	    }

	private static int[] readVector(Scanner scanner) {

		String[] temp = scanner.nextLine().split(" ");
		int tam = temp.length;
		int[] matrix = new int[tam];
		for (int j = 0; j < tam; j++) {
			matrix[j] = Integer.parseInt(temp[j]);
		}

		return matrix;
	}

	public static void main(String[] args) {
		// NO ES NECESARIO EDITAR ESTE METODO.
		Scanner sc = new Scanner(System.in);
		CambioMonedas c = new CambioMonedas(readVector(sc), readVector(sc), readVector(sc));
		sc.close();

		c.vueltaAtras();
		if (c.num_monedas < Integer.MAX_VALUE) {
			System.out.println("La solución es: " + Arrays.toString(c.sol));
			System.out.println("Con: " + c.num_monedas + " monedas");
		} else
			System.out.println("No hay solución");

	}

}
