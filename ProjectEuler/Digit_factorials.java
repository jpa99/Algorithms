
public class Digit_factorials {

	public static void main(String[] args) {
		int sum=0;
		for(int i=10; i>0;i++){
			if(sumDigits(i)){
				sum+=i;
				System.out.println(sum);
			}
		}
	}
	
	public static int factorial(int num){
		int prod=1;
		for(int i=num;i>1;i--){
			prod*=i;
		}
		return prod;
	}
	
	public static boolean sumDigits(int n){
		String num=String.valueOf(n);
		int fsum=0;
		for(int i=0;i<num.length();i++){
			fsum+=factorial(Integer.parseInt(num.substring(i, i+1)));
		}
		if(fsum==n){
			return true;
		}
		else{
			return false;
		}
	}

}
