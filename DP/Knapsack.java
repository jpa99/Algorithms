import cern.colt.Arrays;
import java.util.*;

public class Knapsack {
	
	/*0-1 Knapsack Probelm

	 Problem (informal): Given set of items with cost and weight, find subset of items such that net weight does not exceed W and set has maximum total cost
	 
	 Algorithm: Recursive -- try each possible item and calculate max of remaining
	 
	 Complexity:
	 	* Time - O(nW) where W is the maximum weight possible
	 	* Space - O(n) only one array for number of items.
	 	
	 Functions Defined:
	 	* knapsackTD() - Recursively consideres one less item and maximum of remaining subset
	 	* fractionalknapsack() - Pretty trivial -- greedy algorithm suffices. Solved in O(nlogn) but also possible in O(n) via weighted medians 
	 	
	 */
	static int[][] items;
	static int maxweight;
	public static void main(String[] args){
		int[][] itemsx = {{60, 10}, {100, 20}, {120, 30}};
		//{cost, weight}
		items = itemsx;
		maxweight = 50;
		boolean[] insert = new boolean[items.length];
		//System.out.println(knapsackBU(items, maxweight));
		System.out.println(knapsackTD(maxweight, insert));
	}
	
	public static int knapsackTD(int maxweight, boolean[] insert){
		int max = 0;
		for(int i=0;i<items.length;i++){
			if(!insert[i] && maxweight >= items[i][1]){
				boolean[] insertclone = insert.clone();
				insertclone[i] = true;
				max = Math.max(items[i][0] + knapsackTD(maxweight - items[i][1], insertclone), max);
			}
		}
		return max;
	}
	
	public static void fractionalknapsack(){
		//greedy
		//I don't think it's necessary to implement this since it's pretty straightforward.
		//Just find the item with largest cost and take as much of it as possible. If you take all of it, continue to next largest.
	}
	
}
