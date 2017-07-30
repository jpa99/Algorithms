import java.io.*;
import java.util.*;

public class asdf {
	static int minindex;
	static int min;
	static int time;
	static int[] stage;
	static int[] times;
	static ArrayList<Integer> queue;
	static int t;

	public static void main(String[] args) throws Exception {
		long start=System.currentTimeMillis();
		Scanner scan=new Scanner(new File("cowdance.in"));
		PrintWriter writer = new PrintWriter(new File("cowdance.out"));
		int n=scan.nextInt();
		double t=scan.nextInt();
		int ans=0;
		times=new int[n];
		double x=0;
		for(int asdf=0;asdf<n;asdf++){
			times[asdf]=scan.nextInt();
			x+=times[asdf];
		}
		
		int c=(int)(Math.ceil(x/t));
		System.out.println(c);
		writer.println(c);
		writer.close();
		long end=System.currentTimeMillis()-start;
		System.out.println("Time: "+end+" ms");

	}
	
	//returns whether the stage size is big enough to keep under max time
	public static int time(int k){
		queue=new ArrayList<Integer>();
		stage=new int[k];
		//populate stage array with k elements and queue list with the rest
		for(int j=0;j<k;j++){
			stage[j]=times[j];
		}
		for(int j=k;j<times.length;j++){
			queue.add(times[j]);
		}
		time=0;
		while(time<=t && (!queue.isEmpty())){
			minindex=min(stage);
			min=stage[minindex];
			time+=min;
			for(int num=0;num<stage.length;num++){
				stage[num]-=min;
			}
			stage[minindex]=queue.get(0);
			queue.remove(0);
		}
		time+=maxval(stage);
		return time;
	}
	
	public static int maxval(int[] stage){
		int max=stage[0];
		int index=0;
		for(int i=0;i<stage.length;i++){
			if(stage[i]>max){
				max=stage[i];
				index=i;
			}
		}
		return max;
	}
	
	public static int min(int[] stage){
		int min=stage[0];
		int index=0;
		for(int i=0;i<stage.length;i++){
			if(stage[i]<min){
				min=stage[i];
				index=i;
			}
		}
		return index;
	}

}
