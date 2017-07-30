import java.io.*;
import java.util.*;

public class NotLast {
	static int counter=0;
	public static void main(String[] args) throws Exception {
		long start=System.currentTimeMillis();
		Scanner scan=new Scanner(new File("notlast.in"));
		PrintWriter writer = new PrintWriter(new File("notlast.out"));
		int n=Integer.parseInt(scan.nextLine());
		int[] milk={0, 0, 0, 0, 0, 0, 0};
		for(int i=0;i<n;i++){
			String[] info=scan.nextLine().split(" ");
			String name=info[0];
			int amt=Integer.parseInt(info[1]);
			System.out.println(name+ " "+i);
			if(name.equals("Bessie")){
				milk[0]+=amt;
			}
			else if(name.equals("Elsie")){
				milk[1]+=amt;
			}
			else if(name.equals("Daisy")){
				milk[2]+=amt;
			}
			else if(name.equals("Gertie")){
				milk[3]+=amt;
			}
			else if(name.equals("Annabelle")){
				milk[4]+=amt;
			}
			else if(name.equals("Maggie")){
				milk[5]+=amt;
			}
			else if(name.equals("Henrietta")){
				milk[6]+=amt;
			}
			else{
				System.out.println("lol");
			}
		}
		System.out.println(Arrays.toString(milk));
		int min=milk[0];
		for(int element:milk){
			if(element<min)
				min=element;
		}
		System.out.println("M: "+min);
		for(int i=0;i<milk.length;i++){
			if(milk[i]==min){
				milk[i]=-1;
			}
		}
		System.out.println(Arrays.toString(milk));
		//ok
		int min2=0;
		int inc=0;
		for(int in:milk){
			if(in!=-1)
				min2=in;
			else{
				inc++;
			}
		}
		if(inc==7){
			min2=-1;
		}
		System.out.println(min2);
		int num=milk.length;
		for(int i=0;i<milk.length;i++){
			if(milk[i]==-1){
				num--;
				continue;
			}
			if(milk[i]<min2){
				min2=milk[i];
			}
		}
		System.out.println(min2);
		int count=0;
		int index = 0;
		for(int i=0;i<milk.length;i++){
			if(milk[i]==min2){
				index=i;
				count++;
			}
		}
		
		String cow="";
		if(count>1){
			cow="Tie";
		}
		else if(count==1){
			if(index==0){
				cow="Bessie";
			}
			else if(index==1){
				cow="Elsie";
			}
			else if(index==2){
				cow="Daisy";
			}
			else if(index==3){
				cow="Gertie";
			}
			else if(index==4){
				cow="Annabelle";
			}
			else if(index==5){
				cow="Maggie";
			}
			else if(index==6){
				cow="Henrietta";
			}
		}
		
		System.out.println(cow);
		writer.println(cow);
		writer.close();
		long end=System.currentTimeMillis()-start;
	}
}