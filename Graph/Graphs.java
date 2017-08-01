
public class Graphs {
	
	//Graph class with certain built in algorithms

	private Edge[] edges;
	private Vertex[] vertices;
	
	public Graphs(Vertex[] vertices, Edge[] edges){
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
