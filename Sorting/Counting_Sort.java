import java.util.Arrays;

public class Counting_Sort {
	
	/*Counting Sort

	 Problem (informal): Given collection of integers, return sorted collection
	 
	 Algorithm: Use hashtable to store frequency of each integer in range [-k, k] for k = max(|min|, max) then iterate over table and output
	 
	 Complexity:
	 	* Time - O(n+k) (best)/worst/average case, where k = max(|min|, max)
	 	* Space - O(k) to store [-k, k] frequency table
	 
	 Functions Defined:
	 	* countingsort() - main algorithm
	 	
	 */

	public static void main(String[] args) {
		//test arrays
		int[] arr = {1, 4, 3, 2, 64, 3, 2, 4, 5, 5, 2, 12, 14, 5, 3, 0, -1, -99};
		arr = countingsort(arr);
		System.out.println(Arrays.toString(arr));

	}

	//O(n+k) TIME, O(k) SPACE, STABLE
	public static int[] countingsort(int[] arr, int...max){
		int k;
		if(max.length ==1) 
			k = ++max[0];
		else{ 
			k = arr[0];
			for(int i:arr)
				if(Math.abs(i) > k)
					k = Math.abs(i);
			k++;
		}
		int[] count = new int[k];
		int[] negcount = new int[k];
		int maxval =0, minval =0;
		boolean negative = false;
		for(int i=0;i<arr.length;i++){
			int key = arr[i];
			if(key >=0)
				count[key]++;
			else{
				negcount[Math.abs(key)]++;
				if(!negative) negative = true;
			}
		}
		int index =0;
		for(int i=k-1;negative && i>0;i--){
			while(negcount[i] > 0){
				arr[index] = (-1)*i;
				negcount[i]--;
				index++;
			}
		}
		for(int i=0;i<k;i++){
			while(count[i] > 0){
				arr[index] = i;
				count[i]--;
				index++;
			}
		}
		
		return arr;
	}
}
