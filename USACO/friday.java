import java.io.*;
import java.util.*;

public class friday {

	public static void main(String[] args) throws IOException {
		/*BufferedReader reader= new BufferedReader(new FileReader("friday.in"));
		PrintWriter writer=new PrintWriter(new FileWriter("friday.out"));
		int n=Integer.parseInt(reader.readLine());*/
		
		Scanner scan=new Scanner(new File ("friday.in"));
		scan.nextLine();
		
		int i=1900;
		int[] thirteens={0, 6, 2, 2, 5, 7, 3, 5, 1, 4, 6, 2, 4};
		
		int[] num_thirteens=new int[8];
		
		int n=19;
		while(i<1900+n){
			for(int k=0;k<13;k++){
				num_thirteens[thirteens[k]]++;
				if((i%4==0 && i%100!=0) || (i%400==0)){
					thirteens[k]+=2;
				}
				else{
					thirteens[k]++;
				}
				if(thirteens[k]>7){
					thirteens[k]%=7;
				}
			}
			for(int j:num_thirteens){
				System.out.print(j+" ");
				
			}
			System.out.println("\n\n");
			i++;
		}	
//
		for(int j:num_thirteens){
			System.out.print(j+" ");
		}
		

	}

}
