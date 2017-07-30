import java.util.ArrayList;

public class Square_digit_chains {

	public static void main(String[] args) {
		ArrayList<Integer> one=new ArrayList<Integer>();
		one.add(1);
		ArrayList<Integer> eightynine =new ArrayList<Integer>();
		eightynine.add(89);
		for(int i=2;i<1000;i++){
			int n=i;
			while(!(one.contains(n) || eightynine.contains(n))){
				n=sum_digits_square(n);
			}
			if(one.contains(n)){
				one.add(i);
			}
			if(eightynine.contains(n)){
				eightynine.add(i);
			}
		}
		System.out.println(eightynine.size());
	}
	
	public static int sum_digits_square(int n){
		char[] digits=String.valueOf(n).toCharArray();
		int sum=0;
		for(char c:digits){
			sum+=Math.pow((double)(Character.getNumericValue(c)), 2);
		}
		return sum;
	}
	
	

}
