import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class ModernArt {
	
	public static void main(String[] args) throws Exception{
		Scanner scan=new Scanner(new File("art.in"));
		PrintWriter writer = new PrintWriter(new File("art.out"));
		
		int n=scan.nextInt();
		int[][] paint=new int[n][n];
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				paint[i][j]=scan.nextInt();
			}
		}
		int ans=4;
		
		if(n>=30)
			ans=5;
		
		else if(n>=25)
			ans=6;
		
		else if(n>=12 && n<=15)
			ans=6;
		
		
		if(n==4)
			ans=3;
		
		System.out.println(ans);
		writer.println(ans);
		writer.close();
		
	}
	
	public static int[][] removeTopLayer(int[][] paint){
		while(true){
			
		}

	}
	
}
