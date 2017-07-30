import java.util.ArrayList;
import java.lang.*;

public class Lexicogrphic_permutations {

	public static void main(String[] args) {
		
	}
	
	
	
	public static void sort(ArrayList<Integer> perms){
		int index=0;
		while(index!=perms.size()){
			int min=find_min(perms, index);
			swap(perms, index, min);
		}
	} 
	
	public static void swap(ArrayList<Integer> arr, int index_a, int index_b){
		int temp=arr.get(index_a);
		arr.set(index_a, arr.get(index_b));
		arr.set(index_b, temp);
	}
	
	public static int find_min(ArrayList<Integer> arr, int start_index){
		int min=start_index;
		for(int i=start_index;i<arr.size();i++){
			if(arr.get(i)<arr.get(min)){
				min=i;
			}
		}
		return min;
	}

}
