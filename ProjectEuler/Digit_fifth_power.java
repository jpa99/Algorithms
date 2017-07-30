
public class Digit_fifth_power {

	public static void main(String[] args) {
		int sum=0;
		for(int i=0;i<1000000000;i++){
			if(fifth_power(i)){
				sum+=i;
				System.out.println(i);
			}
		}
		
	}
	
	public static int[] digits(int n){
		char[] num=String.valueOf(n).toCharArray();
		int[] nums=new int[num.length];
		for(int i=0;i<nums.length;i++){
			nums[i]=Character.getNumericValue(num[i]);
		}
		return nums;
	}
	
	public static boolean fifth_power(int n){
		int sum=0;
		for(int i:digits(n)){
			sum+=(int)Math.pow(i,  2);
		}
		return sum==n && String.valueOf(n).length()>1 ? true:false;
	}

}
