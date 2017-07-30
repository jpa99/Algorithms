import javax.swing.plaf.synth.SynthSeparatorUI;

public class Prime_Summation {

	public static void main(String[] args) {
		double sum=0;
		double n=2000000;
		for(double i=n-1;i>=2;i--){
			if(isPrime(i)){
				sum+=i;
			}
		}
		System.out.println(sum+ "end");

	}

	public static boolean isPrime(double n){
		for(double i=2;i<=(int)(Math.sqrt(n));i++){
			if(n%i==0){
				return false;
			}
		}
		return true;
	}
}
