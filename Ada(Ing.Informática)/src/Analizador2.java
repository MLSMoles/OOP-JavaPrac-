import java.lang.Math;

public class Analizador2 {

	public static String answer(String s) {
		return s;
	}
//	Algoritmo.f((long) (i * 10000*n));
//	Algoritmo.f((long)  (i*100*n));

	public static void GreatFilter(long[][] vector) {
		int AproverAlg = 0;
		int AproverGeom = 0;
		double CotaDeErrorIzq = 0.3;
		double CotaDeErrorDer = 5;
		for (int i = 0; i < vector[0].length; i++) {
			double DividerAlg = Math.pow((i+1) * 1000,2);
			double DividerGeom = Math.pow((i)*1000,2);
			System.out.println("^^^^^^"+ 10 * Math.pow(10*(i),2));
			System.out.println(DividerAlg + "...."  +vector[0][i] + "/////"+vector[0][i] / DividerAlg);
			System.out.println(DividerGeom + "////" + vector[1][i] + " /////// " + vector[1][i]/DividerGeom);
			
			AproverAlg += (CotaDeErrorIzq * 0.975 <= (vector[0][i] / DividerAlg)
					&& (vector[0][i] /DividerAlg) <= CotaDeErrorDer * 1.025) ? 0 : 1;
			AproverGeom += (CotaDeErrorIzq * 0.975 <= (vector[1][i] / DividerGeom)
					&& (vector[1][i] / DividerGeom) <= CotaDeErrorDer * 1.025) ? 0 : 1;
			System.out.println(AproverAlg + "/&" +AproverGeom );
		}
		if (AproverAlg < 2 || AproverGeom < 2) {
			System.out.println(answer("N2"));
		} else {//System.out.println((vector[0][8] +"///"+  Math.pow(10 *Math.pow(2,8),2)));
			if (CotaDeErrorIzq * 0.975 >= (vector[0][3] / Math.pow(3*10000,2))) {
				Nfilter(vector);
			} else
				PowerFilter(vector);
		}

	}

	public static void Nfilter(long[][] vector) {
		int AproverAlg = 0;
		int AproverGeom = 0;
		double CotaDeErrorIzq = 0.5;
		double CotaDeErrorDer = 4;
		for (int i = 0; i < vector[0].length-1; i++) {
			double DividerAlg = (i+1) * 100000;
			double DividerGeom = i*1000;
			//System.out.println(DividerAlg + "...."  +vector[0][i] + "/////"+vector[0][i] / DividerAlg);
			//System.out.println(DividerGeom + "////" + vector[1][i] + " /////// " + vector[1][i]/DividerGeom);
			AproverAlg += (CotaDeErrorIzq * 0.975 <= (vector[0][i] / DividerAlg)
					&& (vector[0][i] / DividerAlg) <= CotaDeErrorDer * 1.025) ? 0 : 1;
			AproverGeom += (CotaDeErrorIzq * 0.975 <= (vector[1][i] / (DividerGeom))
					&& (vector[1][i] / (DividerGeom)) <= CotaDeErrorDer * 1.025) ? 0 : 1;
		}
		if (AproverAlg < 5 && AproverGeom < 5) {
			System.out.println(answer("N"));
		} else {
			if (CotaDeErrorIzq * 0.975 >= (vector[0][4] /(4) * 10000))
				ConstantFilter(vector);
			else
				System.out.println(answer("NLOGN"));
		}

	}

	public static void ConstantFilter(long[][] vector) {

		long[][] VectorComparison = new long[2][5];
		long AlgChecker = 0;
		long GeomChecker = 0;
		for (int i = 2; i < vector[0].length-1; i++) {
			VectorComparison[0][i - 2] = vector[0][i - 2] / vector[0][i - 1];
			VectorComparison[1][i - 2] = vector[1][i - 2] / vector[1][i - 1];

			GeomChecker += Comparison(VectorComparison[0][i - 2], VectorComparison[0][i - 1]) == 0 ? 0 : 1;
			AlgChecker += Comparison(VectorComparison[1][i - 2], VectorComparison[1][i - 1]) == 0 ? 0 : 1;

		}
		if (GeomChecker < 4 && AlgChecker < 4)
			System.out.println(answer("1"));
		else {
			System.out.println(answer("LOGN"));

		}
	}

	public static int Comparison(long a, long b) {
		double error = a > b ? a : b;
		if (error * 0.85 <= (a + b)/2 && (a + b) / 2 <= error * 1.15) {
			return 0;
		} else
			return 1;
	}

	public static void PowerFilter(long[][] vector) {
		int AproverAlg = 0;
		int AproverGeom = 0;
		int CotaDeError = 0;
		int imp=0;
		double CotaDeErrorIzq = 0.5;
		double CotaDeErrorDer = 2.5;
		for (int i = 0; i < vector[0].length-1; i++) {
			CotaDeError = 2 ^ i;
			if(vector[0][i] != 0 && vector[1][i]!= 0) {
				double DividerAlg = Math.pow(2,10*Math.pow(4, i));
			double DividerGeom = Math.pow(2,10*10^(i+1));
				imp++;
			AproverAlg += ((vector[0][i]/ DividerAlg) < CotaDeErrorDer * 1.1
					&& (vector[0][i]/DividerAlg) > CotaDeErrorIzq * 0.9) ? 0 : 1;
			AproverGeom += ((vector[1][i]/DividerGeom) < CotaDeErrorDer * 1.1
					&& (vector[1][i]/DividerGeom) > CotaDeErrorIzq * 0.9) ? 0 : 1;
		}
			}
		if (AproverAlg < 2 && AproverGeom < 2) {
			System.out.println(answer("2N"));
		} else {
			//System.out.println(((double) vector[0][imp-1])+ "//" +((double) Math.pow(2,10*Math.pow(2,imp-1))));
			if (vector[0][imp-1]/Math.pow(2,10*Math.pow(4,imp-1))> CotaDeError * 1.1)
				System.out.println(answer("NF"));
			else
				System.out.println(answer("N3"));
		}
	}

	public static void main(String args[]) {
		long t1 = 0;
		long CurrentTime = System.nanoTime();
		long t2 = 0;
		long startTime = System.nanoTime();
		long n = 10;
		long[][] vector = new long[2][5];
		for (int i = 1; i < 6; i++) {
			long GeomMean = 1;
			long AlgMean = 0;
			System.out.println("------");
			for (int k = 0; k <5; k++) {
				if(i==1) {
					
					Temporizador t = new Temporizador(2);
					Temporizador tt = new Temporizador(2);
					
					
//					System.out.println("11  "+ Math.pow(5,i-1) * n);
//					System.out.println("222 " + (Math.pow(10,i-1)*n));
					t.iniciar();
					Algoritmo.f((long) (Math.pow(4,i-1) * n));
					t.parar();
					tt.iniciar();
					Algoritmo.f((long) Math.pow(10,(i-1))*n);
					tt.parar();
					 t1 = t.tiempoPasado();
					 t2 = tt.tiempoPasado();
				}else {
				Temporizador t = new Temporizador(2);
				Temporizador tt = new Temporizador(2);
				t.reiniciar();
				t.iniciar();
				
//				System.out.println("11  "+ Math.pow(5,i-1) * n);
//				System.out.println("222 " + (Math.pow(10,i-1)*n));
				Algoritmo.f((long)  (i*1000*n));
				t.parar();
				tt.reiniciar();
				tt.iniciar();
				Algoritmo.f((long)  (i*100*n));
				
				tt.parar();
				 t1 = t.tiempoPasado();
				 t2 = tt.tiempoPasado();
				}
				//System.out.println(t2+ "?¿?¿?¿?¿?¿?");
				if (k > 1) {
					//System.out.println(t2 + "!!!!!" + GeomMean);
					GeomMean = GeomMean * (long) Math.pow(t2, 0.2);
					//System.out.println(GeomMean);
					//System.out.println(t1 + "//////" + AlgMean);
					AlgMean += t1;
					//System.out.println("!!!!!!" +AlgMean);
				}
				if (k == 4) {
				    System.out.println(GeomMean+ "////");
					AlgMean = AlgMean /5;
					System.out.println("QQQQQQ" +AlgMean);
					vector[0][i - 1] = AlgMean;
					vector[1][i - 1] = GeomMean;
					AlgMean = 0;
					GeomMean= 1;
					//System.out.println((double) vector[0][i-1] /  (double) fact(n));
					if(i<2 && 0.9<=((double) vector[0][i-1]/((double) fact(n)))) {
						PowerFilter(vector); 
						return ; 
					}
				} 
				//System.out.println((CurrentTime-startTime)/1000000000);
				if(((CurrentTime-startTime)/1000000000)>7 && (i<5)) {
					PowerFilter(vector);
					break;
				}
			}
		}

		GreatFilter(vector);
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
