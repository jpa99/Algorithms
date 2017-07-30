import java.io.*;
import java.util.*;

public class MilkPails {
	static int x, y, k, m, min, pail_x=0, pail_y=0;
	public static void main(String[] args) throws Exception {
		
		ArrayList<Integer> errors=new ArrayList<Integer>();
		Scanner scan=new Scanner(new File("pails.in"));
		PrintWriter writer = new PrintWriter(new File("pails.out"));
		String[] a=scan.nextLine().split(" ");
		x=Math.min(Integer.parseInt(a[0]), Integer.parseInt(a[1]));
		y=Math.max(Integer.parseInt(a[0]), Integer.parseInt(a[1]));
		
		k=Integer.parseInt(a[2]);
		m=Integer.parseInt(a[3]);
		min=m;
		
		errors.add(m);
		errors.add(Math.abs(m-x));
		errors.add(Math.abs(m-y));
		
		if(k>=2){
			errors.add(Math.abs(m-x-y));
		}

		for(int i=2;i<k;i++){
			errors.add(Math.abs(m-(y-(k-i)*x)));
		}
		
		System.out.println(min(errors));
		writer.println(min(errors));
		writer.close();
		

	}
	
	public static int min(ArrayList<Integer> n){
		int min=n.get(0);
		for(int i:n){
			if(i<min){
				min=i;
			}
		}
		return min;
	}


}
