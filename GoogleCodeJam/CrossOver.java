import java.util.*;
import java.io.*;
public class CrossOver {

	public static void main(String[] args) throws Exception{
		Scanner scan=new Scanner(new File("output.txt"));
		int n=scan.nextInt();
		int[] stocks=new int[n];
		double[] LTMA=new double[n+1];
		double[] STMA=new double[n+1];
		
		//instantiates with first 300 ints, and scans
		int ltma_hash=0, stma_hash=0;
		for(int i=1;i<=300;i++){
			stocks[i-1]=scan.nextInt();
			ltma_hash+=stocks[i-1];
			if(i>=241)
				stma_hash+=stocks[i-1];
		}

		LTMA[300]=Math.round(100.0*ltma_hash/300.0)/100.0;
		STMA[300]=Math.round(100.0*stma_hash/60.0)/100.0;
		for(int i=301;i<n;i++){
			stocks[i-1]=scan.nextInt();
			ltma_hash+=stocks[i-1];
			stma_hash+=stocks[i-1];
			ltma_hash-=stocks[i-301];
			stma_hash-=stocks[i-61];
			LTMA[i]=Math.round(100.0*(ltma_hash)/(300.0))/100.0;
			STMA[i]=Math.round(100.0*(stma_hash)/(60.0))/100.0;
			if((STMA[i-1] > LTMA[i-1] && STMA[i] <= LTMA[i]) || (STMA[i-1] < LTMA[i-1] && STMA[i] >= LTMA[i]) || (STMA[i-1] == LTMA[i-1] && STMA[i] != LTMA[i])){
				System.out.println(i+" "+STMA[i]+" "+LTMA[i]);
			}
		}

	}

}
