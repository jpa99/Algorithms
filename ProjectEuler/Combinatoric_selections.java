import java.math.BigInteger;

public class Combinatoric_selections {

	public static void main(String[] args) {
		
		int sum=0;
		
		for(int n=23; n<=100;n++){
			for(int r=4;r<n-3;r++){
				int choose = (factorial(n))/(factorial(r)*(factorial(n-r)));
				if(choose>1000000){
					sum++;
				}
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
	
	

}
