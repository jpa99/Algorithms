import java.util.*;
public class primes{
	public static void main(String[] args){
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter an integer");
		int num=scan.nextInt();
		if(isPrime(num)){
			System.out.println(num);
		}
		else{
			System.out.println(factor(num));
		}
	}
	
	public static List<Integer> factor(int n){
		List<Integer> factors=new ArrayList<Integer>();
		for(int i=n-1;i>=2;i--){
			if(n%i==0 && isPrime(i)) {
				n/=i;
				factors.add(i);
				i++;
			}
		}
		return factors;
	}
	public static boolean isPrime(int n){
		for(int i=2;i<n;i++){
			if(n%i==0){
				return false;
			}
		}
		return true;
	}
}
