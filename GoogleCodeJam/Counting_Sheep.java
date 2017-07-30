import java.io.*;
import java.util.*;

public class Counting_Sheep {

	public static void main(String[] args) throws Exception{
		Scanner scan=new Scanner(new File("A-small-practice.in.txt"));
		PrintWriter writer=new PrintWriter(new File("countingsheep.out"));
		int n=scan.nextInt();
		for(int i=1;i<=n;i++){
			int num=scan.nextInt();
			boolean possible=true;
			if(num==0){
				possible=false;
			}
			String s="";
			
			int mult=1;
			while(possible){
				
				s+=String.valueOf(num);
				int con=0;
				for(int j=0;j<10;j++){
					if(s.contains(String.valueOf(j))){
						con++;
					}
				}
				
				mult++;
				num*=mult;
				num/=mult-1;
						
				if(con==10)
					break;
			}
			num*=mult-1;
			num/=mult;
			
			if(possible)
				writer.printf("Case #%d: %d\n", i, num);
			else
				writer.printf("Case #%d: INSOMNIA\n", i);
			
			
		}
		writer.close();

	}

}
