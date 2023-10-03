import java.util.ArrayList;

public class AnalizadorN {
	
	public static void main(String arg[]) {
		Temporizador t = new Temporizador(2);

		t.iniciar();
		Algoritmo.f(16);
		
		t.parar();

		if ((double) t.tiempoPasado() >= (Math.pow(10, 9))) { // Comprobamos primero de todo si se trata de un algoritmo
																// 2N, que son los que tardan mas
			System.out.println("2N");
		} else {
			
			long[] N_test = { 2, 4, 6, 8, 10, 11, 12, 200, 400, 700, 20000, 40000, 70000, 200000, 400000, 700000, };
			t.reiniciar();
			t.iniciar();
			ArrayList<Double> tiempoEjecucion = hallarTiempoEjecucion(N_test, 5, 6 * Math.pow(10, 9));
			t.parar();
			
			/*for (int i = 0; i < tiempoEjecucion.size(); i++) {
				System.out.println("N=" + N_test[i] + ": " + tiempoEjecucion.get(i) * Math.pow(10, -9) + " s");
			}*/
			//System.out.println("\n" + "Tiempo total empleado: " + t.tiempoPasado() * Math.pow(10, -9) + " s");
			System.out.println(masCercano(tiempoEjecucion));
		}
	}

	
	public static ArrayList<Double> hallarTiempoEjecucion(long[] N, int nVeces, double tiempoLimite) {
		/*PruebaFAC fac = new PruebaFAC();
		PruebaLOG log = new PruebaLOG();
		PruebaNLOGN nlogn = new PruebaNLOGN();
		PruebasEXP exp = new PruebasEXP();*/
		
		ArrayList<Double> tiempoEjecucion = new ArrayList<Double>();
		double tiempoEstimado;
		boolean agotado = false;
		Temporizador tempAcumulado = new Temporizador(2);
		Temporizador tempEstimado = new Temporizador(2);
		int i = 0;
		int j;
		tempAcumulado.iniciar();
		while (i < N.length && !agotado) {
			tiempoEstimado = 0;
			j = 0;
			while (j < nVeces && !agotado) {
				tempEstimado.iniciar();
				Algoritmo.f(N[i]);
				//fac.PruebaFACC((int) N[i]); 
				//log.f(3, (int) N[i]); 
				//nlogn.PruebaNLOGN((int) N[i]); //falla
				//exp.f((int) N[i]); 
				
				
				tempEstimado.parar();
				tiempoEstimado += tempEstimado.tiempoPasado();
				tempEstimado.reiniciar();
				if (tempAcumulado.tiempoPasado() > tiempoLimite) {
					agotado = true;
				}
				j++;
			}
			if (!agotado) {
				tiempoEjecucion.add(tiempoEstimado/nVeces);
			}
			i++;
		}
		return tiempoEjecucion;
	}


	/*Esta funcion elimina los valores vacios del array que no le dio tiempo a rellenar*/
	public static long[] quitarCeros(long[] a) {
		int i = -1;

		do {
			i++;
		} while (a[i] != 0 && i < a.length - 1);

		long[] tiempos = new long[i];

		for (int j = 0; j < tiempos.length; j++) {
			tiempos[j] = a[j];
		}

		return tiempos;
	}

	/*Crea un array de 6 (cada pos es una complejidad) y comprueba los dos ultimos valores para calculas la complejidad*/
	public static String masCercano(ArrayList<Double> tiempoEjecucion) {
		String s = "";
		int cont=0;
		
		double[] complejidades = new double[7];

		double rdo = tiempoEjecucion.get(tiempoEjecucion.size()-1) / tiempoEjecucion.get(tiempoEjecucion.size()-2);
		
		// System.out.println(i);
		//System.out.println(rdo);

		
		if (rdo == 1)
			complejidades[1] += 1; // Complejidad 1
		else if (rdo < 1.3)
			complejidades[0] += 1; // Complejidad LOGN
		else if (rdo < 1.9)
			complejidades[2] += 1; // Complejidad NLOGN
		else if (rdo < 2.3 && rdo >= 0.8)
			complejidades[3] += 1; // Complejidad N
		else if (rdo < 4.3 && rdo >= 2.3)
			complejidades[4] += 1; // Complejidad N2
		else if (rdo < 4.7 && rdo >= 4.3)
			complejidades[5] += 1; // Complejidad N3
		
		

		// complejidades[6] siempre estara vacio
		
		//System.out.println("--------");
		//for(int k=0; k<resultados.length; k++) System.out.println(resultados[k]);
		///for(int k=0; k<complejidades.length; k++) System.out.println(complejidades[k]);
		
		double suma = complejidades[0];
		
		while(cont < complejidades.length-1 && suma <= 0) {
			cont++;
			suma = complejidades[cont];
		}
			
			//System.out.println("suma "+ suma);
			//System.out.println("cont "+ cont);
		
		
		switch (cont) {
		case 0: s = "LOGN"; break;
		case 1: s = "1"; break;
		case 2: s = "NLOGN"; break;
		case 3: s = "N"; break;
		case 4: s = "N2"; break;
		case 5: s = "N3"; break;
		default: s = "NF"; 
		}
		
		return s;
	}
	
	
}