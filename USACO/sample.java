import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class sample {

	public static void main(String[] args) throws Exception {
		PrintWriter writer = new PrintWriter(new File("art2.out"));
		Scanner scan=new Scanner(new File("haybales.in"));
		
		int m=0;
		while(scan.hasNextLine()){
			scan.nextLine();
			m++;
		}
		System.out.println(m);

	}
	
	public static void timeout(){
		while(true){
			
		}
	}

}
