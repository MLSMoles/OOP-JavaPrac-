import java.util.Arrays;
import java.util.Random;

public class Mediana {
	protected static Random rand = new Random();

	static final int CHECKER = 2;
	static final int ARRAYS = 0; // Can be either 0 or 1;

	@SuppressWarnings("all")
	public static void main(String[] Args) {
		if (CHECKER == 0) {
			int[] v1 = { 1, 2, 3, 4, 5, 6, 10 };
			int[] w1 = { 1, 2, 3, 5, 5, 6, 10 };
			int[] concate = concat(v1, w1);
			System.out.println("Mediana usando el método de Divide y Vencerás: " + Median(v1, w1, 0, v1.length, 0, w1.length));
			System.out.println("Los 2 arrays concatenados: " + Arrays.toString(concate));
			if(ARRAYS == 0) {
				System.out.println("Primer Array: " + Arrays.toString(v1));
				System.out.println("Primer Array: " + Arrays.toString(w1));
			}
			System.out.println("Mediana de 2 arrays concatenados: " + concate[concate.length / 2]);
		} else if (CHECKER == 1) {
			int[] v2 = { 1, 5, 8, 10, 14 };
			int[] w2 = { 3, 6, 8, 12, 15 };
			int[] concate = concat(v2, w2);
			System.out.println("Mediana usando el método de Divide y Vencerás: " + Median(v2, w2, 0, v2.length, 0, w2.length));
			System.out.println("Los 2 arrays concatenados: " + Arrays.toString(concate));
			if(ARRAYS == 0) {
				System.out.println("Primer Array: " + Arrays.toString(v2));
				System.out.println("Primer Array: " + Arrays.toString(w2));
			}
			System.out.println("Mediana de 2 arrays concatenados: " + concate[concate.length / 2]);

		} else if (CHECKER == 2) {
			int[] RandomArray1 = ArrayGenerator(10, 20);
			int[] RandomArray2 = ArrayGenerator(10, 20);
			int[] concate = concat(RandomArray1, RandomArray2);
			System.out.println("Mediana usando el método de Divide y Vencerás: " + Median(RandomArray1, RandomArray2, 0, RandomArray1.length, 0, RandomArray2.length));
			if(ARRAYS == 0) {
				System.out.println("Primer Array: " + Arrays.toString(RandomArray1));
				System.out.println("Primer Array: " + Arrays.toString(RandomArray2));
			}
			System.out.println("Los 2 arrays concatenados: " + Arrays.toString(concate));
			System.out.println("Mediana de 2 arrays concatenados: " + concate[concate.length / 2]);
		}
	}

	private static int Median(int[] X, int[] Y, int primX, int ultX, int primY, int ultY) {
		int posX, posY;
		int iterator = 0;
		if ((primX >= ultX) && (primY >= ultY))
			return Min2(X[ultX], Y[ultY]);
		iterator = ultX - primX + 1;
		if (iterator == 2) {
			if (X[ultX] < Y[primY])
				return X[ultX];
			else if (Y[ultY] < X[primX])
				return Y[ultY]; 
			else
				return Max2(X[primX], Y[primY]);
		}
		iterator = (iterator - 1) / 2;
		posX = primX + iterator;
		posY = primY + iterator;
		if (X[posX] == Y[posY])
			return X[posX];
		if (X[posX] < Y[posY])
			return Median(X, Y, ultX - iterator, ultX, primY, primY + iterator);
		else
			return Median(X, Y, primX, primX + iterator, ultY - iterator, ultY);
	}

	public static int Min2(int X, int Y) {
		int i = (X < Y) ? X : Y;
		return i;
	}

	public static int Max2(int x, int y) {
		int i = (x < y) ? y : x;
		return i;
	}

	public static int[] concat(int[] v, int[] w) {
		int len = 2 * v.length;
		int[] ans = new int[len];
		int vi = 0;
		int wi = 0;
		for (int i = 0; i < len;) {
			if (wi < w.length && vi < v.length) {
				if (v[vi] < w[wi]) {
					ans[i] = v[vi];
					i++;
					vi++;
				} else if (v[vi] == w[wi]) {
					ans[i] = v[vi];
					ans[i + 1] = w[wi];
					vi++;
					wi++;
					i = i + 2;
				} else {
					ans[i] = w[wi];
					wi++;
					i++;
				}
			} else if (wi < w.length) {
				ans[i] = w[wi];
				wi++;
				i++;
			} else {
				ans[i] = v[vi];
				vi++;
				i++;
			}
		}
		return ans;
	}

	private static int[] ArrayGenerator(int num, int Max) {
		int[] vector = new int[num];
		int k = Max / num;
		vector[0] = rand.nextInt(3);
		for (int i = 1; i < num; i++) {
//			vector[i] = (vector[i] + k < Max) ? NumberGenerator(vector[i - 1], i + k)
//					: NumberGenerator(vector[i - 1], Max);
			vector[i] =  NumberGenerator(vector[i - 1],Max - ( num-i));
		}
		return vector;
	}

	private static int NumberGenerator(int min, int max) {
		return min + rand.nextInt(max - min + 1);
	}

}
