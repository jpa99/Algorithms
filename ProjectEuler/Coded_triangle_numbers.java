import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Coded_triangle_numbers {

	public static void main(String[] args) throws IOException{
       
		//Initialize array containing words from text file
		String fileName="words.txt";
       String[] words = null;
       try{
          FileReader inputFile = new FileReader(fileName);
          BufferedReader bufferReader = new BufferedReader(inputFile);
          String line=bufferReader.readLine();
          words=line.split(",");
          bufferReader.close();
       }
       catch(Exception e){                     
       }
       
   
       //Initialize list containing triangles numbers
       ArrayList<Integer> triangles=new ArrayList<Integer>();
		int num=0;
		for(int index=1;index<500;index++){
			num+=index;
			triangles.add(num);
		}
		
		//Iterate through array of words and test for triangle numbers
		int num_triangles=0;
		for(String n:words){
			int sum=0;
			for(char i:n.toCharArray()){
				sum+="ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(i)+1;
			}
			if(triangles.contains(sum)){
				num_triangles++;
			}
			System.out.println(n +" "+sum+" ");
		}
		System.out.println(num_triangles);
	}


}
