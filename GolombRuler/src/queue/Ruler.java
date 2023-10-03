package queue;

import java.util.Arrays;

public class Ruler {
	 static boolean check(int[] arr, int toCheckValue)
    {
        // sort given array
        Arrays.sort(arr);
 
        // check if the specified element
        // is present in the array or not
        // using Binary Search method
        int res = Arrays.binarySearch(arr, toCheckValue);
 
        boolean test = res > 0 ? true : false;
 
        // Print the result
        return  test;
    }
	static boolean ruler(int n, int l,int[] ruler,int pos, int[] notposs) {
		boolean ok=true;
		int counter;
		if(ruler.length == 0) {
			counter = 1;
		}else counter = ruler[pos];
		while(ok==true && counter < l && ruler.length <=n) {
			if(ruler.length==0) {
				ruler[0]=counter;
				notposs[0] = counter;
				pos++;
				ok = ruler(n,l,ruler,pos,notposs);
			}else {
				if(check(notposs,counter) == false) {
					ruler[pos+1] = counter;
					int length = notposs.length;
					for(int i=0; i<notposs.length;i++) {
						notposs[length + i] = counter - notposs[i];
					}
					pos++;
					ok=ruler(n,l,ruler,pos,notposs);
				}
			}if(ok=false) {
				counter++;
				ok = true;
			}
			if((counter>l || ruler.length>n) && ok == true) {
				ok = false;
			}
		}
		return ok;
	}
			
	public static void main(String[] args){
		
		
		}
}
