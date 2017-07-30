import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.io.*;
import java.time.*;
import java.math.*;
import java.awt.*;

public class Digits {

	public static void main(String[] args) throws Exception{
		Scanner scan= new Scanner(new File("input.txt"));
		PrintWriter writer=new PrintWriter(new File("output.txt"));
		int numinputs=scan.nextInt();
		scan.nextLine();
		
		
		for(int cases=1;cases<=numinputs;cases++){
			int[] nums=new int[10];
			int[] removechar=new int[200];
			char[] arr=scan.nextLine().toCharArray();
			List<Character> list = new ArrayList<Character>();
			for(char c : arr) {
				if(c=='Z'){
					nums[0]++;
					removechar['Z']++;
					removechar['E']++;
					removechar['R']++;
					removechar['O']++;
				}
				else if(c=='W'){
					nums[2]++;
					removechar['T']++;
					removechar['W']++;
					removechar['O']++;
				}
				else if(c=='U'){
					nums[4]++;
					removechar['F']++;
					removechar['O']++;
					removechar['U']++;
					removechar['R']++;
					
				}
				else if(c=='X'){
					nums[6]++;
					removechar['S']++;
					removechar['I']++;
					removechar['X']++;
				}
				else if(c=='G'){
					nums[8]++;
					removechar['E']++;
					removechar['I']++;
					removechar['G']++;
					removechar['H']++;
					removechar['T']++;
				}
			    list.add(c);
			}
			for(int i=0;i<list.size();i++){
				char c=list.get(i);
				if(removechar[c]>0){	
					list.remove(i);
					removechar[c]--;
					i--;
				}
			}
			for(char c : list) {
				if(c=='O'){
					nums[1]++;
					removechar['O']++;
					removechar['N']++;
					removechar['E']++;

				}
				else if(c=='H'){
					nums[3]++;
					removechar['T']++;
					removechar['H']++;
					removechar['R']++;
					removechar['E']++;
					removechar['E']++;
				}
				else if(c=='F'){
					nums[5]++;
					removechar['F']++;
					removechar['I']++;
					removechar['V']++;
					removechar['E']++;
					
				}
				else if(c=='S'){
					nums[7]++;
					removechar['S']++;
					removechar['E']++;
					removechar['V']++;
					removechar['E']++;
					removechar['N']++;
				}
			}
			
			for(int i=0;i<list.size();i++){
				char c=list.get(i);
				if(removechar[c]>0){
					list.remove(i);
					removechar[c]--;
					i--;
				}
			}
			
			for(char c : list) {
				if(c=='I'){
					nums[9]++;
				}
			}
			
			String number="";
			for(int n=0;n<nums.length;n++){
				String x=String.join("", Collections.nCopies(nums[n], String.valueOf(n)));
				number+=x;
			}
			
			//r1: z, zero; w, two, u, four; x, six, g, eight
			//r2: o, one; h, three; f, five; s, seven
			//r3: i, nine
			
			
			
			String output="Case #"+cases+": "+number;
			System.out.println(output);
			writer.println(output);
		}
		scan.close();
		writer.close();
	}

}
