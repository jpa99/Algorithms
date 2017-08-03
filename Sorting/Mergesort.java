import java.util.Arrays;

public class Mergesort {
	
	/*Mergesort

	 Problem (informal): Given collection of objects, return sorted collection
	 
	 Algorithm: Divide and conquer approach -- split array into two subarrays, mergesort subarrays, and merge subarrays
	 
	 Complexity:
	 	* Time - O(nlogn), asymptotically tight since T(n) = 2T(n/2) ==> O(nlogn) via Master Theorem
	 	* Space - O(n) to store subarraays
	 
	 Functions Defined:
	 	* mergesort() - main algorithm
	 
	 Notes
	 	* Sorry, but I can't implement this generically since Java doesn't permit the creation of a generic type array
	 	
	 */

	public static void main(String[] args) {
		//test array
		int[] arr = {1, 4, 3, 2, 64, 3, 2, 4, 5, 5, 2, 12, 14, 5, 3, 0, -1};
		arr = mergesort(arr);
		System.out.println(Arrays.toString(arr));

	}
	
	//O(nlogn) TIME, O(n) SPACE, STABLE
	public static int[] mergesort(int[] arr){
		if(arr.length==1) return arr;
		
		int len = arr.length/2;
		int[] left = new int[len];
		int[] right = new int[arr.length-len];
		for(int i=0;i<arr.length;i++){
			if(i < len){
				left[i] = arr[i];
			}
			else{
				right[i-len] = arr[i];
			}
		}
		
		left = mergesort(left);
		right = mergesort(right);
		int lefthead = 0, righthead = 0;
		int[] merge = new int[arr.length];
		for(int i=0;i<arr.length;i++){
			int leftval = lefthead < left.length? left[lefthead]: Integer.MAX_VALUE;
			int rightval = righthead < right.length? right[righthead]: Integer.MAX_VALUE;
			if(leftval <= rightval){
				arr[i] = leftval;
				lefthead++;
			}
			else{
				arr[i] = rightval;
				righthead++;
			}
		}
		return arr;
	}


}
