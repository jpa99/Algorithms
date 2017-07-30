import java.io.*;
import java.util.*;

public class PromotionCounting {

	public static void main(String[] args) throws Exception {
		int db, ds, dg, dp;
		Scanner scan=new Scanner(new File("promote.in"));
		PrintWriter writer = new PrintWriter(new File("promote.out"));
		
		scan.nextLine();
		ds=change(scan.nextLine().split(" "));
		dg=change(scan.nextLine().split(" "));
		dp=change(scan.nextLine().split(" "));
		
		int silver=ds+dp+dg;
		int gold=dp+dg;
		int platinum=dp;
		
		System.out.println(silver);
		System.out.println(gold);
		System.out.println(platinum);
		writer.println(silver);
		writer.println(gold);
		writer.println(platinum);
		writer.close();
		
		
	}
	
	public static int change(String[] arr){
		return Integer.parseInt(arr[1])-Integer.parseInt(arr[0]);
	}

}
