
public class maxDonations {
	
	/*Maximum Donations (Topcoder)

	 Problem (informal): Given set S of values, find subset Q of values with maximum sum such that no two values in Q are adjacent in S
	 
	 Algorithm: Recursive -- two possibilities at each branch, test both
	 
	 Complexity:
	 	* Time - O(n^2) - each branches off into two possibilities
	 	* Space - O(n^2) due to DP array 
	 	
	 Functions Defined:
	 	* maxDonations() - main algorithm
	 */
	
	static int[] arr;
	static int len;
	static int[][] dp;

	public static void main(String[] args) {
		int[] arr2 = {10, 3, 2, 5, 7, 8};
		//{10, 4, 5}
		arr = arr2;
		len = arr.length;
		dp = new int[len][len];
		
		
		

	}
	
	public static int maxDonations(int start, int end){
		if(end - start ==2) return Math.max(Math.max(arr[start], arr[end]), arr[start+1]);
		if(end - start <=1) return Math.max(arr[start], arr[end]);

		int start1 = (start+1)%len;
		
		int val = arr[start];
		int start2 = start, end2 = end;
		if(start == 0){
			start2+=2;
			
		}
		else{
			start2+=2;
		}
		dp[start][end] = Math.max(maxDonations(start1, end), val + maxDonations(start2, end2));
		return dp[start][end];
	}

}
