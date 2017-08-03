import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class String_Algorithms {

	/*
	 
	 Comparison of performance of String Algorithms on randomly scraped String data
	 	* Demonstrates Boyer-Moore is significantly better (faster) than KMP which is slightly better (faster) than Rabin-Karp
	 	* Boyer-Moore performs better in practice, approximately 150x faster than KMP and ~200x faster than Rabin-Karp
	 	
	*/
	
	public static void main(String[] args) throws FileNotFoundException { 
    	double kmptime=0, bmtime=0, rktime=0;
    	Scanner scan=new Scanner(new File("Strings_random.in"));
		String txt="", pat="";
		int rk = 0;
		int bm = 0;
		int kmp = 0;
		while(scan.hasNextLine()){
			pat=scan.nextLine();
			txt=scan.nextLine().toLowerCase();
			
			long start=System.nanoTime();
		    bm =boyer_moore(pat, txt);
		    bmtime += (System.nanoTime()-start)*1e-6;
		    
		    start=System.nanoTime();
		    kmp = kmp(pat, txt);
		    kmptime += (System.nanoTime()-start)*1e-6;
		    
		    start=System.nanoTime();
		    rk = rabin_karp(pat, txt);
		    rktime += (System.nanoTime()-start)*1e-6;
		}
		
		//int length = String.valueOf((int)kmptime).length()+6;
	
	    System.out.printf("KMP: %.2f ms: %d\n", kmptime, kmp);
	    System.out.printf("Boyer-Moore: %.2f ms: %d\n", bmtime, bm);
	    System.out.printf("Rabin-Karp: %.2f ms: %d\n", rktime, rk);
    }
	
    public static int boyer_moore(String pat, String txt) {
    	 int[] right = new int[256];
    	 Arrays.fill(right, -1);
         for (int j = 0; j < pat.length(); j++)
             right[pat.charAt(j)] = j;
         //maps char to last index in patten
        int m = pat.length();
        int n = txt.length();
        int skip;
        
        //i=0 to i=
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

    public static int kmp(String pat, String txt) {
    	int m = pat.length();
        int[][] dfa = new int[256][m]; 
        dfa[pat.charAt(0)][0] = 1; 
        for (int x = 0, j = 1; j < m; j++) {
            for (int c = 0; c < dfa.length; c++) 
                dfa[c][j] = dfa[c][x];     
            dfa[pat.charAt(j)][j] = j+1;  
            x = dfa[pat.charAt(j)][x];     
        } 
        // simulate operation of DFA on text
        int n = txt.length();
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++)
            j = dfa[txt.charAt(i)][j];

        if (j == m)
        	return i - m;    // found
        return n;                    // not found
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

