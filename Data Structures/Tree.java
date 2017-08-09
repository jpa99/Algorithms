import java.util.*;

public class Tree {

	public static void main(String[] args) {
		Node<String> tree = new Node<String>("Parent"); 
		
		tree.add(new Node<String>("child"));
		tree.getChildren().get(0);

	}
	public static class Node<T> {
	    private List<Node<T>> children = new ArrayList<Node<T>>();
	    private Node<T> parent = null;
	    private T data = null;

	    public Node(T data) {
	        this.data = data;
	    }

	    public Node(T data, Node<T> parent) {
	        this.data = data;
	        this.parent = parent;
	    }

	    public List<Node<T>> getChildren() {
	        return children;
	    }

	    public void setParent(Node<T> parent) {
	        parent.add(this);
	        this.parent = parent;
	    }

	    public void add(T data) {
	        Node<T> child = new Node<T>(data);
	        child.setParent(this);
	        this.children.add(child);
	    }

	    public void add(Node<T> child) {
	        child.setParent(this);
	        this.children.add(child);
	    }

	    public T data() {
	        return this.data;
	    }

	    public void setData(T data) {
	        this.data = data;
	    }

	    public boolean isRoot() {
	        return (this.parent == null);
	    }

	    public boolean isLeaf() {
	        return this.children.size() == 0;
	    }

	    public void removeParent() {
	        this.parent = null;
	    }
	}


	public class Graph {

	private Edge[] edges;
	private Vertex[] vertices;
	
	public Graph(Vertex[] vertices, Edge[] edges){
		this.vertices=vertices;
		this.edges=edges;
	}
	
	//Returns whether or not graph contains input vertex
	public boolean hasVertex(Vertex v){
		for(Vertex vertex:vertices){
			if(vertex.getValue()==v.getValue()){
				return true;
			}
		}
		return false;
	}
	
	//returns sum of edge weights in graph
	public int getCost(){
		int sum=0;
		for(Edge e:edges){
			sum+=e.getWeight();
		}
		return sum;
	}
	
	public Edge[] getEdges(){
		return edges;
	}
	
	public String[] vertex(){
		String[] v= new String[vertices.length];
		for(int i=0;i<vertices.length;i++){
			v[i]=vertices[i].toString();
		}
		return v;
	}
	
	public Vertex[] getVertices(){
		return vertices;
	}
	
	public String toString(){
		String ver="";
		for(Vertex v:vertices){
			ver+=v.toString()+",";
		}
		String edg="";
		for(Edge e:edges){
			edg+=e.toString()+" ";
		}
		return "Vertices: "+ver+"\nEdges: "+edg+"\n";
	}
	
	
	
	
}


	public class Edge {
	
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

	

	public class Vertex {
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

}

