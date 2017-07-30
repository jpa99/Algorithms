import java.io.File;
import java.util.*;

public class Super_Substrings {

	public static void main(String[] args) throws Exception{
		Scanner scan=new Scanner(new File("input.txt"));
		String n=scan.next();
		ArrayList<String> subs=new ArrayList<String>();
		for(int i=0;i<n.length();i++){
			for(int j=i+1;j<=n.length();j++){
				subs.add(n.substring(i, j));
			}
		}
		
		int count=0;
		for(String s:subs){
			int num=Integer.parseInt(s);
			if(num%6==0 && String.valueOf(num).length()==s.length()){
				count++;
			}
		}
		System.out.println(count);

	}
	public static int sum(int num){
		int sum=0;
		while (num > 0) {
            sum+=num % 10;
            num/=10;
        }
		return sum;
	}


}	
