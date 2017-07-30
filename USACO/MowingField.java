import java.io.*;
import java.util.*;

public class MowingField {

	public static void main(String[] args) throws Exception {
		int min;
		ArrayList<int[]> points=new ArrayList<int[]>();
		int[] temp={0, 0};
		points.add(temp);
		Scanner scan=new Scanner(new File("mowing.in"));
		PrintWriter writer = new PrintWriter(new File("mowing.out"));
		int num=Integer.parseInt(scan.nextLine());
		String[][] directions=new String[num][2];
		for(int j=0;j<num;j++){
			directions[j]=scan.nextLine().split(" ");
		}
		for(int i=0;i<directions.length;i++){
			
			if(directions[i][0].equals("N")){
				for(int cells=0;cells<Integer.parseInt(directions[i][1]);cells++){
					points.add(new int[]{points.get(points.size()-1)[0], points.get(points.size()-1)[1]+1});
				}
			}
			
			else if(directions[i][0].equals("S")){
				for(int cells=0;cells<Integer.parseInt(directions[i][1]);cells++){
					points.add(new int[]{points.get(points.size()-1)[0], points.get(points.size()-1)[1]-1});
				}
			}
			
			else if(directions[i][0].equals("E")){
				for(int cells=0;cells<Integer.parseInt(directions[i][1]);cells++){
					points.add(new int[]{points.get(points.size()-1)[0]+1, points.get(points.size()-1)[1]});
				}
			}
			
			else if(directions[i][0].equals("W")){
				for(int cells=0;cells<Integer.parseInt(directions[i][1]);cells++){
					points.add(new int[]{points.get(points.size()-1)[0]-1, points.get(points.size()-1)[1]});
				}
			}
	
		}
		min=points.size()+1;
		for(int k=0;k<points.size();k++){
			for(int l=0;l<points.size();l++){
				if(!(k-l==0) && points.get(k)[0]==points.get(l)[0] && points.get(k)[1]==points.get(l)[1]){
					min=Math.min(min, Math.abs(l-k));
				}
			}
		}
		if(min>points.size()){
			System.out.println(-1);
			writer.println(-1);
		}
		else{
			System.out.println(min);
			writer.println(min);
		}
		writer.close();
		
		
	}

}
