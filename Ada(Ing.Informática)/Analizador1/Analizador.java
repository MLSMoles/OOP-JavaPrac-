import java.lang.Math;

public class Analizador {

	public static String answer(String s) {
		return s;
	}

	public static String Abort() {
		return "NF!";

	}

	public static void GreatFilter(long[][] vector) {
		int AproverAlg = 0;
		int AproverGeom = 0;
		double CotaDeErrorIzq = 0.5;
		double CotaDeErrorDer = 4;
		for (int i = 0; i < vector[0].length-1; i++) {
			double DividerAlg = Math.pow(1000*Math.pow(2, i),2);
			double DividerGeom = Math.pow(450*(i+1),2);
			System.out.println(DividerAlg + "...."  +vector[0][i] + "/////"+vector[0][i] / DividerAlg);
			System.out.println(DividerGeom + "////" + vector[1][i] + " /////// " + vector[1][i]/DividerGeom);
			
			AproverAlg += (CotaDeErrorIzq * 0.975 <= (vector[0][i] / DividerAlg)
					&& (vector[0][i] /DividerAlg) <= CotaDeErrorDer * 1.025) ? 0 : 1;
			AproverGeom += (CotaDeErrorIzq * 0.975 <= (vector[1][i] / DividerGeom)
					&& (vector[1][i] / DividerGeom) <= CotaDeErrorDer * 1.025) ? 0 : 1;
			//System.out.println(AproverAlg + "/&" +AproverGeom );
		}
		if (AproverAlg < 2 && AproverGeom < 2) {
			System.out.println(answer("N2"));
		} else {
			if (CotaDeErrorIzq * 0.975 >= (vector[0][5] / (1000 * 2 ^ (8 + 1)) ^ 2)) {
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
			double DividerAlg = 1000*Math.pow(2, i+1);
			double DividerGeom = 1000*i;
			AproverAlg += (CotaDeErrorIzq * 0.975 <= (vector[0][i] / DividerAlg)
					&& (vector[0][i] / (1000 * 2 ^ (i + 1))) <= CotaDeErrorDer * 1.025) ? 0 : 1;
			AproverGeom += (CotaDeErrorIzq * 0.975 <= (vector[1][i] / (DividerGeom))
					&& (vector[1][i] / (DividerGeom)) <= CotaDeErrorDer * 1.025) ? 0 : 1;
		}
		if (AproverAlg < 2 && AproverGeom < 2) {
			System.out.println(answer("N"));
		} else {
			if (CotaDeErrorIzq * 0.975 >= (vector[0][5] /(1000*Math.pow(2, 8))))
				ConstantFilter(vector);
			else
				System.out.println(answer("NLOGN"));
		}

	}

	public static void ConstantFilter(long[][] vector) {

		long[][] VectorComparison = new long[2][10];
		long AlgChecker = 0;
		long GeomChecker = 0;
		for (int i = 0; i < vector[0].length-1; i++) {
			VectorComparison[0][i - 2] = vector[0][i - 2] / vector[0][i - 1];
			VectorComparison[1][i - 2] = vector[1][i - 2] / vector[1][i - 1];

			GeomChecker += Comparison(VectorComparison[0][i - 2], VectorComparison[0][i - 1]) == 0 ? 0 : 1;
			AlgChecker += Comparison(VectorComparison[1][i - 2], VectorComparison[1][i - 1]) == 0 ? 0 : 1;

		}
		if (GeomChecker < 2 && AlgChecker < 2)
			System.out.println(answer("1"));
		else {
			System.out.println(answer("LOGN"));

		}
	}

	public static int Comparison(long a, long b) {
		double error = a > b ? a : b;
		if (error * 0.90 <= (a + b) / 2 && (a + b) / 2 <= error * 1.10) {
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
		double CotaDeErrorDer = 4;
		for (int i = 0; i < vector[0].length-1; i++) {
			CotaDeError = 2 ^ i;
			double DividerAlg = Math.pow(2,1000*Math.pow(2, i+1));
			double DividerGeom = Math.pow(2,1000*i);
			//System.out.println(1/(1000 *Math.pow(2, i+1)));
			if(vector[0][i] != 0 && vector[1][i]!= 0) {
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
			if (vector[0][imp]/Math.pow(2,1000*Math.pow(2,imp))> CotaDeError * 1.1)
				System.out.println(answer("NF"));
			else
				System.out.println(answer("N3"));
		}
	}

	public static void main(String args[]) {
		long startTime = System.nanoTime();
		long n = 1000;
		int n1 = 1000;
		long[][] vector = new long[2][5];
		for (int i = 1; i < 6; i++) {
			long GeomMean = 1;
			long AlgMean = 0;
			//System.out.println("------");
			for (int k = 0; k < 8; k++) {
				long CurrentTime = System.nanoTime();
				Temporizador t = new Temporizador();
				Temporizador tt = new Temporizador();
				t.iniciar();
				tt.iniciar();
				Algoritmo.f((long) (Math.pow(2, i) * n));
				Algoritmo.f( i*n1);
				t.parar();
				tt.parar();
				long t1 = t.tiempoPasado();
				long t2 = tt.tiempoPasado();
				System.out.println(t2+ "?¿?¿?¿?¿?¿?");
				if (k > 1) {
					//System.out.println(t2 + "!!!!!" + GeomMean);
					GeomMean = GeomMean * (long) Math.pow(t2, 0.14285714);
					//System.out.println(GeomMean);
					//System.out.println(t1 + "//////" + AlgMean);
					AlgMean += t1;
					//System.out.println("!!!!!!" +AlgMean);
				}
				if (k == 7) {
				//	System.out.println(GeomMean+ "////");
					AlgMean = AlgMean / 7;
					//System.out.println("QQQQQQ" +AlgMean);
					vector[0][i - 1] = AlgMean;
					vector[1][i - 1] = GeomMean;
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

}
