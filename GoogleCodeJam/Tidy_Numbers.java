import java.util.*;
import java.io.*;

public class Tidy_Numbers {

	public static void main(String[] args) throws Exception {
		Scanner scan=new Scanner(new File("tidy.in"));
		PrintWriter writer=new PrintWriter(new File("output.txt"));
		
		int n=scan.nextInt();
		scan.nextLine();
		for(int asdf=1;asdf<=n;asdf++){
			String num=scan.nextLine();
			
			System.out.println(num);
			long nu=Long.parseLong(num);
			String tidy;
			int i=(int) nu;

			while(true){
				if(num.contains("0")){
					tidy=nines(num);
					break;
				}
				int tid=isTidy(i);
				if(tid<0){
					tidy=String.valueOf(i);
					break;
				}
				System.out.println(tid);
				String str=String.valueOf(i);
				String decrement=String.valueOf(Integer.parseInt(str.substring(tid, tid+1))-1);
				String len=new String(new char[str.length()-tid-1]).replace("\0", "9");
				i=(int) Long.parseLong(str.substring(0, tid)+decrement+len);	
			}
			
			
			//parse String 
			long indignation=Long.parseLong(tidy);
			String a="Case #"+asdf+": "+indignation;
			System.out.println(a);
			writer.println(a);
		}
		writer.close();
		scan.close();
	}
	
	//return -1 if tidy or i >=0 where char[i] > char[i+1]
	public static int isTidy(long n){
		String num=String.valueOf(n);
		for(int i=0;i<num.length()-1;i++){
			if(Integer.valueOf(num.substring(i, i+1)) > Integer.valueOf(num.substring(i+1, i+2))){
				return i;
			}
		}
		return -1;
	}
	
	//returns sequence of 9s less than int
	public static String nines(String n){
		return new String(new char[n.length()-1]).replace("\0", "9");
	}

}
