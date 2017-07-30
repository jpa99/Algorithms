
public class Totient_permutations {

	public static void main(String[] args) {
		double min=2;
		double index=0;
		for(double i=2;i<1000000;i++){
			if(same_digits(i, totient(i)) && ((i/totient(i)) < min)){
				min=i/totient(i);
				index=i;
			}
		}
		System.out.println(index);

	}
	
	
	public static double gcd(double a, double b){
		return (b==0) ? a:gcd(b, a%b);
	}
	
	public static double totient(double n){
		double num=0;
		for(double i=n;i>0;i--){
			if(gcd(n, i)==1){
				num++;
			}
		}
		return num;
	}
	
	public static boolean same_digits(double a, double b){
		String num_a=String.valueOf(a);
		String num_b=String.valueOf(b);
		if(num_a.length()!=num_b.length()){
			return false;
		}
		for(int i=0;i<num_a.length();i++){
			if(!num_b.contains(num_a.substring(i, i+1))){
				return false;
			}
		}
		return true;
	}

}
