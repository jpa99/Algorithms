import java.io.*;
import java.util.*;

public class AngryCows {

	public static void main(String[] args) throws Exception {
		int max_exploded=0, start, end, count=1, exploded=0, temp_start, temp_end;
		ArrayList<Integer> arr=new ArrayList<Integer>();
		
		Scanner scan=new Scanner(new File("angry.in"));
		PrintWriter writer = new PrintWriter(new File("angry.out"));
		int num=Integer.parseInt(scan.nextLine());
		int[] bales=new int[num];
		for(int i=0;i<num;i++){
			bales[i]=Integer.parseInt(scan.nextLine());
		}
		Arrays.sort(bales);
		
		for(int index=0;index<bales.length;index++){
			arr=new ArrayList<Integer>();
			for(int x:bales){
				arr.add(x);
			}
			int blast=bales[index];
			arr.remove(index);
			start=blast-1;
			end=blast+1;
			while(exists(start, end, arr)>0){
				temp_start=first(start, end, arr);
				temp_end=last(start, end, arr);
				explode(start, end, arr);
				count++;
				start=temp_start-count;
				end=temp_end+count;
			}
			count=1;
			exploded=bales.length-arr.size();
			max_exploded=Math.max(max_exploded, exploded);
		}
		
		System.out.println(max_exploded);
		writer.println(max_exploded);
		writer.close();
		
		
	}
	
	public static int exists(int a, int b, ArrayList<Integer> arr){
		int range=0;
		for(int n: arr){
			if(n>=a && n<=b){
				range++;
			}
		}
		return range;
	}
	
	public static int first(int a, int b, ArrayList<Integer> arr){
		int min=1000000;
		for(int n=0;n<arr.size();n++){
			if(arr.get(n)>=a && arr.get(n)<=b){
				min=Math.min(min,  arr.get(n));
			}
		}
		return min;
	}
	
	public static int last(int a, int b, ArrayList<Integer> arr){
		int max=0;
		for(int n=0;n<arr.size();n++){
			if(arr.get(n)>=a && arr.get(n)<=b){
				max=Math.max(max,  arr.get(n));
			}
		}
		return max;
	}
	
	public static void explode(int a, int b, ArrayList<Integer> arr){
		for(int n=arr.size()-1;n>=0;n--){
			if(a<=arr.get(n) && arr.get(n)<=b){
				arr.remove(n);
			}
		 }
	}


}
