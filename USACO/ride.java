/*
ID: joelpabraham
LANG: JAVA
TASK: ride
*/

import java.io.*;
import java.util.*;


public class ride {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new FileReader("ride.in"));
		PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
		StringTokenizer input = new StringTokenizer(read.readLine());
		int in=1;
		for(char c:String.valueOf(input.nextToken()).toCharArray()){
			in*=c-64;
		}
		StringTokenizer output = new StringTokenizer(read.readLine());
		int out=1;
		for(char c:String.valueOf(output.nextToken()).toCharArray()){
			out*=c-64;
		}
		if(in%47==out%47){
			write.println("GO");
		}
		else{
			write.println("STAY");
		}
		write.close();
		System.exit(0);    
	}

}
