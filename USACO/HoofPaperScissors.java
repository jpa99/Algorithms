import java.io.*;
import java.util.*;

public class HoofPaperScissors {

	public static void main(String[] args) throws Exception {
		
		Scanner scan=new Scanner(new File("hps.in"));
		PrintWriter writer = new PrintWriter(new File("hps.out"));
		int n=scan.nextInt();
		int[] moves=new int[n];
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		map.put("H", 0);
		map.put("P", 1);
		map.put("S", 2);
		for(int i=0;i<n;i++){
			moves[i]=map.get(scan.next());
		}
		int[] part1=new int[3];
		int[] part2=numRepeats(moves);
		
		int maxg=Math.max(Math.max(part1[0], part1[1]), part1[2])+ Math.max(Math.max(part1[0], part2[1]),part2[2]);
		for(int piv=0;piv<moves.length;piv++){
			part1[moves[piv]]++;
			part2[moves[piv]]--;
			int lmax=Math.max(Math.max(part1[0], part1[1]), part1[2])+ Math.max(Math.max(part2[0], part2[1]),part2[2]);

			if(lmax>maxg){
				maxg=lmax;
			}
			
		}
		writer.println(maxg);
		writer.close();

		
	}
	
	public static int[] numRepeats(int[] arr){
		int Hdup=0, Pdup=0, Sdup=0;
		for(int i=0;i<arr.length;i++){
			if(arr[i]==0){
				Hdup++;
			}
			if(arr[i]==1){
				Pdup++;
			}
			if(arr[i]==2){
				Sdup++;
			}
		}
		int[] asdf={Hdup, Pdup, Sdup};
		return asdf;
	}
	
}
		