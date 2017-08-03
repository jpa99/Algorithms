import java.util.Arrays;
import java.util.Random;

public class Sorting {
	/*
	 
	 Generally, based on runtime benchmarking with large arrays (n = 10^9+) of random values, the runtime hierarchy is as follows. I'm not quite sure why quicksort seems to take so long:
	 
	 Counting Sort < Timsort < Radix Sort < Heapsort < Mergesort < Selection Sort < Bubble sort < Insertion Sort < Quicksort (?)
	
	 */

	public static void main(String[] args) {
		long start; 
		//easy test array
		//int[] arr = {3, 5, 4, 2, 12, 1, 17, 7, 6, 5, 9, 32, 0, 13, 8, 9, Integer.MAX_VALUE/100};
		
		
		//Randomly generate array
		Random rng = new Random();
		int arraysize = 1000;
		int[] arr = new int[arraysize];
		for(int i=0;i<arraysize;i++){
			arr[i] = rng.nextInt(arraysize);
		}
		
		
		//System.out.println("Original Array: "+Arrays.toString(arr)+"\n\n");
		
		//Timort
		start = System.nanoTime();
		Arrays.sort(arr);
		System.out.println("Timsort: "+((System.nanoTime()-start)*1E-6) + " ms\n\n");

		
		//Radix Sort
		start = System.nanoTime();
		radixsort(arr);
		System.out.println("Radix Sort: "+((System.nanoTime()-start)*1E-6) + " ms\n\n");

		//Counting Sort
		start = System.nanoTime();
		countingsort(arr);
		System.out.println("Counting Sort: "+((System.nanoTime()-start)*1E-6) + " ms\n\n");

		//Quicksort
		start = System.nanoTime();
		//quicksort(arr);
		System.out.println("Quicksort: "+((System.nanoTime()-start)*1E-6) + " ms\n\n");
		
		//Heapsort
		start = System.nanoTime();
		heapsort(arr);
		System.out.println("Heapsort: "+((System.nanoTime()-start)*1E-6) + " ms\n\n");
		
		//Mergesort
		start = System.nanoTime();
		mergesort(arr);
		System.out.println("Mergesort: "+((System.nanoTime()-start)*1E-6) + " ms\n\n");
		
		//Insertion Sort
		start = System.nanoTime();
		insertionsort(arr);
		System.out.println("Insertion Sort: "+((System.nanoTime()-start)*1E-6) + " ms\n\n");
		
		//Bubble Sort
		start = System.nanoTime();
		bubblesort(arr);
		System.out.println("Bubble Sort: "+((System.nanoTime()-start)*1E-6) + " ms\n\n");
		
		//Selection Sort
		start = System.nanoTime();
		selectionsort(arr);
		System.out.println("Selection Sort: "+((System.nanoTime()-start)*1E-6) + " ms\n\n");
	}
	
	public static int getMax(int[] arr){
		int max = arr[0];
		for(int i:arr) if(i > max) max = i;
		return max;
	}

	//O(n) TIME, O(k) SPACE, STABLE
	public static int[] bucketsort(int[] arr){
		return arr;
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
	
	
	//O(n+k) TIME, O(k) SPACE, STABLE
	public static int[] countingsort(int[] arr, int...max){
		int k;
		if(max.length > 0) 
			k = ++max[0];
		else{ 
			k = arr[0];
			for(int i:arr)
				if(i > k)
					k = i;
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
		
	
	//O(nlogn) TIME, O(1) SPACE, NOT STABLE
	public static int[] quicksort(int[] arr, int...range){
		int p = range.length > 0 ? range[0] : 0;
	    int r = range.length > 1 ? range[1] : arr.length-1;
		
		if(p >= r) return arr;
		
		int randint = p + (int) ((r-p+1)*Math.random()); // random integer in range [p,r]
		int q = arr[randint];
		arr[randint] = arr[r];
		arr[r] = q;
		
		int i=p-1;
		for(int j=p;j<r;j++){
			if(arr[j] <= q){
				int temp = arr[j];
				arr[j] = arr[++i];
				arr[i] = temp;
			}
		}
		
		arr[r] = arr[++i];
		arr[i] = q;
		quicksort(arr, 0, i-1);
		quicksort(arr, i+1, r);
		
		return arr;
	}
	
	
	//O(nlogn) TIME, O(1) SPACE, NOT STABLE
	public static int[] heapsort(int[] arr){
		int heaplength = arr.length;
		for(int i = arr.length/2; i>0;i--){
			arr = maxheapify(arr, i, heaplength);
		}
		
		for(int i=arr.length-1;i>=0;i--){
			int max = arr[0];
			arr[0] = arr[i];
			arr[i] = max;
			heaplength--;
			arr = maxheapify(arr, 1, heaplength);
		}
		
		return arr;
	}
	
	public static int[] maxheapify(int[] arr, int node, int heaplength){
		int left = node*2;
		int right = node*2+1;
		int largest = node;
		
		if(left <= heaplength && arr[left-1] > arr[node-1]){
			largest = left;
		}
		if(right <= heaplength && arr[right-1]  > arr[largest-1]){
			largest = right;
		}
		
		if(largest != node){
			int temp = arr[node-1];
			arr[node-1] = arr[largest-1];
			arr[largest-1] = temp;
			maxheapify(arr, largest, heaplength);
		}
		return arr;
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
	
	
	//O(n^2) TIME, O(1) SPACE, STABLE
	public static int[] insertionsort(int[] arr){
		for(int i=0;i<arr.length;i++){
			int key = arr[i];
			boolean flag = false;
			int temp = 0;
			for(int j=0;j<=i;j++){
				if(!flag && key<arr[j]){
					flag = true;
					temp = key;
				}
				if(flag){
					int t = arr[j];
					arr[j] = temp;
					temp = t;
				}
			}
		}
		return arr;
	}
	
	
	//O(n^2) TIME, O(1) SPACE, STABLE
	public static int[] bubblesort(int[] arr){
		for(int i=0;i<arr.length;i++){
			for(int j=0; j<arr.length-1;j++){
				if(arr[j] > arr[j+1]){
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		return arr;
	}
	
	
	//O(n^2) TIME, O(1) SPACE, NOT STABLE
	public static int[] selectionsort(int[] arr){
		int index=0;
		for(int x=0;x<arr.length;x++){
			int min = Integer.MAX_VALUE;
			int minindex =0;
			for(int i=index;i<arr.length;i++){
				if(arr[i] < min){
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
