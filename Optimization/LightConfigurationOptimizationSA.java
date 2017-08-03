import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class LightConfigurationOptimizationSA {
	static final int M = 5, N = 5; //Source/Target arrays are M*N grids
	static final int k = 5; //Number of LEDs
	static final double Z = 5; //Distance between source and target planes
	static final int len = M*N;
	
	static final double halfwidth = 12; //Manufacturer provided angular half-width
	static final double m = Math.log(0.5)/Math.log(Math.cos(halfwidth*Math.PI/180));
	static final double intensity = 1; //arbitrary units, can change later
	
	//Simulated Annealing parameters
	static final double Tmin = .0001; //Temperature at which iteration terminates
	static final double alpha = 0.9; //Decrease in temperature

	public static void main(String[] args) {
		double T = 1; //Initial temperature
		String[][] sourceArray = new String[M][N];
		Solution min = new Solution(Double.MAX_VALUE, null);
		Solution currentSol = genRandSol();
		while(T > Tmin){
			for(int i=0;i<100;i++){
				if(currentSol.CVRMSE < min.CVRMSE){
					min = currentSol;
				}
				Solution newSol = neighbor(currentSol);
				double ap = Math.pow(Math.E, (currentSol.CVRMSE - newSol.CVRMSE)/T);
				if(ap > Math.random()){
					currentSol = newSol;
				}
			}
			T*=alpha;
		}
		
		System.out.println(min.CVRMSE+"\n\n");
		
		for(String[] row:sourceArray) Arrays.fill(row, "⚫");
		
		for(int LED:min.config){
			int[] LEDcoord = indexToPoints(LED);
			sourceArray[LEDcoord[0]][LEDcoord[1]] = "⚪";
		}
		
		for(String[] row:sourceArray) System.out.println(Arrays.toString(row));

	}
	
	//Given current configuration, returns "neighboring" configuration (i.e. very similar)
	//integer of k points each in range [0, n)
	/*Different neighbor selection strategies?
	 	* Move all points 0 or 1 units in a random direction
	 	* 
	*/
	public static Solution neighbor(Solution currentSol){
		int[] config = currentSol.config; //+- 1 for left/right, +-M for up/down
		int[] directions = {-1, 1, M, -M};
		for(int i=0;i<config.length;i++){
			int newIndex = config[i]+directions[(int) (Math.random()*4)];
			if(newIndex >= 0 && newIndex < len) config[i] = newIndex;
		}
		return new Solution(cost(config), config);
	}
	
	//Generates random solution via modified Fisher-Yates shuffle for first k elements
	//Pseudorandomly selects k integers from the interval [0, n-1]
	public static Solution genRandSol(){
		Random rng = new Random();
		HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>(2*k);
	    int[] configuration = new int[k];

	    for (int i = 0; i < k; i++) {
	        int j = i + rng.nextInt(len - i);
	        configuration[i] = (hash.containsKey(j) ? hash.remove(j) : j);
	        if (j > i) hash.put(j, (hash.containsKey(i) ? hash.remove(i) : i));
	    }
	    
	    double cost = cost(configuration);
	    return new Solution(cost, configuration);
	}
	
	//Returns irradiance contributed by specific LED at a specific point
	public static double irradiance(int LED, int[] pointcoord){
		int[] LEDcoord = indexToPoints(LED);
		return (Math.pow(Z, m+1)*intensity)/
Math.pow((Math.pow(pointcoord[0] - LEDcoord[0], 2) + Math.pow(pointcoord[1] - LEDcoord[1], 2) + Math.pow(Z,  2)), ((m+3)/2));	
	}
	
	//Mapping from [0, M*N] --> [0,M]x[0,N]
	public static int[] indexToPoints(int index){
		int[] points = {index%M, index/M};
		return points;
	}
	
	//Cost function, evaluates CVRMSE of input LED configuration
	//Complexity is O(M*N*k), asymptotically tight
	public static double cost(int[] LEDarr){
		double netIrradiance = 0; //Sum of irradiance at all points
		double avgIrradiance = 0; //Average irradiance across grid
		double netErrorSquared = 0, sigma = 0;
		
		//unique combination of LEDs exists
		for(int targetPoint = 0; targetPoint < len; targetPoint++){
			//given some point on target plane and set of LED positions, find irradiance
			int[] pointcoord = indexToPoints(targetPoint);
			for(int LED:LEDarr){
				netIrradiance+=irradiance(LED, pointcoord);
			}
		}
		
		avgIrradiance = netIrradiance/len;
		
		for(int targetPoint = 0; targetPoint < len; targetPoint++){
			//given some point on target plane and set of LED positions, find irradiance
			int[] pointcoord = indexToPoints(targetPoint);
			double pointIrradiance = 0; //irradiance at point from all LEDs
			for(int LED:LEDarr){
				pointIrradiance+=irradiance(LED, pointcoord);
			}
			netErrorSquared+=Math.pow(pointIrradiance - avgIrradiance, 2);
		}
		
		sigma = Math.sqrt(netErrorSquared/len);
		return sigma/avgIrradiance; //objective function
	}
	
	static class Solution{
		public double CVRMSE;
		public int[] config;
		public Solution(double CVRMSE, int[] configuration){
			this.CVRMSE = CVRMSE;
			config = configuration;
		}
	}

}
