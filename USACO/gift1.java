/*
ID: joelpabraham
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class gift1 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("gift1.in"));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
		int np=Integer.parseInt(reader.readLine());
		HashMap<String, Integer> names_final=new HashMap<String, Integer>();
		String[] names=new String[np];
		for(int i=0;i<np;i++){
			names[i]=reader.readLine();
			names_final.put(names[i], 0);
		}
		int gift;
		int leftover;
		for(int i=0;i<np;i++){
			String giver=reader.readLine();
			StringTokenizer st = new StringTokenizer(reader.readLine());
			int money=Integer.parseInt(st.nextToken());
			int recipients=Integer.parseInt(st.nextToken());
			if(recipients!=0){
				gift=money/recipients;
				leftover=money%recipients;
			}
			else{
				gift=0;
				leftover=money;
			}
			int a=names_final.remove(giver);
			names_final.put(giver, a+leftover-money);
			for(int j=0;j<recipients;j++){
				String recipient=reader.readLine();
				int temp=names_final.remove(recipient);
				names_final.put(recipient, temp+gift);
			}
		}
		for(String i:names){
			writer.println(i+" "+(names_final.get(i)));
		}
		writer.close();
		System.exit(0);
	}

}
