
public class Analizador1 {

	public static String masCercano(double ratio) {
		 if (ratio < 1.03) {
           return "1";
       }else if (1.02 <= ratio && ratio < 4) { 
           return "LOGN";
       }else if (5 <= ratio && ratio < 11) { 
           return "N";
       }else if (11 <= ratio && ratio < 50) { 
           return "NLOGN";
       }else if (50 <= ratio && ratio < 500) { 
           return "N2";
       } else if (500 <= ratio && ratio < 7000) { 
           return "N3";
       }else if (7000 <= ratio && ratio < 25000) {
           return "2N";
       }else{                                 
           return "NF";
       }
	}
	public static void main(String arg[]) {
		Temporizador tE0 = new Temporizador(1);
		Temporizador tE1 = new Temporizador(2);
		
		
		long t0 = 0;
		long t1 = 0;
		int iterator = 1;
		int Counter = 0;
		int n1= 10;
		long Limit = 6000;
		tE0.iniciar();
		while (Counter<Limit) {
			tE1.reiniciar();
			tE1.iniciar();
			Algoritmo.f(n1/10);
			tE1.parar();
			t0 = tE1.tiempoPasado();
			tE1.reiniciar();
			tE1.iniciar();
			Algoritmo.f(iterator*n1);
			tE1.parar();
			t1 += tE1.tiempoPasado();
			tE0.parar();
			Counter  +=tE1.tiempoPasado();
			tE0.iniciar();
			n1 = n1*10;
			iterator ++;
		}
		
		double ratio = (double) t1/t0;
		
		System.out.println(masCercano(ratio));
		
	}
	
	public static long fact(long n) {
		long i = 1;
		long fact = 1;
		while( i!=(n+1)) {
			fact = fact * i;
			i++;
		}
		return fact; 
	}
}
