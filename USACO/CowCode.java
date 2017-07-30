import java.io.*;
import java.util.*;

public class CowCode {
	static int counter=0;
	public static void main(String[] args) throws Exception {
		long start=System.currentTimeMillis();
		Scanner scan=new Scanner(new File("cowcode.in"));
		PrintWriter writer = new PrintWriter(new File("cowcode.out"));
		String text=scan.next();
		long n=Long.parseLong(scan.next());

		String c=f(n, text);
		System.out.println(c);
		
		writer.println(c);
		writer.close();
		long end=System.currentTimeMillis()-start;
		System.out.println("Time: "+end+" ms");
	}
	public static String f(long n, String word){
		int len=word.length();
		if(n <= len){
			if(n<=0){
				return String.valueOf(word.charAt(len-1));
			}
			return String.valueOf(word.charAt((int)(n-1)));
		}
		int iter=(int)(Math.floor(Math.log((n-1)/len)/Math.log(2)));
		long less=(long) (len*Math.pow(2, iter));
		if(n-less==1){
			return f(n-1, word);
		}
		return f((long)(n-1-less), word);
	}

}