import java.util.*;

public class Prime_factorization {

	public static void main(String[] args) {
		System.out.println(factor(646));
		
	}

	
	public static ArrayList<Double> factor(double n){
		ArrayList<Double> factors=new ArrayList<Double>();
		for(double i=n-1;i>=2;i--){
			if(n%i==0 && isPrime(i)) {
				n/=i;
				factors.add(i);
				i++;
			}
		}
		return factors;
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
