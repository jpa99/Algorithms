
public class Nontrivial_digit_cancelling_fractions {

	public static void main(String[] args) {
		

	}
	
	public static boolean cancelling(int n, int d){
		String num=String.valueOf(n);
		String den=String.valueOf(d);
		if(num.substring(0,1)== den.substring(0,1) ||
			num.substring(1)== den.substring(0,1) ||
			num.substring(0,1)== den.substring(1) ||
			num.substring(1)== den.substring(1)){
		}
		return true;
	}

}
