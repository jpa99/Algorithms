import java.math.BigInteger;
import java.util.ArrayList;

public class Distinct_powers {

	public static void main(String[] args) {
		ArrayList<BigInteger> power=new ArrayList<BigInteger>();
		for(int i=2;i<=100;i++){
			for(int j=2;j<=100;j++){
				BigInteger num=BigInteger.valueOf(i).pow(j);
				if(!power.contains(num)){
					power.add(num);
					System.out.println(i+" "+j+" "+num);
				}
			}
		}
		System.out.println(power.size());

	}

}
