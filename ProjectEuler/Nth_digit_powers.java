import java.lang.Math;

import java.util.*;

public class Nth_digit_powers {

	public static void main(String[] args) {
		Date start=new Date();
		int num=0;
		for(int i=1;i<1000000000;i++){
			double pow= Math.pow(i, 1.0/(String.valueOf(i).length()));
			if(pow==(int)(pow)){
				num++;
			}
		}
		System.out.println(num);
		Date end=new Date();
		System.out.println("Run time = "+ (start.getTime() - end.getTime())/1000.0+" seconds");
	}

}
