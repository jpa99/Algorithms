import java.math.BigInteger;
import java.util.Random;


public class Rabin_Karp {
	
	/*Rabin-Karp

	 Problem (informal): Given strings text and pattern, determine wheter pattern occurs within text
	 
	 Algorithm: Uses rolling hash function to shift window of length k across pattern and compare pattern substring hash with text hash
	 
	 Complexity:
	 	* Time - O(nk) worst case (if check at every position) but O(n+k) best/average case
	 	
	 Functions Defined:
	 	* hash() - generates unique hash for given String key
	 	* check() - checks for match at offset i, validates hash for match
	 	* rabin_karp() - main algorithm with rolling hash
	 	
	 Note:
	 	* KMP and Boyer-Moore are faster for single string searching, but Rabin-Karp is better for multiple string searching
		
	 */
    public static void main(String[] args) { long start=System.nanoTime();
		String txt="qwertyuiopasdfghjklzxcvbnm";
		String pat = "bn";
	
	    System.out.println(rabin_karp(pat, txt) < txt.length());
	    System.out.println("\n"+(System.nanoTime()-start)*1e-6+"  ms");
    }

    private static long hash(String key, int m, long q) { 
        long h = 0; 
        for (int j = 0; j < m; j++) 
            h = (256 * h + key.charAt(j)) % q;
        return h;
    }

    private static boolean check(String pat, String txt, int i, int m) {
        for (int j = 0; j < m; j++) 
            if (pat.charAt(j) != txt.charAt(i + j)) 
                return false; 
        return true;
    }

    public static int rabin_karp(String pat, String txt) {
    	int m = pat.length();
        long q = BigInteger.probablePrime(31, new Random()).longValue();

         // precompute R^(m-1) % q for use in removing leading digit
        long RM = 1;
         for (int i = 1; i <= m-1; i++)
             RM = (256 * RM) % q;
         long patHash = hash(pat, m, q);
         
        int n = txt.length(); 
        if (n < m) return n;
        long txtHash = hash(txt, m, q); 

        // check for match at offset 0
        if ((patHash == txtHash) && check(pat, txt, 0, m))
            return 0;

        // check for hash match; if hash match, check for exact match
        for (int i = m; i < n; i++) {
            // Remove leading digit, add trailing digit, check for match. 
            txtHash = (txtHash + q - RM*txt.charAt(i-m) % q) % q; 
            txtHash = (txtHash*256 + txt.charAt(i)) % q; 

            // match
            int offset = i - m + 1;
            if ((patHash == txtHash) && check(pat, txt, offset, m))
                return offset;
        }

        // no match
        return n;
    }
}
