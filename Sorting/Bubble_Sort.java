import java.util.Arrays;

public class Bubble_Sort {
	
	/*Selection Sort

	 Problem (informal): Given collection of objects, return sorted collection
	 
	 Algorithm: If two adjacent elements violate sortedness, swap
	 
	 Complexity:
	 	* Time - O(n^2) worse/average case, O(n) best case if initially sorted
	 	* Space - O(1) since no additional storage needed
	 
	 Functions Defined:
	 	* bubblesort() - main algorithm
	 	
	 */

	public static void main(String[] args) {
		//test arrays
		Integer[] arr = {1, 4, 3, 2, 64, 3, 2, 4, 5, 5, 2, 12, 14, 5, 3, 0, -1};
		String[] strarr = {"hope you find this helpful!", "wef", "rg", "q2rq2r", "avs", "erhijer0g", "ewofij", "gwe", "q", "random"};
		arr = bubblesort(arr);
		strarr = bubblesort(strarr);
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(strarr));

	}
	
	//O(n^2) TIME, O(1) SPACE, STABLE
	public static <E extends Comparable<E>> E[] bubblesort(E[] arr){
		for(int i=0;i<arr.length;i++){
			for(int j=0; j<arr.length-1;j++){
				if(arr[j].compareTo(arr[j+1]) >= 1){
					E temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		return arr;
	}


}
