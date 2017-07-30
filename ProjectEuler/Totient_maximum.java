import java.lang.Math;
public class Totient_maximum {

	public static void main(String[] args) {
		double max=0;
		double max_index=1;;
		for(double i=100000;i>1;i--){
			double t=i/totient(i);
			if(t>max){
				max=t;
				max_index=i;
			}
		}
		System.out.println(max_index);
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

}
