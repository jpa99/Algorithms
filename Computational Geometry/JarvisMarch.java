import java.util.Arrays;
import java.util.Stack;

public class JarvisMarch {
	
	/*Jarvis March

	 Problem (informal): Find convex hull surrounding a set of points
	 
	 Algorithm: Find lowest point and highest points, simulate wrapping string tightly around points
	 
	 Complexity:
	 	* Time - O(nh) where n is number of points and h is number of points on convex hull
	 	* Space - O(n) to store set of points
	 	
	 Functions Defined:
	 	* jarvis() - Jarvis march algorithm
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
		Stack<int[]> s = jarvis(points);
		for(int[] x : s){
			System.out.println(Arrays.toString(x));
		}
	}
	
	//Jarvis March algorithm
	public static Stack<int[]> jarvis(int[][] points){
		Stack<int[]> stack = new Stack<int[]>();
		
		//find lowest & highest points -- guaranteed to be included in convex hull
		int minindex = 0, maxindex = 0;
		for(int i=0;i<points.length;i++){
			if(points[i][1] < points[minindex][1])
				minindex = i;
			if(points[i][1] > points[maxindex][1])
				maxindex = i;
		}
		
		int chain = 1;
		stack.push(points[minindex]);
		int lastindex = minindex;
		while(lastindex != minindex || stack.size() == 1){
			int[] parmin = {points[lastindex][0] + chain, points[lastindex][1]};
			double minangle = 361;
			int nextindex = 0;
			
			for(int i=0; i < points.length; i++){
				if(i == lastindex) continue;
				double angle = angle(points[i], points[lastindex], parmin);
				if(angle < minangle){
					minangle = angle;
					nextindex = i;
				}
			}
			stack.push(points[nextindex]);
			lastindex = nextindex;
			if(lastindex == maxindex){
				chain = -1;
			}
		}
		stack.pop();
		return stack;
		
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