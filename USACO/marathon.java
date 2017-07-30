import java.io.*;
import java.util.*;

public class marathon {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("marathon.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("marathon.out")));
		int checkpoints=Integer.parseInt(reader.readLine());
		List<ArrayList<Integer>> points=new ArrayList<ArrayList<Integer>>();
		int distance_saved=0;
		int total_distance=0;;
		for(int i=0;i<=checkpoints;i++){
			if(i<4){
				StringTokenizer st=new StringTokenizer(reader.readLine());
				ArrayList<Integer> temp=new ArrayList<Integer>();
				temp.add(Integer.parseInt(st.nextToken()));
				temp.add(Integer.parseInt(st.nextToken()));
				points.add(temp);
			}
			if(i>1 && i<checkpoints){
				total_distance+=distance_between(points.get(i-2), points.get(i-1));
				int local_distance_saved=distance_between(points.get(i-2), points.get(i-1))+distance_between(points.get(i-1), points.get(i))-distance_between(points.get(i-2), points.get(i));
				if(local_distance_saved>distance_saved){
					distance_saved=local_distance_saved;
				}
			}
		}
		total_distance+=distance_between(points.get(checkpoints-2), points.get(checkpoints-1));
		writer.println(total_distance-distance_saved);
		writer.close();
		System.exit(0);
	}
	
	public static int distance_between(ArrayList<Integer> p1, ArrayList<Integer> p2){
		return Math.abs(p1.get(0)-p2.get(0))+Math.abs(p1.get(1)-p2.get(1));
	}

}
