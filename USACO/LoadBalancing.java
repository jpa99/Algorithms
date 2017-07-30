import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class LoadBalancing {

	public static void main(String[] args) throws Exception{
		int n;
		String[] v;
		int[] temp;
		ArrayList<int[]> points=new ArrayList<int[]>();
		ArrayList<Integer> x=new ArrayList<Integer>();
		ArrayList<Integer> y=new ArrayList<Integer>();
		Scanner scan=new Scanner(new File("balancing.in"));
		PrintWriter writer = new PrintWriter(new File("balancing.out"));
		n=Integer.parseInt(scan.nextLine());
		int min=n;
		for(int i=0;i<n;i++){
			v=scan.nextLine().split(" ");
			x.add(Integer.parseInt(v[0]));
			y.add(Integer.parseInt(v[1]));
			points.add(new int[]{Integer.parseInt(v[0]), Integer.parseInt(v[1])});
		}
		Collections.sort(x);
		Collections.sort(y);
		int b;
		for(int a=x.get(0)-1;a<=x.get(x.size()-1)+1;a+=2){
			for(b=y.get(0)-1;b<=y.get(y.size()-1)+1;b+=2){
				if(max_points(a, b, points)>max_points(a, b-2, points)){
					break;
				}
				min=Math.min(min, max_points(a, b, points));
			}
			if(max_points(a, b, points)>max_points(a-2, b, points)){
				break;
			}
		}
		System.out.println(min);
		writer.println(min);
		writer.close();
	}
	
	public static int max_points(int x, int y, ArrayList<int[]> points){
		int q1=0, q2=0, q3=0, q4=0;
		for(int[] i:points){
			if(i[0]>x && i[1]>y){
				q1++;
			}
			else if(i[0]<x && i[1]>y){
				q2++;
			}
			else if(i[0]<x && i[1]<y){
				q3++;
			}
			else{
				q4++;
			}
		}
		return Math.max(Math.max(q1, q2), Math.max(q3, q4));
	}
	
	/*
	public static int optimalx(ArrayList<Integer> x){
		removeDuplicates(x);
		Collections.sort(x);
		
	}
	
	public static int median(ArrayList<Integer> list){
		int len=list.size();
		if(len%2==0){
			return (list.get(len/2) + list.get((len/2)+1))/2;
		}
		else{
			
		}
	}
	
	public static void removeDuplicates(ArrayList<Integer> list){
		list = new ArrayList<Integer>(new LinkedHashSet<Integer>(list));
	}
	*/
}
