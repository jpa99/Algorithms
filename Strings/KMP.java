import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KMP {
	
	/*Knuth-Morris-Pratt

	 Problem (informal): Given strings text and pattern, determine wheter pattern occurs within text
	 
	 Algorithm: Naive algorithm with optimal shifting (length of longest proper prefix that is also a proper suffix)
	 
	 Complexity:
	 	* Time - O(n+k) since preprocessing is O(k) and online portion is O(n) where n is pattern length and k is text length
	 	* Space - O(n+k) since both pattern and text must be stored
	 	
	 Functions Defined:
	 	* kmpIterative() - Standard KMP algorithm, maintains txt window and shifts according to prefix table
	 	* kmpDFA() - Implementation of KMP via deterministic finite automaton, easier to implement but suboptimal in runtime
	 	* longest_prefix_suffix() - returns length of longest proper prefix of a string that is also a proper suffix

	 Note:
	 	* Although KMP is asymptotically faster, Boyer-Moore is sublinear in practice due to natural ordering of letters
		* Iterative implementation is ~10x faster than DFA
		
	 */
	
	public static void main(String[] args) throws FileNotFoundException { long start=System.nanoTime();
		String txt="abfwjiefoiwjef";
		String pat = "ieoi";
		
	    System.out.println(kmpDFA(pat, txt) < txt.length());
	    System.out.println(+(System.nanoTime()-start)*1e-6+"  ms\n\n");
	    
	    //Scanner scan=new Scanner(new File("input.txt"));
		long kmpTime=System.nanoTime();
		boolean ans = kmpIterative(pat, txt);
		System.out.println(ans);
		System.out.println((System.nanoTime()-kmpTime)*1e-6+"  ms\n\n");
	}

	//Iterative implementation
	public static boolean kmpIterative(String pattern, String text){
		//Preprocess via Partial Matching Table
		int size = pattern.length();
		int[] π=new int[size];
		for(int j=0;j<size;j++){
			π[j]=longest_prefix_suffix(pattern.substring(0, j+1));

		}
		//abxabweqweqwrqowri
		//abeab
		//KMP Algorithm
		boolean contains=false;
		int j=0, i=0;
		while(i<text.length()){
            if (pattern.charAt(j) == text.charAt(i)){
                j++;
                i++;
            }
            if (j == size){
                contains=true;
                j = π[j-1];
            }
            else if (i < text.length() && pattern.charAt(j) != text.charAt(i)){
                if (j != 0)
                    j = π[j-1];
                else
                    i = i+1;
            }
		}
		return contains;
	}

	//DFA implementation
    public static int kmpDFA(String pat, String txt) {

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

    //Helper function for iterative implementation
	public static int longest_prefix_suffix(String str){
		for(int i=str.length()-2;i>=0;i--){
			if(str.substring(0, i).equals(str.substring(str.length()-i))){
				return i;
			}
		}
		return 0;
	}


    
}
