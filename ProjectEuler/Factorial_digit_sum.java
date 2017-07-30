import java.math.BigInteger;

public class Factorial_digit_sum {

	public static void main(String[] args) {
		System.out.println(sum_digits(factorial(100)));
	}
	
	public static BigInteger factorial(int num){
		BigInteger prod=BigInteger.valueOf(1);
		for(int i=num;i>1;i--){
			prod=prod.multiply(BigInteger.valueOf(i));
		}
		return prod;
	}
	public static double sum_digits(BigInteger n){
		String num=String.valueOf(n);
		int sum=0;
		for(int i=0;i<num.length();i++){
			sum+=Integer.parseInt(num.substring(i,i+1));
		}
		return sum;
	}

}
