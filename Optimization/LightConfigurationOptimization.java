import java.util.*;
import java.math.*;
import org.apache.commons.math3.util.Combinations;

public class LightConfigurationOptimization {
	static final int M = 5, N = 5; //Source/Target arrays are M*N grids
	static final int k = 5; //Number of LEDs
	static final double Z = 5; //Distance between source and target planes
	static final int len = M*N; //Number of candidate locations
	
	static final double halfwidth = 12; //Manufacturer provided angular half-width
	static final double m = Math.log(0.5)/Math.log(Math.cos(halfwidth*Math.PI/180));
	static final double intensity = 1; //arbitrary units, can change later
	public static void main(String[] rip){
		long start = System.nanoTime();

		//Given two parallel M*N arrays (source and target), Z units apart, and 5 light fixtures, find optimal configuration
		//Objective function is sigma/E
		int[] optLEDarr = new int[k];
		
		double min = Double.MAX_VALUE; //current mimimum of objective function
		String[][] sourceArray = new String[M][N];
		
		for (Iterator<int[]> iter = new Combinations(M*N, k).iterator(); iter.hasNext();) {        
			LEDConfiguration config = cost(iter.next());
			if(config.CVRMSE < min){
				min = config.CVRMSE;
				optLEDarr = config.LEDarr;
			}
        }
		System.out.println(Arrays.toString(optLEDarr));
		for(String[] row:sourceArray) Arrays.fill(row, "⚫");
		
		for(int LED:optLEDarr){
			int[] LEDcoord = indexToPoints(LED);
			sourceArray[LEDcoord[0]][LEDcoord[1]] = "⚪";
		}
		
		System.out.println(min+"\n\n");
		
		for(String[] row:sourceArray) System.out.println(Arrays.toString(row));
		System.out.println("\nRuntime: "+((System.nanoTime()-start)/1000000)+" ms");
	}
	
	//Outputs cost function evaluated at specified LED configuration
	public static LEDConfiguration cost(int[] LEDarr){
		double netIrradiance = 0; //Sum of irradiance at all points
		double avgIrradiance = 0; //Average irradiance across grid
		double netErrorSquared = 0, sigma = 0;
		double CVRMSE = 0; //objective function, CVRMSE = sigma/avgIrradiance
		int len = M*N;
		
		//unique combination of LEDs exists
		for(int targetPoint = 0; targetPoint < len; targetPoint++){
			//given some point on target plane and set of LED positions, find irradiance
			int[] pointcoord = indexToPoints(targetPoint);
			for(int LED:LEDarr){
				netIrradiance += irradiance(LED, pointcoord);
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
		CVRMSE = sigma/avgIrradiance; //objective function
		
		LEDConfiguration config = new LEDConfiguration(CVRMSE, LEDarr);
		return config;
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
	
	static class LEDConfiguration{
		public double CVRMSE;
		public int[] LEDarr;
		public LEDConfiguration(double CVRMSE, int[] LEDarray){
			this.CVRMSE = CVRMSE;
			LEDarr = LEDarray;
		}
	}

}
