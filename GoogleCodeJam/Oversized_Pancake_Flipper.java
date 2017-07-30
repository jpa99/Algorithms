import java.io.*;
import java.util.*;

public class Oversized_Pancake_Flipper {

	public static void main(String[] args) throws Exception{
		Scanner scan=new Scanner(new File("pancakes.in"));
		PrintWriter writer=new PrintWriter(new File("pancakes.txt"));
		int n=scan.nextInt();
		scan.nextLine();
		for(int asdf=1;asdf<=n;asdf++){
			String[] line=scan.nextLine().split(" ");
			String pancake=line[0];
			int size=Integer.parseInt(line[1]);
			boolean[] row=new boolean[pancake.length()];
			for(int i=0;i<row.length;i++){
				row[i]=(pancake.substring(i, i+1).equals("+"));
			}
			
			int maxstartflip=row.length-size;
			int numflips=0;
			
			/*--------------------------------------------------------------------------
			 *       parses input to boolean[] row containing pancake in booleans
			 *--------------------------------------------------------------------------*/
			
			
			
			if(maxstartflip < size){
				HashSet<Boolean> set1=new HashSet<Boolean>();
				for(int iterator=maxstartflip;iterator<size;iterator++){
					set1.add(row[iterator]);
				}
				if(set1.contains(true) && set1.contains(false)){
					System.out.printf("Case #%d: IMPOSSIBLE\n", asdf);
					writer.printf("Case #%d: IMPOSSIBLE\n", asdf);
					continue;
				}
			}
			
			/*--------------------------------------------------------------------------
			 *             tests impossibility with intersection of sets
			 *--------------------------------------------------------------------------*/
			
			
			
			boolean impossible=false;
			/////////
			while(indexOf(row, false)>=0){		
				int i=indexOf(row, false);
				if(i > maxstartflip){
					i=maxstartflip;
				}
				row=flip(i, size, row);
				numflips++;
				if(numflips > row.length){
					impossible=true;
					break;
				}
			}
			if(impossible){
				System.out.printf("Case #%d: IMPOSSIBLE\n", asdf);
				writer.printf("Case #%d: IMPOSSIBLE\n", asdf);
				continue;
			}
			System.out.printf("Case #%d: %d\n", asdf, numflips);
			writer.printf("Case #%d: %d\n", asdf, numflips);
			
			
		}
		
		scan.close();
		writer.close();

	}
	
	public static boolean[] flip(int start, int size, boolean[] pancakes){
		for(int i=0;i<size;i++)
			pancakes[i+start]=!pancakes[i+start];
		return pancakes;
	}
	
	public static int indexOf(boolean[] pancake, boolean element){
		for(int i=0;i<pancake.length;i++){
			if(pancake[i]==element)
				return i;
		}
		return -1;
	}
	
	/*public static int containsthree(int size, boolean[] pancakes){
		int numconsec=0;
		int start=0;
		for(int i=0;i<=pancakes.length-size;i++){
			
		}
	}*/

}
