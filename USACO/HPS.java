import java.io.*;
import java.util.*;

public class HPS {
	static int counter=0;
	public static void main(String[] args) throws Exception {
		long start=System.currentTimeMillis();
		Scanner scan=new Scanner(new File("hps.in"));
		PrintWriter writer = new PrintWriter(new File("hps.out"));
		int n=scan.nextInt();
		int[] a=new int[n];
		int[] b=new int[n];
		for(int i=0;i<n;i++){
			a[i]=scan.nextInt();
			b[i]=scan.nextInt();
		}
		
		int r=0, p=0, s=0;
		int max=0;
		int currentmax;
		for(int i=0;i<6;i++){
			switch(i){
			case 0:
				r=1;
				p=2;
				s=3;
				break;
			case 1:
				r=1;
				p=3;
				s=2;
				break;
			case 2:
				r=2;
				p=1;
				s=3;
				break;
			case 3:
				r=2;
				p=3;
				s=1;
				break;
			case 4:
				r=3;
				p=1;
				s=2;
				break;
			case 5:
				r=3;
				p=2;
				s=1;
				break;
			}
			
			currentmax=0;
			for(int j=0;j<n;j++){
				if(greater(a[j], b[j], r, p, s)){
					currentmax++;
				}
			}
			
			if(currentmax>max){
				max=currentmax;
			}
		}
		
		System.out.println(max);
		writer.println(max);
		writer.close();
		long end=System.currentTimeMillis()-start;
		System.out.println("Time: "+end+" ms");
	}
	
	public static boolean greater(int a, int b, int rock, int paper, int scissors){
		if(a==rock && b==scissors){
			return true;
		}
		else if(a==scissors && b==paper){
			return true;
		}
		else if(a==paper && b==rock){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	//1, 1, 2, 2, 3, 3
	//2, 3, 1, 3, 1, 2
	//3, 2, 3, 1, 2, 1
}