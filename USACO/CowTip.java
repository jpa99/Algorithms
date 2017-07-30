import java.io.*;
import java.util.*;

public class CowTip {
	static int counter=0;
	public static void main(String[] args) throws Exception {
		long start=System.currentTimeMillis();
		Scanner scan=new Scanner(new File("cowtip.in"));
		PrintWriter writer = new PrintWriter(new File("cowtip.out"));
		
		int n=Integer.parseInt(scan.nextLine());
		int[][] farm=new int[n][n];
		
		for(int i=0;i<n;i++){
			String str=scan.nextLine();
			for(int j=0;j<n;j++){
				farm[i][j]=Integer.parseInt(str.substring(j, j+1));
			}
		}
		
		int count=0;
		while(tipped(farm)>0){
			int[] tip=maxTipped(farm);
			farm=invert(farm, tip[0], tip[1]);
			for(int[] row:farm){
				System.out.println(Arrays.toString(row));
			}
			System.out.println("\n\n");
			count++;
		}
		
		System.out.println(count);
		writer.println(count);
		writer.close();
		
		long end=(System.currentTimeMillis()-start)*10;
		System.out.println("Time: "+end+" ms");
	}
	
	public static int tipped(int[][] farm){
		int count=0;
		for(int[] row:farm){
			for(int el:row){
				if(el==1){
					count++;
				}
			}
		}
		return count;
	}
	
	public static int[][] invert(int[][] farm, int row, int col){
		for(int i=0;i<=row;i++){
			for(int j=0;j<=col;j++){
				farm[i][j]=((farm[i][j]+1)%2);
			}
		}
		return farm;
	}
	
	public static int[] maxTipped(int[][] farm){
		int max=0;
		int maxi=0, maxj=0;
		for(int i=0;i<farm.length;i++){
			for(int j=0;j<farm[0].length;j++){
				if((farm[i][j]==1) && ((i+j)>max)){
					max=i+j;
					maxi=i;
					maxj=j;
				}
			}
		}
		int[] asdf={maxi, maxj};
		return asdf;
	}

}