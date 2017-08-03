import java.util.Arrays;

public class Insertion_Sort {
	
	/*Insertion Sort

	 Problem (informal): Given collection of objects, return sorted collection
	 
	 Algorithm: Maintain sorted and unsorted disjoint subarrays, insert element in correct position in sorted array
	 
	 Complexity:
	 	* Time - O(n^2) worse/average case, O(n) best case if initially sorted
	 	* Space - O(1) since no additional storage needed
	 
	 Functions Defined:
	 	* insertionsort() - main algorithm
	 	
	 */

	public static void main(String[] args) {
		//test arrays
		Integer[] arr = {1, 4, 3, 2, 64, 3, 2, 4, 5, 5, 2, 12, 14, 5, 3, 0, -1};
		String[] strarr = {"hope you find this helpful!", "wef", "rg", "q2rq2r", "avs", "erhijer0g", "ewofij", "gwe", "q", "random"};
		arr = insertionsort(arr);
		strarr = insertionsort(strarr);
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(strarr));

	}
	
	//O(n^2) TIME, O(1) SPACE, STABLE
	public static <E extends Comparable<E>> E[] insertionsort(E[] arr){
		for(int i=0;i<arr.length;i++){
			E key = arr[i];
			boolean flag = false;
			E temp = arr[i];
			for(int j=0;j<=i;j++){
				if(!flag && key.compareTo(arr[j]) <=0){
					flag = true;
					temp = key;
				}
				if(flag){
					E t = arr[j];
					arr[j] = temp;
					temp = t;
				}
			}
		}
		return arr;
	}


}
