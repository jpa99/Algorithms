import java.util.Arrays;

public class Radix_Sort {
	
	/* (LSD) Radix Sort

	 Problem (informal): Given collection of integers, return sorted collection
	 
	 Algorithm: Sorts by least significant digit until most significant digit
	 
	 Complexity:
	 	* Time - O(nk) (best)/worst/average case, where k =log(max) [i.e max number of digits]
	 	* Space - O(n+k) to store output 
	 
	 Functions Defined:
	 	* radixsort() - main algorithm
	 
	 Notes
	 	* Advantage over counting sort since counting sort is n^2 for range(n^2)
	 	* Radix sort has high constants often, so quicksort typically preferred since it's also general purpose
	 	* Radix sort has advantage in stability
	 	
	 */

	public static void main(String[] args) {
		//test arrays
		int[] arr = {1, 4, 3, 2, 64, 3, 2, 4, 5, 5, 2, 12, 14, 5, 3, 0};
		arr = radixsort(arr);
		System.out.println(Arrays.toString(arr));

	}

	//O(n+k) TIME, O(k) SPACE, STABLE
	public static int[] radixsort(int[] arr, int...maximum){
		int max;
		if( maximum.length > 0 ) 
			max = ++maximum[0];
		else{
			max = arr[0];
			for(int i:arr)
				if(i > max)
					max = i;
		}
		
		for(int exp = 1; max/exp > 0; exp*=10){ // Counting sort of digit exp
			int[] count = new int[10];
			int[] output = new int[arr.length];
			for(int i=0;i<arr.length;i++){
				count[(arr[i]/exp) % 10]++;
			}
			for(int i=1;i<10;i++){
				count[i]+=count[i-1];
			}
			for(int i=arr.length-1;i>=0;i--){
				output[count[(arr[i]/exp) % 10]-1] = arr[i];
				count[(arr[i]/exp) % 10]--;
			}
			arr = output;
			
		}
		return arr;
	}
}
