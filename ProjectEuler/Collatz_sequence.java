
public class Collatz_sequence {
	public static void main(String[] args){
		double starting=0;
		double max=0;
		for(double i=2;i<1000000;i++){
			if(collatz(i)>max){
				max=collatz(i);
				starting=i;
				System.out.println(starting);
			}
		}
		
		
	}
	
	public static double collatz(double n){
		double sum=0;
		while(n!=1){
			if(n%2==0){
				n/=2;
			}
			else{
				n=3*n+1;
			}
			sum++;
		}
		return sum;
	}
}
