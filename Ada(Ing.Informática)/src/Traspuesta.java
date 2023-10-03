import java.util.Random;

public class Traspuesta {
	static Random rand = new Random();

	private static void to_String1(int[][] mm) {
		for (int i = 0; i < mm.length; i++) {
			for (int j = 0; j < mm[0].length; j++) {
				System.out.print(mm[i][j] + "|");
			}
			System.out.println();
		}
	}

	private static void fill(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				if (i == j)
					m[i][j] = 0;
				else
					m[i][j] = rand.nextInt(10);
			}
		}
	}

	private static void transpuesta(int[][] m, int hf, int hl, int vf, int vl) {
		if (hf < hl) {
			int LenHor = (hl + hf) / 2;
			int LenVer = (vl + vf) / 2;
			transpuesta(m, hf, LenHor, vf, LenVer);
			transpuesta(m, LenHor + 1, hl, LenVer + 1, vl);
			transpuesta(m, LenHor + 1, hl, vf, LenVer);
			transpuesta(m, hf, LenHor, LenVer + 1, vl);
			change(m, LenHor + 1, vf, hf, LenVer + 1, hl - LenHor);

		}
	}

	static void change(int[][] m, int hf, int vf, int hl, int vl, int len) {
		for (int i = 0; i <= len - 1; i++)
			for (int j = 0; j <= len - 1; j++) {
				int aux = m[hf + i][vf + j];
				m[hf + i][vf + j] = m[hl + i][vl + j];
				m[hl + i][vl + j] = aux;
			}
	}

	public static void main(String[] args) {
		int[][] mm = new int[4][4];
		fill(mm);
		to_String1(mm);
		System.out.println("-----------------------");
		transpuesta(mm, 0, mm.length - 1, 0, mm.length - 1);
		to_String1(mm);
	}
}
