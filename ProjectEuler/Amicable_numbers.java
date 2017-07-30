
public class Amicable_numbers {

	public static void main(String[] args) {
		int sum=0;
		for(int i=0;i<10000;i++){
			if(amicable(i, sum_divisors(i)) && i!=sum_divisors(i)){
				sum+=i;
			}
		}
		System.out.println(sum);
	}
	
	
	public static boolean amicable(int a, int b){
		return sum_divisors(a)==b && sum_divisors(b)==a ? true:false;
	}
	
	public static int sum_divisors(int n){
		int sum=0;
		for(int i=2;i<(int)(n/2)+1;i++){
			if(n%i==0){
				sum+=i;
			}
		}
		return sum+1;
	}

}
