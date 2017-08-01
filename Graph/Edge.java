
public class Edge {
	
	//Edge class with built in functions; used for Graph class
	
	private int weight;
	private Vertex v1;
	private Vertex v2;
	private boolean directed=false;
	
	public Edge(int weight, int value1, int value2){
		this.weight=weight;
		v1=new Vertex(value1);
		v2=new Vertex(value2);
	}
	
	public Edge(int weight, int value1, int value2, boolean directed){
		this.weight=weight;
		v1=new Vertex(value1);
		v2=new Vertex(value2);
		directed=true;
	}
	
	//returns whether or not the edge is incident to the input vertex
	public boolean incident(int vertex){
		return vertex==v1.getValue() || vertex==v2.getValue();
	}
	
	public Vertex getVertex1(){
		return v1;
	}
	
	public Vertex getVertex2(){
		return v2;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public boolean getDirected(){
		return directed;
	}
	
	public void makeDirected(){
		directed=true;
	}
	
	public void swap(){
		Vertex temp=v2;
		v2=v1;
		v1=temp;
	}
	
	public String toString(){
		return weight+"["+v1+","+v2+"]";
	}
}
