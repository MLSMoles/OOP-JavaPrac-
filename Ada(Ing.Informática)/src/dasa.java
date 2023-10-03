import java.util.Arrays;

public class dasa {
	public static void main(String[] args) {
		int v[] = {1,2,3,4,5};
		
		System.out.println(Arrays.toString(rotater(v,2)));

	}

	public static int[] rotater(int[] v, int k) {

		int len = v.length;
		for (int i = 0; i < k; i++) {
			int number = v[0];
			for (int j = 1; j < v.length; j++) {
				v[j - 1] = v[j];
			}
			v[v.length-1] = number;
		}
		return v;
	}
}