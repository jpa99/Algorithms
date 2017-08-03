import java.util.Arrays;

public class Heapsort {
	
	/*Quicksort

	 Problem (informal): Given collection of objects, return sorted collection
	 
	 Algorithm: Create maxheap, pop max, maxheapify and continue
	 
	 Complexity:
	 	* Time - O(nlogn) and asymptotically tight, since maxheapify is O(logn) and performed n times
	 	* Space - O(1) since entirely in-place
	 
	 Functions Defined:
	 	* heapsort() - main algorithm
	 	* maxheapify() - returns maxheap given non heap 
	
	 Notes
	 	* Although Mergesort and Heapsort are O(nlogn) worst case and quicksort seems worse, it is better in practice
	 	
	 */

	public static void main(String[] args) {
		//test array
		Integer[] arr = {1, 4, 3, 2, 64, 3, 2, 4, 5, 5, 2, 12, 14, 5, 3, 0, -1};
		String[] strarr = {"hope you find this helpful!", "wef", "rg", "q2rq2r", "avs", "erhijer0g", "ewofij", "gwe", "q", "random"};
		arr = heapsort(arr);
		strarr = heapsort(strarr);
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(strarr));
	}
	
	//O(nlogn) TIME, O(1) SPACE, NOT STABLE
	public static <E extends Comparable<E>> E[] heapsort(E[] arr){
		int heaplength = arr.length;
		for(int i = arr.length/2; i>0;i--){
			arr = maxheapify(arr, i, heaplength);
		}
		
		for(int i=arr.length-1;i>=0;i--){
			E max = arr[0];
			arr[0] = arr[i];
			arr[i] = max;
			heaplength--;
			arr = maxheapify(arr, 1, heaplength);
		}
		
		return arr;
	}
	
	//Creates maxheap from array
	public static <E extends Comparable<E>> E[] maxheapify(E[] arr, Integer node, Integer heaplength){
		Integer left = node*2;
		Integer right = node*2+1;
		Integer largest = node;
		
		if(left.compareTo(heaplength) <=0 && arr[left-1].compareTo(arr[node-1]) >= 0){
			largest = left;
		}
		if(right.compareTo(heaplength) <= 0 && arr[right-1].compareTo(arr[largest-1]) >= 0){
			largest = right;
		}
		
		if(largest != node){
			E temp = arr[node-1];
			arr[node-1] = arr[largest-1];
			arr[largest-1] = temp;
			maxheapify(arr, largest, heaplength);
		}
		return arr;
	}


}
