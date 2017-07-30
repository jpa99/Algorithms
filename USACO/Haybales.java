import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Haybales {

	public static void main(String[] args) throws Exception{
		PrintWriter writer = new PrintWriter(new File("haybales.out"));
		Scanner scan=new Scanner(new File("haybales.in"));
		
		int n=scan.nextInt();
		int q=scan.nextInt();
		int[] bales=new int[n];
		for(int i=0;i<n;i++){
			bales[i]=scan.nextInt();
		}
		Arrays.sort(bales);
		List<Integer> haybales = Arrays.stream(bales).boxed().collect(Collectors.toList());
		//[2, 3, 5, 7]
		
		for(int i=0;i<q;i++){
			int s=scan.nextInt();
			int e=scan.nextInt();
			int startIndex=ceilIndex(haybales, s);
			int endIndex=floorIndex(haybales, e);
			int start=0, end=0;
			int diff=0;
			if(startIndex==-1 || endIndex==-1){
				diff=0;
			}else if(startIndex==endIndex){
				diff=1;
			}else{
				start=haybales.indexOf(startIndex)+1;
				end=haybales.indexOf(endIndex)+1;
				diff=end-start+1;
			}
			
			
			System.out.println(diff);
			
			
			writer.println(diff);
			
		}
		writer.close();

	}
	
	//decrements query until it finds closest index where a haybale is located, lower than query
	public static int floorIndex(List<Integer> haybales, int query){
		for(int i=query;i>=0;i--){
			if(haybales.contains(i)){
				return i;
			}
		}
		return -1;
	}
	
	public static int ceilIndex(List<Integer> haybales, int query){
		for(int i=query;i<=haybales.get(haybales.size()-1);i++){
			if(haybales.contains(i)){
				return i;
			}
		}
		return -1;
	}

}
