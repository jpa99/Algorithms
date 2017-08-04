import java.util.Arrays;
import java.util.Random;

public class Fisher_Yates {
	
	/*Fisher-Yates Algorithm

	 Problem (informal): Given input array, return permuted array such that elements are uniformly distributed (pseudorandomly)
	 
	 Algorithm: For each index i, swap element at i with element at random index 0 <= j <= i
	 
	 Complexity:
	 	* Time - O(n) since only one iteration
	 	* Space - O(n) to choose random integer at each position
	 	
	 Notes:
	 	* shuffle() - randomly permutes input array
	 	* swap() - swaps elements at indices i and j
	 	
	 Notes:
	 	* Simple but effective shuffling algorithm
	 	
	 */

	public static void main(String[] args) {
		Integer[] arr = {1, 4, 3, 2, 64, 3, 2, 4, 5, 5, 2, 12, 14, 5, 3, 0, -1};
		arr = shuffle(arr);
		System.out.println(Arrays.toString(arr));

	}
	
	public static <E extends Comparable<E>> E[] shuffle(E[] arr){
		Random rng = new Random();
		for(int i = arr.length - 1 ; i > 0 ; i--){
			int j = rng.nextInt(i+1); //random element in range [0, i]
			swap(arr, i, j);
		}
		return arr;
	}
	
	public static <E> void swap(E[] arr, int i, int j){
		E temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
