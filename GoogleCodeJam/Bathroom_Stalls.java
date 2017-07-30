import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Bathroom_Stalls {

	public static void main(String[] args) throws Exception{
		Scanner scan=new Scanner(new File("bathroom.in"));
		PrintWriter writer=new PrintWriter(new File("output.txt"));
		
		int numCases=scan.nextInt();
		for(int cases=1;cases<=numCases;cases++){
			int n=scan.nextInt();
			int k=scan.nextInt();
			k++;
			boolean[] stalls=new boolean[n+2];
			stalls[0]=true;
			stalls[n+1]=true;
			for(int x=1;x<=n;x++){
				stalls[x]=false;
			}
			
			//[true, false, false, false, true]//
			
			int[][] values=new int[n+2][2];
			int[] arr={-1, -1};
			values[0]=arr;
			values[n+1]=arr;
			int lastindex=0;
			while(--k>0){
				int minimax=-1, maximax=-1, nummin=0, indexofmin=0, indexofmax=0;
				for(int i=1;i<n+1;i++){
					int left=0, right=0, index=i;
					if(stalls[i]){
						values[i]=arr;
						continue;
					}
					for(int j=i-1;j>=0;j--){
						if(stalls[j])
							break;
						left++;
					}
	
					for(int j=i+1;j<n+2;j++){
						if(stalls[j])
							break;
						right++;
					}
	
					values[i][0]=left;
					values[i][1]=right;
					
					
					//finds global max and global min of LR
					if(Math.min(left, right) > minimax){
						minimax=Math.min(left, right);
						nummin=0;
						indexofmin=i;
						maximax=Math.max(left, right);
						indexofmax=i;
						
					}
					else if(Math.min(left, right) == minimax){
						nummin++;
						if(Math.max(left, right) > maximax){
							maximax=Math.max(left, right);
							indexofmax=i;
						}
					}
					
	
				}

				if(nummin==0){
					stalls[indexofmin]=true;
					lastindex=indexofmin;
				}
				else{
					stalls[indexofmax]=true;
					lastindex=indexofmax;
				}
			}
			int minimum=Math.min(values[lastindex][0], values[lastindex][1]);
			int maximum=Math.max(values[lastindex][0], values[lastindex][1]);
			
			
			System.out.printf("Case #%d: %d %d\n", cases, maximum, minimum);
			writer.printf("Case #%d: %d %d\n", cases, maximum, minimum);
			
			
		}
		writer.close();
		scan.close();

	}

}
