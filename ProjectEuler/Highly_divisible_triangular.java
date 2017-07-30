import java.util.ArrayList;

public class Highly_divisible_triangular {

	public static void main(String[] args) {
		int index=1;
		int max_divisors=0;
		int i=1;
		while(max_divisors<=500){
			max_divisors=Math.max(max_divisors, num_divisors(i));
			index++;
			i+=index;
		}
		System.out.println("The "+index+"th triangle number, "+i+", has "+max_divisors+" divisors");
		
	}
	
	public static int num_divisors(int n){
		int num=0;
		for(int i=2;i<(int)(n/2)+1;i++){
			if(n%i==0){
				num++;
			}
		}
		return num+2;
	}

}
