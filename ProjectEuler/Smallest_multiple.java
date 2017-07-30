
public class Smallest_multiple {

	public static void main(String[] args) {
		for(int i=2520; i< (2.5*Math.pow(10,18)); i++){
			int sum=0;
			for(int k=20;k>1;k--){
				if(i%k==0){
					sum++;
				}
			}
			if(sum==19){
				System.out.println(i);
				break;
			}
		}
	}
	
}
