import java.math.BigInteger;
import java.util.ArrayList;

public class Thousand_digit_fibonacci {

	public static void main(String[] args) {
		ArrayList<BigInteger> fib=new ArrayList<BigInteger>();
		fib.add(BigInteger.valueOf(1));
		fib.add(BigInteger.valueOf(1));
		int i=0;
		for(i=2;String.valueOf(fib.get(fib.size()-1)).length()<1000;i++)
			fib.add(fib.get(i-1).add(fib.get(i-2)));
		System.out.println(i);
	}

}
