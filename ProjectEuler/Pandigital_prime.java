import java.util.ArrayList;
import java.util.Date;

public class Pandigital_prime {

	public static void main(String[] args) {
		Date start = new Date();
		ArrayList<Integer> pans=new ArrayList<Integer>();
		for(int i=7654321;i>2143;i-=2){
			if(pandigital(i) && isPrime(i)){
				pans.add(i);
			}
		}
		System.out.println(pans.get(0));
		Date end = new Date();
		System.out.println("Run time = "+ (start.getTime() - end.getTime())/1000.0+" seconds");
	}
	
	public static boolean pandigital(int n){
		String num=String.valueOf(n);
		for(int i=num.length(); i>=1; i--){
			if(!num.contains(Integer.toString(i))){
				return false;
			}
		}
		return true;	
	}
	
	public static boolean isPrime(int n){
		for(int i= (int)(Math.sqrt(n)+1);i>1;i--){
			if(n%i==0){
				return false;
			}
		}
		return true;
	}

}
