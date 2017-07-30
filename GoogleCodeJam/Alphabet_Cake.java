import java.util.*;
import java.util.Map.Entry;
import java.io.*;
import java.time.*;
import java.math.*;
import java.awt.*;

public class Alphabet_Cake {
	static String input="input.txt";
	static String out="output.txt";
	static PrintWriter writer;
	static Scan scan;

	public static void main(String[] args) throws Exception{
		writer=new PrintWriter(new BufferedWriter(new FileWriter(out)));
		Scan scan=new Scan(input);
		int numinputs=scan.nextInt();
		for(int cases=1;cases<=numinputs;cases++){
			int r=scan.nextInt(); int c=scan.nextInt();
			char[][] cake=new char[r][c];
			ArrayList<Character> nonfree=new ArrayList<Character>();
			HashMap<Character, ArrayList<int[]>> map=new HashMap<Character, ArrayList<int[]>>();
			for(int i=0;i<r;i++){
				cake[i]=scan.nextLine().toCharArray();
				for(int j=0;j<c;j++){
					char x=cake[i][j];
					int[] asdf={i, j};
					ArrayList<int[]> ar=map.get(x);
					ar.add(asdf);
					map.put(x, ar);
				}
			}
			
			Iterator<Entry<Character, ArrayList<int[]>>> it = map.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<Character, ArrayList<int[]>> pair = (Map.Entry<Character, ArrayList<int[]>>)it.next();
		        ArrayList<int[]> arr=pair.getValue();
		        char cc=pair.getKey();
		        it.remove();
		        
		        boolean h=false, v=false;
		        for(int k=1;k<arr.size();k++){
		        	if(Math.abs(arr.get(k)[0]-arr.get(k-1)[0]) > 1){
		        		int sr=arr.get(0)[0], er=arr.get(arr.size())[0];
				        int sc=arr.get(0)[1], ec=arr.get(arr.size())[1];
				        
		        		break;
		        	}
		        	if(Math.abs(arr.get(k)[1]-arr.get(k-1)[1]) > 1){
		        		int sr=arr.get(0)[0], er=arr.get(arr.size())[0];
				        int sc=arr.get(0)[1], ec=arr.get(arr.size())[1];
		        		break;
		        	}
		        }
		        
		        
		        if(!(v || h))
		        	continue;
		        
		        int sr=arr.get(0)[0], er=arr.get(arr.size())[0];
		        int sc=arr.get(0)[1], ec=arr.get(arr.size())[1];
		        for(int[] p:pair.getValue()){
		        	cake[p[0]][p[1]]=cc;
		        }
		        
		    }
			
			
			
			String output="Case #"+cases+": ";
			System.out.println(output);
			writer.println(output);
		}
		
		writer.close();
	}
	
	//Print line
	public static <T> void print(T t){
		   System.out.println(t);
	}
	
	//Optimized Scanner Class, includes next String, int, Long, double, line
	static class Scan{
        BufferedReader br;
        StringTokenizer st;
 
        public Scan(String file) throws IOException{
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            
        }
 
        String next(){
            while (st == null || !st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt(){
            return Integer.parseInt(next());
        }
 
        long nextLong(){
            return Long.parseLong(next());
        }
 
        double nextDouble(){
            return Double.parseDouble(next());
        }
 
        String nextLine(){
            String str = "";
            try{
                str = br.readLine();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return str;
        }
    }

}
