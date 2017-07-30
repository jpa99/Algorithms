import java.math.BigInteger;

public class Concealed_square {

	public static void main(String[] args) {
		for(long i=1010101010;i<1389026624;i+=10){
			BigInteger num=BigInteger.valueOf(i);
			String n=String.valueOf(num.pow(2));
			if(n.length()=="1929394959697989990".length()){
				int index=0;
				char[] nums=n.toCharArray();
				for(int j=0;j<nums.length;j+=2){
					if(nums[j]=="1929394959697989990".toCharArray()[j]){
						index++;
					}
					if(index==10){
						System.out.println(i);
					}
				}
			}
		}
	}

}
