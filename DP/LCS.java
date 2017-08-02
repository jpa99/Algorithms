import java.util.*;
import cern.colt.Arrays;

public class LCS {
	
	/*Longest Common Subsequence (LCS)

	 Problem (informal): Given two arrays, find largest subsequence of both arrays
	 
	 Algorithm: If elements at position i are equal, find LCS of subarrays [0, i-1], else find max of both possible cases 
	 
	 Complexity:
	 	* Time - O(n^2) 
	 	* Space - O(n^2) with memoization and O(1) without
	 	
	 Functions Defined:
	 	* iterativeLCS() - Bottom up implementation, finds longest length and reconstructs sequence
	 	* recursiveLCS() - Top down implementation, only outputs length of sequence
	 	
	 */
	
	static int[] arr1;
	static int[] arr2;
	static int[][] dp;
	
	static Stack<Integer> solution = new Stack<Integer>();
	public static void main(String[] args) {
		int[] arrA = {1, 2, 3, 4, 7, 5, 9};
		int[] arrB = {1, 4, 3, 7, 9, 5};
		
		arr1 = arrA;
		arr2 = arrB;
		
		System.out.println(Arrays.toString(iterativeLCS(arrA, arrB)));

	}
	
	public static int[] iterativeLCS(int[] a, int[] b){
		int[][] dp = new int[a.length+1][b.length+1];
		for(int i = 1; i <= a.length; i++){
			for(int j = 1; j <= b.length; j++){
				if(a[i-1] == b[j-1]){
					dp[i][j] =  1 + dp[i-1][j-1];
				}
				else{
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);	
				}
			}
		}
		
		int[] lcs = new int[dp[a.length][b.length]];
		int index = lcs.length-1;
		int i=a.length; int j = b.length;
		
		while(i > 0 && j > 0){
			if(a[i-1] == b[j-1]){
				lcs[index] = a[i-1];
				i--; j--; index--;
			}
			else if(dp[i-1][j] >= dp[i][j-1])
				i--;	
			else
				j--;		
		}
		
		return lcs;
	}
	
	public static int recursiveLCS(int a, int b){
		if(a < 0 || b < 0) return 0;
		
		if(arr1[a] == arr2[b]){
			solution.push(arr1[a]);
			dp[a][b] = 1 + recursiveLCS(a-1, b-1);
			return dp[a][b];
		}
		int opt1 = recursiveLCS(a, b-1);
		int opt2 = recursiveLCS(a-1, b);
		
		if(opt1 >= opt2){
			dp[a][b] = opt1;
		}
		
		else{
			dp[a][b] = opt2;
		}
		
		return dp[a][b];
	}

}
