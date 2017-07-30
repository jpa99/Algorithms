import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class BuildGates {
	public static void main(String[] args) throws Exception{
		int min;
		ArrayList<int[]> points=new ArrayList<int[]>();
		int[] temp={0, 0};
		points.add(temp);
		Scanner scan=new Scanner(new File("gates.in"));
		PrintWriter writer = new PrintWriter(new File("mowing.out"));
		int num=Integer.parseInt(scan.nextLine());
		String[] directions=scan.nextLine().split("");
		
		for(int i=0;i<directions.length;i++){
			if(directions[i].equals("N")){
				points.add(new int[]{points.get(points.size()-1)[0], points.get(points.size()-1)[1]+1});
			}
			else if(directions[i].equals("S")){
				points.add(new int[]{points.get(points.size()-1)[0], points.get(points.size()-1)[1]-1});
			}	
			else if(directions[i].equals("E")){
				points.add(new int[]{points.get(points.size()-1)[0]+1, points.get(points.size()-1)[1]});
			}
			else if(directions[i].equals("W")){
				points.add(new int[]{points.get(points.size()-1)[0]-1, points.get(points.size()-1)[1]});
			}
		}
		
		for(int[] i:points){
			System.out.println(Arrays.toString(i));
		}
		
		
	}

}
