import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface MATH_TOOLBOX {
	public int run(int a, int b);
}


 class factorial implements MATH_TOOLBOX{
    public int run(int n, int x){
		int prod=1;
		for(int i=n;i>1;i--){
			prod*=i;
		}
		return prod;
    }
 }
 
class sum_digits_square implements MATH_TOOLBOX{
	public int run(int i, int x){
		char[] digits=String.valueOf(i).toCharArray();
		int sum=0;
		for(char c:digits){
			sum+=Math.pow((double)(Character.getNumericValue(c)), 2);
		}
		return sum;
	}
}

class main{
	public static void main(String[] args){	
		List<HashMap<MATH_TOOLBOX, String>> functions=new ArrayList<HashMap<MATH_TOOLBOX, String>>();
		new HashMap<MATH_TOOLBOX, String>();
		functions.add(new HashMap<MATH_TOOLBOX, String>());
	}
}

