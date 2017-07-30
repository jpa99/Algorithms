import java.io.*;
import java.util.*;

public class AngryCows2 {

	public static void main(String[] args) throws Exception{
		int num, cows, min_power=0,temp_power=0, max_power;
		ArrayList<Integer> arr;
		Scanner scan=new Scanner(new File("angry.in"));
		PrintWriter writer = new PrintWriter(new File("angry.out"));
		String[] s=scan.nextLine().split(" ");
		num=Integer.parseInt(s[0]);
		int[] bales=new int[num];
		cows=Integer.parseInt(s[1]);
		for(int i=0;i<num;i++){
			bales[i]=Integer.parseInt(scan.nextLine());
		}
		Arrays.sort(bales);
		
		arr=new ArrayList<Integer>();
		for(int x:bales){
			arr.add(x);
		}
		
		max_power=(int) (Math.ceil((0.5)*(bales[bales.length-1]-bales[0])));
		for(int j=0;j<=max_power;j++){
			
		}
		System.out.println(max_power);
		writer.println(min_power);
		writer.close();

	}
	
	public static void explode(int a, int b, ArrayList<Integer> arr){
		for(int n=arr.size()-1;n>=0;n--){
			if(a<=arr.get(n) && arr.get(n)<=b){
				arr.remove(n);
			}
		 }
	}
	

}
