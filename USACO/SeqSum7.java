import java.io.*;
import java.util.*;

public class SeqSum7 {
	static int[] cows;
	public static void main(String[] args) throws Exception{
		int num, count, max_length=0, length;
		double sum;
		boolean div=false;
		Scanner scan=new Scanner(new File("div7.in"));
		PrintWriter writer = new PrintWriter(new File("div7.out"));
		num=Integer.parseInt(scan.nextLine());
		cows=new int[num];
		for(int i=0;i<num;i++){
			cows[i]=Integer.parseInt(scan.nextLine());
		}
		for(length=cows.length-1;length>0;length--){
			for(int start=0;(start+length)<cows.length;start++){
				count=sum(start, start+length);
				if(count%7==0){
					div=true;
					
				}
			}
			if(div==true){
				break;
			}
		}
		System.out.println(length+1);
		writer.println(length+1);
		writer.close();
		
	}
	
	public static int sum(int start, int end){
		int sum=0;
		for(int i=start;i<=end;i++){
			sum+=cows[i];
		}
		return sum;
	}

}
