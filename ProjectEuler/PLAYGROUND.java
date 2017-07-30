import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;

@SuppressWarnings("unused")
class PLAYGROUND{
	
	static int[] order_initial;
	static int[] order_final;
	
	@SuppressWarnings("resource")
	public static void main (String[] args) throws IOException
	{
		System.out.println("\n1. Add a task\n"+"2. Display the next task to be completed\n"+"3. Mark task as completed\n"+"4. Display last task completed\n"+"5. Return last task completed to the task to do list\n"+"6. Display the employee information\n"+"7. Exit");
		
	}
	
	public static ArrayList<String> removeDuplicates(ArrayList<String> list) {
		ArrayList<String> result = new ArrayList<>();
		HashSet<String> set = new HashSet<>();
		for (String item : list) {
		    if (!set.contains(item)){
				result.add(item);
				set.add(item);
		    }
		}
		return result;
    }
	//Neccessariliy array of distinct integers
		public static int indexOf(int element, int[] arr){
			for(int i=0;i<arr.length;i++){
				if(arr[i]==element){
					return i;
				}
			}
			return -1;
		}
		
		public static int cycle(int i, int[] temp){
			int index = indexOf(i, order_final);
			int n=order_final[index];
			temp[index]=i;
			return n;
		}
	
	public static BigInteger factorial2(int num){
		BigInteger one=BigInteger.valueOf(1);
		BigInteger prod=one;
		for(BigInteger i=BigInteger.valueOf(num);i.min(one)==one;i.subtract(one)){
			prod=prod.multiply(i);
		}
		return prod;
	}
	
	public static int factorial(int num){
		int prod=1;
		for(int i=num;i>1;i--){
			prod*=i;
		}
		return prod;
	}
		

	
	public static int sum_digits_square(int i){
		if(i==1 || i==89){
			return i;
		}
		char[] digits=String.valueOf(i).toCharArray();
		int sum=0;
		for(char c:digits){
			sum+=Math.pow((double)(Character.getNumericValue(c)), 2);
		}
		return sum_digits_square(sum);
	}
	
	public static boolean triangle_word(String n){
		ArrayList<Integer> triangles=new ArrayList<Integer>();
		int num=0;
		for(int index=1;index<500;index++){
			num+=index;
			triangles.add(num);
		}
		int sum=0;
		for(char i:n.toCharArray()){
			sum+=i-'A'+1;
		}
		return triangles.contains(sum) ? true:false;
	}
	
	public static int num_divisors(int n){
		int num=0;
		for(int i=2;i<(int)(n/2)+1;i++){
			if(n%i==0){
				num++;
			}
		}
		return num+2;
	}
	
	public static ArrayList<Integer> primes(int a, int b){
		ArrayList<Integer> list=new ArrayList<Integer>();
		for(int i=a;i<=b;i++){
			if(isPrime(i)){
				list.add(i);
			}
		}
		return list;
	}
	
	public static boolean isPrime(int n){
		for(int i=2;i<n;i++){
			if(n%i==0){
				return false;
			}
		}
		return true;
	}
	

	public static boolean amicable(int a, int b){
		return sum_divisors(a)==b && sum_divisors(b)==a ? true:false;
	}
	
	public static int sum_divisors(int n){
		int sum=0;
		for(int i=2;i<(int)(n/2)+1;i++){
			if(n%i==0){
				sum+=i;
			}
		}
		return sum+1;
	}

}