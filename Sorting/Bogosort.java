import java.util.Arrays;
import java.util.Random;

public class Bogosort {
	
	/*Bogosort

	 Problem (informal): Given input array, return permuted array such that elements are uniformly distributed (pseudorandomly)
	 
	 Algorithm: For each index i, swap element at i with element at random index 0 <= j <= i
	 
	 Complexity:
	 	* Time - O(n) best case if presorted, O((n+1)!) if deterministic (no repeated permutations) but unbounded if randomized 
	 	* Space - O(1) since no additional space used
	 
	 Functions Defined:
	 	* sorted() - checks if generic array is sorted
	 	* bogosort() - lol
	 	* shuffle() - Fisher-Yates shuffling algorithm to randomly permute array
	 	* swap() - swaps two elements
	 	
	 Notes:
	 	* PROBABLY WON'T TERMINATE FOR LARGE ARRAYS! xD
	 	
	 */

	public static void main(String[] args) {
		Integer[] arr = {1, 4, 3, 5, 4, 3, 2, 8};
		arr = bogosort(arr);
		System.out.println(Arrays.toString(arr));

	}
	
	
	public static <E extends Comparable<E>> boolean sorted(E[] arr){
		for(int i=0;i<arr.length-1;i++){
			if(arr[i].compareTo(arr[i+1]) == 1)
				return false;
		}
		return true;
	}
	
	public static <E extends Comparable<E>> E[] bogosort(E[] arr){
		while(!sorted(arr)){
			arr = shuffle(arr);
		}
		return arr;
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
