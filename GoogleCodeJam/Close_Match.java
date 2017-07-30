import java.util.Scanner;
import java.io.*;
import java.time.*;
import java.math.*;
import java.awt.*;

public class Close_Match {

	public static void main(String[] args) throws Exception{
		Scanner scan= new Scanner(new File("input.txt"));
		PrintWriter writer=new PrintWriter(new File("output.txt"));
		int numinputs=scan.nextInt();
		scan.nextLine();
		
		for(int cases=1;cases<=numinputs;cases++){
			String a=scan.nextLine();
			String b=scan.nextLine();
			String apat=a.replaceAll("\\*", ".{0,4}");
			
			String bpat=b.replaceAll("\\*", ".{0,4}");
			
			System.out.println(apat);
			System.out.println(bpat);
			System.out.println("bpat".matches(apat));
			
			//String output="Case #"+cases+": "+"_____";
			//System.out.println(output);
			//writer.println(output);
		}
		scan.close();
		writer.close();
	}

}
