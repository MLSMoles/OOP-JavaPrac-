import java.util.ArrayList;

public class Analizador {
	public static long[] N_test = { 2, 4, 6, 8, 10, 11, 12, 200, 5000, 10000, 20000, 40000, 70000, 200000, 400000, 700000, 1000000, 3000000, 7000000, 10000000};
	public static void main(String arg[]) {
		
		Temporizador t = new Temporizador();
		t.iniciar();
		Algoritmo.f(30);
		t.parar();
		if ((double) t.tiempoPasado() >= (6*Math.pow(10, 9))) {
			PowerFilter(N_test,2);
		} else {
			
			t.reiniciar();
			t.iniciar();
			ArrayList<Double> tiempoEjecucion = Timer(N_test, 5, 7 * Math.pow(10, 9));
			t.parar();
			
			System.out.println(masCercano(tiempoEjecucion));
		}
	}

	
	private static void PowerFilter(long[] n_test,int nVeces) {
		double tiempoEstimado;
		double counter=0;
		int Filter = 0;
		Temporizador tempEstimado = new Temporizador(2);
		int i =2;
		while(i<7) {
			tempEstimado.iniciar();
			Algoritmo.f(n_test[i]);
			tempEstimado.parar();
			if(i!=2) {
				tiempoEstimado = tempEstimado.tiempoPasado();
				 double time = tiempoEstimado/(Math.pow(2, i));
				Filter = (0.6<=time && time < 2) ? 0:1;
				counter = (0.6>time) ? 0:1;
						}
			tempEstimado.reiniciar();
			i++;
		}
		if(Filter<=2 && Filter>0.6)System.out.println("2N");
		else {
			if(counter<0.6) System.out.println("N3");
			else System.out.println("NF");
		}
	}
	



	public static ArrayList<Double> Timer(long[] N, int nVeces, double tiempoLimite) {
		
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

	public static String masCercano(ArrayList<Double> tiempoEjecucion) {
		//long[] N_test = { 2, 4, 6, 8, 10, 11, 12, 200, 400, 700, 20000, 40000, 70000, 200000, 400000, 700000, 1000000, 3000000, 7000000, 10000000};
		String s = "";
		int size = tiempoEjecucion.size()-1;
		s = LogN(tiempoEjecucion,size);
		if(s=="") {
			s = N(tiempoEjecucion,size);;
		}if(s==""){
			s = NLogN(tiempoEjecucion,size);
		}if(s==""){
			s = N2(tiempoEjecucion,size);
		}if(s==""){
			s = N3(tiempoEjecucion,size);
		}
//		int size = tiempoEjecucion.size()-1;
//		s = LogN(tiempoEjecucion.get(tiempoEjecucion.size() - 1),size);
//		if(s=="") {
//			s = N(tiempoEjecucion.get(tiempoEjecucion.size() - 1),size);;
//		}if(s==""){
//			s = NLogN(tiempoEjecucion.get(tiempoEjecucion.size() - 1),size);
//		}if(s==""){
//			s = N2(tiempoEjecucion.get(tiempoEjecucion.size() - 1),size);
//		}if(s==""){
//			s = N3(tiempoEjecucion.get(tiempoEjecucion.size() - 1),size);
//		}
//			
		return s;
	}
//
//
//private static String N3(double tiempoEjecucion,int size) {
//	String s = "";
//	double coef = tiempoEjecucion/(Math.pow(N_test[size],3));
//	s = (0.25 < coef &&  coef<4) ? "N3":"";
//	if(s=="") s = (0.25> coef) ? "N2":"2N";
//	return s;
//	}
//
//
//private static String N2(double tiempoEjecucion,int size) {
//	String s = "";
//	double coef = tiempoEjecucion/(N_test[size]*N_test[size]);
//	s = (0.25 < coef &&  coef<8) ? "N2":"";
//	if(s=="") s = (0.25> coef) ? "NLOGN":"";
//	return s;
//	}
//
//
//private static String NLogN(double tiempoEjecucion,int size) {
//	String s = "";
//	double coef = tiempoEjecucion/(N_test[size]*Math.log(N_test[size]));
//	s = (0.5 < coef &&  coef<8) ? "NLOGN":"";
//	if(s=="") s = (0.5 > coef) ? "N":"";
//	return s;
//	}
//
//
//private static String N(double tiempoEjecucion,int size) {
//	String s = "";
//	double coef = tiempoEjecucion/(N_test[size]);
//	System.out.println(coef);
//	s = (0.25 < coef && coef<=100) ? "N":"";
//	if(s=="") s = (0.25 > coef) ? "LOGN":"";
//	return s;
//	}
//
//
//private static String LogN(double tiempoEjecucion, int size) {
//	String s = "";
//	double coef = tiempoEjecucion/Math.log(N_test[size]);
//	s = (0.25 < coef &&  coef<8) ? "LOGN":"";
//	if(s=="") s = (0.25>coef) ?  "1":"";
//	return s;
//	}


private static String N3(ArrayList<Double> tiempoEjecucion,int size) {
	String s = "";
	double coef1 = tiempoEjecucion.get(size-1)/(Math.pow(N_test[size-1],3));
	double coef2 = tiempoEjecucion.get(size)/(Math.pow(N_test[size],3));
	double ratio = Math.sqrt(coef1 * coef2);
	s = (coef1*0.9<=coef2 && coef2<=coef1*1.10) ? "N3":"";
	if(s=="") s = (0.25> ratio) ? "N2":"2N";
	return s;
	}


private static String N2(ArrayList<Double> tiempoEjecucion,int size) {
	String s = "";
	double coef1 = tiempoEjecucion.get(size-1)/(Math.pow(N_test[size-1],2));
	double coef2 = tiempoEjecucion.get(size)/(Math.pow(N_test[size],2));
	double ratio = Math.sqrt(coef1 * coef2);
	s = (coef1*0.65<=coef2 && coef2<=coef1*1.45) ? "N2":"";
	if(s=="") s = (0.25> ratio) ? "NLOGN":"";
	return s;
	}


private static String NLogN(ArrayList<Double> tiempoEjecucion,int size) {
	String s = "";
	double coef1 = tiempoEjecucion.get(size-1)/N_test[size-1]*Math.log(N_test[size-1]);
	double coef2 = tiempoEjecucion.get(size)/N_test[size]*Math.log(N_test[size]);
	double ratio = Math.sqrt(coef1 * coef2);
	s = (coef2*0.7<=coef1 && coef1<=coef2*1.30) ? "NLOGN":"";
	if(s=="") s = (0.5 > ratio) ? "N":"";
	return s;
	}


private static String N(ArrayList<Double> tiempoEjecucion,int size) {
	String s = "";
	double coef1 = tiempoEjecucion.get(size-1)/N_test[size-1];
	double coef2 = tiempoEjecucion.get(size)/N_test[size];
	double ratio = Math.sqrt(coef1 * coef2);
	//System.out.println(coef1 + "///" + coef2);
	
	s = (coef1*0.55<=coef2 && coef2<=coef1*1.45) ? "N":"";
	if(s=="") s = (0.25 > ratio) ? "LOGN":"";
	return s;
	}


private static String LogN(ArrayList<Double> tiempoEjecucion, int size) {
	String s = "";
	double coef1 = tiempoEjecucion.get(size-1)/Math.log(N_test[size-1]);
	double coef2 = tiempoEjecucion.get(size)/Math.log(N_test[size]);
	double ratio = Math.sqrt(coef1 * coef2);
	s = (coef1*0.9<=coef2 && coef2<=coef1*1.10) ? "LOGN":"";
	if(s=="") s = (0.25>ratio) ?  "1":"";
	return s;
	}


}
	
