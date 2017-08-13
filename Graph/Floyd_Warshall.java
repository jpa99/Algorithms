import java.awt.*;
import java.util.*;
import java.util.Queue;
import java.util.stream.Collectors;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;


public class Floyd_Warshall {
	
	/*Floyd-Warshall Algorithm 

	 Problem (informal): Given a weighted graph G, source u and sink v, find minimally weighted u~v path in G
	 
	 Algorithm: BFS with priority queue; maintains distance to certain node and updates along with previous 
	 
	 Complexity:
	 	* Time - O(|V|^2) where V is set of vertices and E is set of edges
	 	* Space - O(|V|) to store visited vertices
	 	
	 Functions Defined:
	 	* floyd_warshall() - Main algorithm to return all pairs shortest path
	 	* Vertex() - Custom class to store weighted graphs
	 	* display() - Takes adjacency matrix input and displays GUI graph visualization (*Requires JUNG Library*)
	 

	 Notes:
	 	* IterativeBFS runs 3-4x faster than QueueBFS in practice (on average)
	 	
	 */

	public static void main(String[] args) {
		
		//Initializing and filling sample array
		ArrayList<Vertex>[] adjlist= new ArrayList[5];
	
		adjlist[1] = new ArrayList<Vertex>();
		adjlist[2] = new ArrayList<Vertex>();
		adjlist[3] = new ArrayList<Vertex>();
		adjlist[4] = new ArrayList<Vertex>();
		
		adjlist[1].add(new Vertex(2, 1)); adjlist[1].add(new Vertex(3, 3));
		adjlist[2].add(new Vertex(3, 1)); adjlist[2].add(new Vertex(4, 2)); adjlist[2].add(new Vertex(4, 1));
		adjlist[3].add(new Vertex(4, 2));
		adjlist[4].add(new Vertex(1, 4));
		
		//Starting node
		int source = 1;
		//Ending node
		int dest = 4;
		System.out.print(floyd_warshall(adjlist, source, dest));
		
		//Uncomment next line to visualize graph (need to convert to array first
		//display(adj, "Graph");
	}
	
	//Queue based implementation (CLRS)
	//Returns value of shortest path from source to destination
	public static ArrayList<Integer> floyd_warshall(ArrayList<Vertex>[] adjlist, int source, int destination){
		boolean[] visited = new boolean[adjlist.length];
		int[] distance = new int[adjlist.length];
		int[] prev = new int[adjlist.length];
		Arrays.fill(distance, Integer.MAX_VALUE);
		PriorityQueue<Integer> q=new PriorityQueue<Integer>();
		q.add(source);
		distance[source] = 0;
		visited[source] = true;
		ArrayList<Vertex> bft = new ArrayList<Vertex>();
		while(!q.isEmpty()){
			int top=q.poll();
			visited[top] = true;
			for(Vertex node:adjlist[top]){
				if(distance[top] + node.val < distance[node.name]){
					prev[node.name] = top;
					distance[node.name] = distance[top] + node.val;
				}			
				if(!visited[node.name]){
					q.add(node.name);
					visited[node.name] = true;
				}
			}
		}
		ArrayList<Integer> path = new ArrayList<Integer>();
		path.add(source);
		int current = destination;
		while(current != source){
			path.add(1, current);
			current = prev[current];
		}
		return path;
	}
		
	//Uses JUNG to display 2D graph 
	public static void display(boolean[][] adj, String name){
		
		//Populate Graph object
		Graph<Integer,Integer> graph=new SparseMultigraph<Integer,Integer>();
		for(int i=0;i<adj.length;i++){
			graph.addVertex(i+1);
			for(int j=0;j<adj[0].length;j++){
				if(adj[i][j])
					graph.addEdge((int)(Math.random()*Integer.MAX_VALUE), i+1, j+1, EdgeType.UNDIRECTED);
			}
		}
		 
		//VisualizationImageServer vs = new VisualizationImageServer(new CircleLayout(g), new Dimension(650, 650));
		//Initialize visualization
        Layout<Integer, String> layout = new CircleLayout(graph);
		layout.setSize(new Dimension(620,620));
        VisualizationViewer<Integer,String> vs = new VisualizationViewer<Integer,String>(layout);
        vs.setPreferredSize(new Dimension(650,650));
        
        //Creates GraphMouse and adds to visualization
        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vs.setGraphMouse(gm);
		
		//Initialize JFrames
		JFrame frame = new JFrame(name);
	    frame.getContentPane().setBackground(Color.RED);
	    frame.getContentPane().add(vs);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	 
	    
	    //Colors Vertices
        Transformer<Integer,Paint> vertexPaint = new Transformer<Integer,Paint>() {
           public Paint transform(Integer i) {
        	   return Color.GREEN;
           } 
       };
		
	    //Labels Edges
	    float dash[] = {10.0f};
        final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
        Transformer<String, Stroke> edgeStrokeTransformer =new Transformer<String, Stroke>(){
            public Stroke transform(String s) {
                return edgeStroke;
            }
        };
        
        //Renders Vertex colors/labels
        vs.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vs.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
        
        //Renders Edge labels
        //vs.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());

	}

	//defined Vertex class for weighted graphs to hold edge weights
	static class Vertex{
		public int name;
		public int val;
		
		public Vertex(int name){
			this.name = name;
		}
		
		public Vertex(int name, int val){
			this.name = name;
			this.val = val;
		}
	
		
		public String toString(){
			return "Name: "+name+", Value: "+val;
		}
	}
	
	public static int[] list2arr(ArrayList<Integer> list){
		return list.stream().mapToInt(i->i).toArray();
	}
	
	public static ArrayList<Integer> list2arr(int[] arr){
		return (ArrayList<Integer>) Arrays.stream(arr).boxed().collect(Collectors.toList());
	}
}
