import java.io.*;
import java.util.*;

public class FencePainting {

	public static void main(String[] args) throws Exception {
		int paint=0;
		Scanner scan=new Scanner(new File("paint.in"));
		PrintWriter writer = new PrintWriter(new File("paint.out"));
		String[] first=scan.nextLine().split(" ");
		String[] second=scan.nextLine().split(" ");
		boolean[] fence=new boolean[101];
		for(int i=Integer.parseInt(first[0]); i<Integer.parseInt(first[1]);i++){
			if(!fence[i]){
				fence[i]=true;
			}
		}
		for(int i=Integer.parseInt(second[0]); i<Integer.parseInt(second[1]);i++){
			if(!fence[i]){
				fence[i]=true;
			}
		}
		for(int i=0;i<fence.length;i++){
			if(fence[i]){
				paint++;
			}
		}
		System.out.println(paint);
		writer.println(paint);
		writer.close();
		
		
	}

}
