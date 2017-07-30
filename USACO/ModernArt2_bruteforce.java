import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ModernArt2_bruteforce {
	
	public static void main(String[] args) throws Exception{
		Scanner scan=new Scanner(new File("art2.in"));
		PrintWriter writer = new PrintWriter(new File("art2.out"));
		int rounds=0;
		int n=scan.nextInt();
		boolean test=true;
		if(n>=50000){
			test=false;
			rounds=-1;
		}
		int min=10;
		int max=100;
		if(test){
			int[] paint=new int[n];
			ArrayList<Integer> colors=new ArrayList<Integer>();
			
			for(int i=0;i<n;i++){
				paint[i]=scan.nextInt();
				if(!colors.contains(paint[i]) && paint[i]!=0){
					colors.add(paint[i]);
				}
			}
			
			ArrayList<ArrayList<Integer>> map=new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> unknown=new ArrayList<Integer>();
			for(int i=0;i<=paint.length;i++){
				map.add(new ArrayList<Integer>());
			}
			
			for(int i=0;i<paint.length;i++){
				map.get(paint[i]).add(i);
			}
			
			
			/*-------------------------------------------------------------------------------------------------------------
			 * Populated Array paint, populated Nested List map (color -> position), List unknown (for -1), List colors containing all unused colors
			---------------------------------------------------------------------------------------------------------------*/
	
			rounds=0;
			//System.out.println(Arrays.toString(paint));
			boolean cont=true;
			if(n>=50000){
				cont=false;
				rounds=-1;
			}
	
			ArrayList<Integer> temp=new ArrayList<Integer>();
			while(cont){
				temp=new ArrayList<Integer>();
				//visualization
				for(int i=0;i<map.size();i++){
					for(int x:map.get(i)){
						paint[x]=i;
					}
				}
				for(int a:unknown){
					paint[a]=-1;
				}
				
				boolean empty=true;
				for(int i=1;i<paint.length;i++){
					if(colors.contains(i) && !map.get(i).isEmpty() && consecutive(map.get(i), unknown)){
						empty=false;
						temp.add(i);
					}
				}		
				
				for(int i:temp){
					unknown.addAll(map.get(i));
					map.set(i, new ArrayList<Integer>());
					colors.remove(colors.indexOf(i));
				}
				
				if(empty)
					break;
				
				rounds++;
			}
	}
		//if(colors.isEmpty())
		if(true){
			System.out.println(rounds);
			writer.println(rounds);
		}
		else{
			System.out.println(-1);
			writer.println(-1);
		}
		writer.close();
		
	}
	
	//returns true if there exists a sequence of consecutive integers containing all elements in list2 and a subset of x2
	public static boolean consecutive(ArrayList<Integer> list2, ArrayList<Integer> x2){
		ArrayList<Integer> list=new ArrayList<Integer>();
		ArrayList<Integer> x=new ArrayList<Integer>();
		for(int a:list2)
			list.add(a);
		for(int b:x2)
			x.add(b);

		int[] arr = list.stream().mapToInt(i->i).toArray();
		Arrays.sort(arr);
		
		for (int i = arr[0];i<= arr[arr.length-1]; i++) {
			if (list.contains(i))
				list.remove(list.indexOf(i));
			else if(!x.contains(i))
				return false;
		}
		if(list.size()==0)
			return true;
		
		return false;
	}
	
	public static void timeout(){
		while(true){
			
		}
	}

	
}
