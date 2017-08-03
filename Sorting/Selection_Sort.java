import java.util.Arrays;

public class Selection_Sort {
	
	/*Selection Sort

	 Problem (informal): Given collection of objects, return sorted collection
	 
	 Algorithm: Choose minimum of unsorted array, append to sorted array and label as sorted
	 
	 Complexity:
	 	* Time - O(n^2) best/worse/average case, asymptotically tight
	 	* Space - O(1) since no additional storage needed
	 
	 Functions Defined:
	 	* selectionsort() - main algorithm
	 	
	 */

	public static void main(String[] args) {
		//test arrays
		Integer[] arr = {1, 4, 3, 2, 64, 3, 2, 4, 5, 5, 2, 12, 14, 5, 3, 0, -1};
		String[] strarr = {"hope you find this helpful!", "wef", "rg", "q2rq2r", "avs", "erhijer0g", "ewofij", "gwe", "q", "random"};
		arr = selectionsort(arr);
		strarr = selectionsort(strarr);
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(strarr));

	}
	
	//O(n^2) TIME, O(1) SPACE, NOT STABLE
		public static <E extends Comparable<E>> E[] selectionsort(E[] arr){
			int index=0;
			for(int x=0;x<arr.length;x++){
				E min = arr[index];
				int minindex =0;
				for(int i=index;i<arr.length;i++){
					if(arr[i].compareTo(min) <=0 || i==index){
						min = arr[i];
						minindex = i;
					}
				}
				arr[minindex] = arr[index];
				arr[index] = min;
				index++;
			}
			return arr;
		}

}
