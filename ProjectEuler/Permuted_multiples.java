import java.util.Date;

public class Permuted_multiples {

	public static void main(String[] args) {
		Date start = new Date();
		for(int i=0; i< 1000000;i++){
			if(same_digits(i,2*i) && 
				same_digits(2*i, 3*i) &&
				same_digits(3*i, 4*i) &&
				same_digits(4*i, 5*i) &&
				same_digits(5*i, 6*i)){
				System.out.println(i);
			}
		}
		Date end = new Date();
		System.out.println("Run time = "+ (start.getTime() - end.getTime())/1000.0+" seconds");
	}
	
	public static boolean same_digits(int a, int b){
		String num_a=String.valueOf(a);
		String num_b=String.valueOf(b);
		if(num_a.length()!=num_b.length()){
			return false;
		}
		for(int i=0;i<num_a.length();i++){
			if(!num_b.contains(num_a.substring(i, i+1))){
				return false;
			}
		}
		return true;
	}

}
