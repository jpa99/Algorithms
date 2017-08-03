import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Naive_String_Searching {
	
	/*Naive Algorithm

	 Problem (informal): Given strings text and pattern, determine wheter pattern occurs within text
	 
	 Algorithm: Intuitive search algorithm, iterates over every window of pattern length and compares strings
	 
	 Complexity:
	 	* Time - O(nm) (asymptotically tight if finding all occurances) Since all windows tested
	 	* Space - O(1) since no additional space used
	 	
	 Functions Defined:
	 	* naive() - Naive search algorithm returns true if pattern contained in text

	 Note:
	 	* All algorithms are much better than naive. Do not use in competition EVER -- this will be the bottleneck.
	 */
	
	public static void main(String[] args) throws Exception{
		long start = System.nanoTime();
		
		String txt = "awefdwefwfef";
		String pat = "fwef";
		
		boolean ans = naive(pat, txt);
		System.out.println(ans);
		System.out.println("\n"+((System.nanoTime()-start)*1E-6)+" ms");
		
	}
	
	public static boolean naive(String pat, String txt){
		boolean contains = false;
		for(int i=0;i<=txt.length() - pat.length();i++){
			contains = txt.substring(i, i+pat.length()).equals(pat);
			if(contains) return true;
		}
		return false;
	}
}
