import java.util.Arrays;

public class Timsort {
	
	/*Timsort

	 Problem (informal): Given collection of objects, return sorted collection
	 
	 Algorithm: Variant of mergesort and insertion sort (hybrid) exploiting natural runs (sequences of presorted data) to yield better real-world perfomance
	 
	 Complexity:
	 	* Time - O(nlogn) average/worst case but O(n) best case 
	 	* Space - O(n) to store subarraays, variant of mergesort
	 
	 Notes
	 	* Exploits natural runs in arrays, thus yielding superior empirical performance
	 */
	
	public static void main(String[] args){
		int[] arr = {1, 4, 3, 2, 64, 3, 2, 4, 5, 5, 2, 12, 14, 5, 3, 0, -1};
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
