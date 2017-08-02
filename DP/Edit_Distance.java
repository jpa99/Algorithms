import java.util.Arrays;

public class Edit_Distance {
	
	/*Edit (Levenshtein Distance) Algorithm

	 Problem (informal): Given two strings, find minimum number of deletions, insertions, or replacements required to transform 
	 
	 Algorithm: If characters are same, continue, else, consider removal, deletion, and replacement and consider minimum recursively
	 
	 Complexity:
	 	* Time - O(n^2) 
	 	* Space - O(n^2) with memoization and O(1) without
	 	
	 Functions Defined:
	 	* editDistanceIterative() - Bottom up implementation, finds minimum iteratively
	 	* editDistanceRecursive() - Top down implementation, recursive
	 	
	 */

	static int[][] dp;
	public static void main(String[] args) {
		String a = "hey if you like this repository, you should give it a star!";
		String b = "I'd appreciate any feedback or requests -- just fork this repo and add to the README.md";
		
		dp = new int [a.length()+1][b.length()+1];
		
		for(int[] row :dp){
			Arrays.fill(row, -1);
		}
		
		System.out.println(editDistanceIterative(a, b));
		System.out.println(editDistanceTD(a, b));

	}
	
	public static int editDistanceIterative(String a, String b){
		int[][] dp = new int[a.length()+1][b.length()+1];
		for(int i=0; i <= a.length();i++){
			for(int j=0; j <= b.length();j++){
				if(i==0 || j==0)
					dp[i][j] = i+j;
				else if(a.charAt(i-1) == b.charAt(j-1))
					dp[i][j] = dp[i-1][j-1];
				else
					dp[i][j] = 1 + Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]);
			}
		}
		return dp[a.length()][b.length()];
	}
	
	//minimal # of insertions, substitutions, deletions required to change String a to b
	public static int editDistanceTD(String a, String b){
		int m = a.length();
		int n = b.length();
		if(dp[m][n] !=-1){ 
			return dp[m][n];
		}
		if(m==0 || n==0){
			dp[m][n] = m+n;
			return dp[m][n];
		}
		if(a.charAt(m-1) == b.charAt(n-1)){
			dp[m][n] = editDistanceTD(a.substring(0, m-1), b.substring(0, n-1));
			return dp[m][n];
		}
		dp[m][n] = 1 + Math.min(Math.min(editDistanceTD(a.substring(0, m-1), b), editDistanceTD(a, b.substring(0,  n-1))), editDistanceTD(a.substring(0, m-1), b.substring(0, n-1)));
		return dp[m][n];
	}

}
