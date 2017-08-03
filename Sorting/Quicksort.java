import java.util.Arrays;

public class Quicksort {
	
	/*Quicksort

	 Problem (informal): Given collection of objects, return sorted collection
	 
	 Algorithm: Choose random parition, move all elements less than partition to its left and vice versa, quicksort subarrays
	 
	 Complexity:
	 	* Time - O(n^2) worst case, O(nlogn) best/average case due to randomness
	 	* Space - O(logn) since quicksort() called logn times and each time partition is stored
	 
	 Functions Defined:
	 	* quicksort() - main algorithm
	
	 Notes
	 	* Although Mergesort and Heapsort are O(nlogn) worst case and quicksort seems worse, it is better in practice
	 	* Quicksort uses hardware caches efficiently yielding smaller constants (concealed by Big-O notation)
	 	
	 */

	public static void main(String[] args) {
		//test array
		Integer[] arr = {1, 4, 3, 2, 64, 3, 2, 4, 5, 5, 2, 12, 14, 5, 3, 0, -1};
		String[] strarr = {"hope you find this helpful!", "wef", "rg", "q2rq2r", "avs", "erhijer0g", "ewofij", "gwe", "q", "random"};
		arr = quicksort(arr);
		strarr = quicksort(strarr);
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(strarr));
	}
	
	//O(nlogn) TIME, O(1) SPACE, NOT STABLE
	public static <E extends Comparable<E>> E[] quicksort(E[] arr, int...range){
		int p = range.length > 0 ? range[0] : 0;
	    int r = range.length > 1 ? range[1] : arr.length-1;
		
		if(p >= r) return arr;
		
		int randint = p + (int) ((r-p+1)*Math.random()); // random integer in range [p,r]
		E q = arr[randint];
		arr[randint] = arr[r];
		arr[r] = q;
		
		int i=p-1;
		for(int j=p;j<r;j++){
			if(arr[j].compareTo(q) <=0){
				E temp = arr[j];
				arr[j] = arr[++i];
				arr[i] = temp;
			}
		}
		
		arr[r] = arr[++i];
		arr[i] = q;
		quicksort(arr, 0, i-1);
		quicksort(arr, i+1, r);
		
		return arr;
	}


}
