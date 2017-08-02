import java.util.Arrays;
import java.util.Stack;

public class GrahamScan {
	
	/*Graham's Scan

	 Problem (informal): Find convex hull surrounding a set of points
	 
	 Algorithm: Find lowest point and sort remaining points by polar angle, push onto stack if left turn
	 
	 Complexity:
	 	* Time - O(nlogn) due to sorting; actual online algorithm is linear
	 	* Space - O(n) to store set of points
	 	
	 Functions Defined:
	 	* graham() - Graham's scan algorithm
	 	* polarbubblesort() - Sorts points in order of increasing polar angle from minimum
	 	* angle() - Computes angle between two lines given by three points
	 	* dot() - computes dot product between two vectors to easily find angle
	 	* norm() - computes magnitude of a vector
	 	* cross() - finds cross product between two vectors to determine angle concavity
	 	* d() - distance function, computes distance between two poitns

	 */

	public static void main(String[] args) {
		//test set of points
		int[][] points = {{0, -2}, {2, 0}, {2, 20}, {1, 1}, {1, 2}, {-2, 0}, {-1, -1}};

		
		//Graham's Scan
		Stack<int[]> s = graham(points);
		for(int[] x : s){
			System.out.println(Arrays.toString(x));
		}
	}
	
	//Currently O(n^2) because of inefficient polar sort; use O(nlogn) sort to improve
	public static Stack<int[]> graham(int[][] points){
		Stack<int[]> stack = new Stack<int[]>();
		
		//find lowest point -- guaranteed to be included in 
		int minindex = 0;
		for(int i=0;i<points.length;i++){
			if(points[i][1] < points[minindex][1])
				minindex = i;
		}
		
		//swap lowest point with first point
		int[] min = points[minindex];
		points[minindex] = points[0];
		points[0] = min;
		
		//sort points clockwise aroung p0 by polar angle
		int[] parmin = {points[minindex][0] - 1, points[minindex][1]};
		points = polarbubblesort(points, min, parmin);
		
		stack.push(points[0]);
		stack.push(points[1]);
		stack.push(points[2]);

		//algorithm -- push if concave
		
		for(int i = 3; i < points.length; i++){
			while(angle(stack.get(1), stack.peek(), points[i]) >= 180){
				stack.pop();
			}
			stack.push(points[i]);
		}
		return stack;
	}
	
	//O(n^2) TIME, O(1) SPACE, STABLE
	//Improve to O(nlogn)
	public static int[][] polarbubblesort(int[][] arr, int[] min, int[] parmin){
		for(int i=0;i<arr.length;i++){
			for(int j=0; j<arr.length-1;j++){
				if(angle(parmin, min, arr[j]) < angle(parmin, min, arr[j+1])){
					int[] temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
				if(angle(parmin, min, arr[j]) == angle(parmin, min, arr[j+1]) && d(arr[j+1], min) < d(arr[j], min)){
					int[] temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		return arr;
	}
	
	public static double angle(int[] a, int[] b, int[] c){

		int[] v1 = {a[0] - b[0], a[1] - b[1]};
		int[] v2 = {c[0] - b[0], c[1] - b[1]};
		
		double val = Math.acos(dot(v1, v2)/(norm(v1)*norm(v2)))*180/Math.PI;
		
		if(cross(v1, v2) > 0)
			val = 360 - val;
		
		return val;
	}
	
	public static double dot(int[] v1, int[] v2){
		return v1[0]*v2[0] + v1[1]*v2[1];
	}
	
	public static double norm(int[] v){
		return Math.sqrt(v[0]*v[0] + v[1]*v[1]);
	}
	
	public static int cross(int[] v1, int[] v2){
		return v1[0]*v2[1] - v2[0]*v1[1];
	}
	
	public static double d(int[] a, int[] b){
		return Math.sqrt(Math.pow(b[0] - a[0], 2) + Math.pow(b[1] - a[1], 2));
	}

}