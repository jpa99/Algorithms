import java.util.ArrayList;

public class Even_fibonacci_sum {

	public static void main(String[] args) {
		ArrayList<Integer> fib=new ArrayList<Integer>();
		fib.add(1);
		fib.add(2);
		int sum=2;
		while(fib.get(fib.size()-1) <= 4000000){
			int i=2;
			fib.add(fib.get(i-1)+fib.get(i-2));
			if(fib.get(i)%2==0){
				sum+=fib.get(i);
			}
			i++;
		}
		System.out.println(Math.pow(2, 1000));
		System.out.println(sum);
	}

}
