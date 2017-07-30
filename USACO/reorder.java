import java.io.*;
import java.util.*;
public class reorder {
	
	static int[] order_final;
	static int[] order_intial;

	public static void main(String[] args) throws IOException{
		BufferedReader reader=new BufferedReader(new FileReader("text.txt"));
		PrintWriter writer=new PrintWriter(new FileWriter("textout.txt"));
		int n=Integer.parseInt(reader.readLine());
		int[] order_initial=new int[n];
		order_final=new int[n];
		for(int i=0;i<n;i++){
			order_initial[i]=Integer.parseInt(reader.readLine());
		}
		for(int i=0;i<n;i++){
			order_final[i]=Integer.parseInt(reader.readLine());
		}
		for(int i:order_initial){
			int[] temp=order_initial.clone();
			int replaced=i;
			while(!temp.equals(order_final)){
				replaced=cycle(replaced, temp);
				
			}
			
		}

	}
	
	//Neccessariliy array of distinct integers
	public static int indexOf(int element, int[] arr){
		for(int i=0;i<arr.length;i++){
			if(arr[i]==element){
				return i;
			}
		}
		return -1;
	}
	
	public static int cycle(int i, int[] temp){
		int index = indexOf(i, order_final);
		int n=order_final[index];
		temp[index]=i;
		return n;
	}

}
