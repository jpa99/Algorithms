import java.math.BigInteger;
import java.util.Random;

public class Boyer_Moore {

	/*Boyer-Moore

	 Problem (informal): Given strings text and pattern, determine wheter pattern occurs within text
	 
	 Algorithm: Instead of searching left to right, align window and search right to left; if mismatch, shift optimally based on precomputed table
	 
	 Complexity:
	 	* Time - O(n+m) if pattern absent, unless counting all occurances in which case O(nm) if string is repeated character
	 	* Space - O(k) where k is the number of distinct characters in the pattern string
	 	
	 Functions Defined:
	 	* boyer_moore() - Boyer-Moore algorithm

	 Note:
	 	* Although KMP is asymptotically faster, Boyer-Moore is sublinear in practice due to natural ordering of letters
		* Empirical analysis demonstrates SUBLINEARITY of Boyer-Moore!!!!!! {c r a z y !}
	 */
	
	public static void main(String[] args) { long start=System.nanoTime();
    
		//test strings/patterns
		String txt="aawefawefergergergegrttnbtbt";
		String pat = "ef";
		
	    System.out.println(boyer_moore(pat, txt) < txt.length());
	    
	    //benchmarking
	    System.out.println("\n"+(System.nanoTime()-start)*1e-6+"  ms");
	}

	//Boyer-Moore algorithm
    public static int boyer_moore(String pat, String txt) {
    	 int[] right = new int[256];
         for (int c = 0; c < right.length; c++)
             right[c] = -1;
         for (int j = 0; j < pat.length(); j++)
             right[pat.charAt(j)] = j;
         
        int m = pat.length();
        int n = txt.length();
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i+j)) {
                    skip = Math.max(1, j - right[txt.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return n;                       // not found
    }


    
    
}

