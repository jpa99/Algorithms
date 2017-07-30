import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class BovineGenomics_bruteforce {

	public static void main(String[] args) throws Exception{
		Scanner scan=new Scanner(new File("cownomics.in"));
		PrintWriter writer = new PrintWriter(new File("cownomics.out"));
		
		int n=scan.nextInt();
		int m=scan.nextInt();
		scan.nextLine();
		String[] spot=new String[n];
		String[] plain=new String[n];
		for(int i=0;i<n;i++){
			spot[i]=scan.nextLine();
			
		}
		for(int i=0;i<n;i++){
			plain[i]=scan.nextLine();
		}
		
		int minlen=m;
		int gs=0;
		int ge=0;
		
		for(int i=0;i<m;i++){
			for(int j=i+1;j<=m;j++){
				if(disjoint(spot, plain, i, j)){
					int len=j-i;
					if(len<minlen){
						minlen=len;
						gs=i;
						ge=j;
					}
				}
			}
		}
		System.out.println(minlen);
		writer.println(minlen);
		writer.close();
		
		

	}
	
	public static boolean disjoint(String[] spotarr, String[] plainarr, int s, int e){
		String[] spots=new String[spotarr.length];
		String[] plains=new String[plainarr.length];
		for(int i=0;i<spots.length;i++){
			String st=spotarr[i];
			String pt=plainarr[i];
			spots[i]=st.substring(s, e);
			plains[i]=pt.substring(s, e);
		}
		
		boolean contains=true;
		for(int i=0;i<spots.length;i++){
			for(int j=0;j<spots.length;j++){
				if(spots[i].equals(plains[j])){
					contains=false;
					
				}
			}
		}
		
		return contains;
	}
	
	

}
