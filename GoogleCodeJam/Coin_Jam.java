import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Coin_Jam {

	public static void main(String[] args) throws Exception{
		Scanner scan=new Scanner(new File("input.txt"));
		
		PrintWriter writer=new PrintWriter(new File("output.txt"));
		
		int t=scan.nextInt();
		int len=scan.nextInt();
		int numjams=scan.nextInt();
		System.out.println("Case #1:");
		//iterate through possibilities, test if jamcoin, 
		int max=(int) Math.pow(2, len-2);
		int jams=0;
		for(int i=0;i<max && jams < numjams;i++){
			String istring = Integer.toBinaryString(i);
			istring = String.format("%"+String.valueOf(len-2)+"s", istring).replace(' ', '0');
			istring="1"+istring+"1";
			System.out.print(istring+" ");
			for(int a=2;a<=10;a++){
				System.out.print(convert(istring, a)+" ");
			}
			
			System.out.println();
			int[] nums=out(istring);
			System.out.println(Arrays.toString(nums));/*
			if(IntStream.of(nums).anyMatch(x -> x == -1)){
				continue;
			}
			
			System.out.print(istring+" ");
			for(int el:nums){
				System.out.print(el+" ");
			}
			System.out.println();
			jams++;*/
		}
		

	}
	public static int convert(String s, int base){
		int num=0;
		int len=s.length()-1;
		if(len==0)
			return Integer.parseInt(s);
		if(s.substring(0, 1).equals("1"))
			return (int)Math.pow(base, len)+convert(s.substring(1), base);
		else
			return convert(s.substring(1), base);
	}
	
	//input String ofones and zeros, output is integer array of length 9 with index i corresponding to the divisor of the input in base i+2
	
	public static int[] out(String s){
		int[] nums=new int[9];
		for(int base=2;base<=10;base++){
			int num=convert(s, base);
			nums[base-2]=-1;
			for(int x=2;x<=(int)(Math.sqrt(num))+1;x++){
				if(num%x==0){
					nums[base-2]=x;
					break;
				}
			}
		}
		return nums;
	}
	
	
	public static boolean isDiv(String n){
		int numdivisors=0;
		for(int i=2;i<=10;i++){
			int num=convert(n, i);
			for(int x=2;x<=(int)(Math.sqrt(num))+1;x++){
				if(num%i==0){
					numdivisors++;
					break;
				}
			}
		}
		if(numdivisors==9)
			return true;
		return false;
	}
	
	public static String divisors(String n){
		String s="";
		for(int i=2;i<=10;i++){
			int num=convert(n, i);
			for(int x=2;x<=(int)(Math.sqrt(num))+1;x++){
				if(num%i==0){
					s+=String.valueOf(x)+" ";
					break;
				}
			}
		}
		return s;
	}
	
	//1001/
	public static String binary_convert(int n, int len){
		//return max power of 2 leq n
		if(n==0){
			String s="";
			for(int i=0;i<len;i++)
				s+="0";
			return s;
		}
		if(n==1)
			return binary_convert(0, len-1)+"1";
		
		int b=(int)Math.floor(Math.log(n)/Math.log(2));
		return "1"+binary_convert(n-(int)(Math.pow(2, b)), b);
	}
	
	// binary string --> int
	public static int convertss(String s, int base){
		int num=0;
		int len=s.length()-1;
		if(len==0)
			return Integer.parseInt(s);
		if(s.substring(0, 1).equals("1"))
			return (int)Math.pow(base, len)+convert(s.substring(1), base);
		else
			return convert(s.substring(1), base);
	}
	
	public static String[] gen_bin_array(int len){
		int n=(int) Math.pow(2, len-1);
		String[] arr=new String[n];
		for(int i=0; i < n; i++){
			arr[i]=binary_convert(i+n, 10);
		}
		return arr;
	}

}
