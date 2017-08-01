
public class Vertex {
	
	//Vertex class
	//probably unnecessary, but already built into Prim's Algorith implementation
	
	int value;
	
	public Vertex(int val){
		value=val;
	}
	
	public int getValue(){
		return value;
	}
	
	public String toString(){
		return String.valueOf(value);
	}
	
}
