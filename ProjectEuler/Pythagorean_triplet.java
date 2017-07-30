
public class Pythagorean_triplet {

	public static void main(String[] args) {
		
		for(double a=1;a<1000;a++){
			for(double b=1;b<1000;b++){
				double c= Math.sqrt((Math.pow(a, 2))+(Math.pow(b, 2)));
				if(a+b+c==1000){
					System.out.println(a*b*c);
				}
			}
		}
		System.out.println("end");
	}
}
