import java.util.ArrayList;
import java.lang.Math;

public class Consecutive_distinct_prime_factors {

	public static void main(String[] args) {
		for(double i=0;i<100000000;i++){
			if(!isPrime(i) && factor(i).size()==4 &&
				factor(i+1).size()==4 &&
				factor(i+2).size()==4 &&
				factor(i+3).size()==4
					){
				System.out.println(i);
				
			}
		}
	}

	public static ArrayList<Double> factor(double n){
		ArrayList<Double> factors=new ArrayList<Double>();
		for(double i= n-1;i>=2;i--){
			if(n%i==0 && isPrime(i) && !inlist(i, factors)) {
				n/=i;
				factors.add(i);
				i++;
			}
		}
		return factors;
	}
	
	public static boolean inlist(double n, ArrayList<Double> arr){
		for(double i: arr){
			if(n==i){
				return true;
			}
		}
		return false;
	}
	
	public static double return_max(ArrayList<Double> factors){
		double max=factors.get(0);
		for(double i:factors){
			if(i>max){
				max=i;
			}
		}
		return max;
	}
	
	public static boolean isPrime(double n){
		for(double i=2;i<n;i++){
			if(n%i==0){
				return false;
			}
		}
		return true;
	}
}
