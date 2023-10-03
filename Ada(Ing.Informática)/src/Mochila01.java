import java.util.Random;

public class Mochila01 {

	protected static Random r = new Random();
	private static int LEN = 10;
	private static int METHOD = 1; // can be 0,1,2.
	private static int[] value = RandomArray();
	private static int[] weight = RandomArray();
	

	//------------ 0 and 1 cases
	private static void quicksort(int[] v,int[] aux, int f, int l) {
		if (f < l) {
			int index = partition(v,aux,f, l);
			quicksort(v,aux, f, index - 1);
			quicksort(v,aux, index + 1, l);
		}
	}
	
	
	private static int partition(int arr[],int[] aux, int begin, int end) {
	    int pivot = arr[end];
	    int i = (begin-1);
	    int swapTemp = 0;
	    for (int j = begin; j < end; j++) {
	        if (arr[j] <= pivot) {
	            i++;
	            swapTemp = arr[i];
	            arr[i] = arr[j];
	            arr[j] = swapTemp;
	            swapTemp = aux[i];
	            aux[i] = aux[j];
	            aux[j] = swapTemp;
	        }
	    }
	    swapTemp = arr[i+1];
	    arr[i+1] = arr[end];
	    arr[end] = swapTemp;
	    swapTemp = aux[i+1];
	    aux[i+1] = aux[end];
	    aux[end] = swapTemp;

	    return i+1;
	}



	//----------Case 2
	
	private static float[] ratio(int[] w,int[] v) {
		float[] ratio = new float[LEN];
		for(int i = 0;i<LEN;i++) {
			ratio[i] = w[i]/v[i];
		}
		return ratio;
	}
	
	private static int partition(float arr[],int[] aux, int[] aux2, int begin, int end) {
	    float pivot = arr[end];
	    int i = (begin-1);
	    float swapTemp = 0;
	    for (int j = begin; j < end; j++) {
	        if (arr[j] <= pivot) {
	            i++;
	            swapTemp = arr[i];
	            arr[i] = arr[j];
	            arr[j] = swapTemp;
	            int swapTemp1 = aux[i];
	            aux[i] = aux[j];
	            aux[j] = swapTemp1;
	            swapTemp = aux2[i];
	            aux2[i] = aux2[j];
	            aux2[j] = swapTemp1;
	        }
	    }

	    swapTemp = arr[i+1];
	    arr[i+1] = arr[end];
	    arr[end] = swapTemp;
	    int swapTemp1 = aux[i+1];
	    aux[i+1] = aux[end];
	    aux[end] = swapTemp1;
	    swapTemp1 = aux2[i+1];
	    aux2[i+1] = aux2[end];
	    aux2[end] = swapTemp1;

	    return i+1;
	}
	
	private static void quicksort(float[] v,int[] aux,int[] aux2, int f, int l) {
		if (f < l) {
			int index = partition(v,aux,aux2,f, l);
			quicksort(v,aux,aux2, f, index - 1);
			quicksort(v,aux,aux2, index + 1, l);
		}
	}


	
	//------------------------ ALL cases
	
	
	private static int[]  RandomArray() {
		int[] ans = new int[LEN];
		for (int i = 0; i < LEN; i++) {	
			boolean aux = false;
			while(!aux) {
				int random = r.nextInt(20);
				aux = (random>0) ? true:false;
				ans[i] = random;
			}
		}
		return ans;
	}
	
	private static int[] solver(int[] weight,int[] value, int P) {
		int i=0;
		int[] sol = new int[LEN];
		while(P-weight[i]>=0 && i+1<LEN) {
			sol[i] = value[i];
			P  -= weight[i];
			i++;
		}
		return sol;
	}
	
	private static int sum(int[] v) {
		int sum=0;
		for(int i=0;i<LEN;i++) {
			sum +=v[i];
		}
		return sum;
	}
	
	private static void to_String(int[] v) {
		int i = 0;
		while(i<LEN && v[i] !=0) {
			if(i+1<LEN)System.out.print((v[i + 1] != 0) ? v[i] + "," : v[i]);
			else System.out.println(v[i]);
			i++;
		}
		if(i<LEN) System.out.println();
	}
	
	private static void reverse(int[] v) {
		for(int i=0;i<LEN/2;i++) {
			int aux = v[i];
			v[i] = v[LEN-1-i];
			v[LEN-1-i]= aux;
		}
	}
	
	public static void main(String[] args) {
		//Create 2 arrays of value and weight, element i from first array is associated with i element in second array.
		int MaxWeight = 50;
		int[] solution;
		//METHOD = 0-->Implementation using Greedy algorithm that chooses with least weight, first we sort weight array, than in solver method choose as many objects as possible.
		//METHOD = 1-->Implementation using Greedy algorithm that chooses with most value, first we sort weight array, than in solver method choose as many objects as possible.
		//METHOD = 2-->Implementation using Greedy algorithm that chooses with best weight/value, first we sort weight array, than in solver method choose as many objects as possible.
		switch(METHOD) {
		case(0):quicksort(weight,value, 0, weight.length - 1);
				break;
		case(1):quicksort(value,weight, 0, weight.length - 1);
				reverse(value);
				reverse(weight);
				break;
		case(2): float[] ratio = ratio(weight,value); // Gets an array of ratios.
				quicksort(ratio,value,weight, 0, weight.length - 1);
				break;
		}
		System.out.println("Array of weights:");
		to_String(weight);
		System.out.println("Array of values:");
		to_String(value);
		System.out.println("Array of a solution:");
		solution = solver(weight,value,MaxWeight);
		to_String(solution); // Prints solution array;
		System.out.println("Sum of values of all items in a knapsack:");
		System.out.println(sum(solution)); //Prints sum of all values.
		
	}

}
