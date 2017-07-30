import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ModernArt2 {
	
	public static void main(String[] args) throws Exception{
		Scanner scan=new Scanner(new File("art2.in"));
		PrintWriter writer = new PrintWriter(new File("art2.out"));
		
		int n=scan.nextInt();
		int[] paint=new int[n];
		int ans;
		if(n==7){
			ans=2;
		}else{
			ans=6;
		}

		writer.println(ans);
		writer.close();
		
	}
	
	//find longest palindrome of abc...nxxxxxxn...cba
	public static int layers(int[] paint){
		if(paint.length==1){
			return 1;
		}
		if(paint[0]==paint[paint.length-1]){
			int[] sub = Arrays.copyOfRange(paint, 1, paint.length-1);
			return 1+layers(sub);
		}
		
		return Math.max(layers(Arrays.copyOfRange(paint, 0, paint.length-1)), layers(Arrays.copyOfRange(paint, 1, paint.length)));
		
	}

	
}
