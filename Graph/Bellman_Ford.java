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


public class Bellman_Ford {
	
	/*Bellman-Ford Algorithm 

	 Problem (informal): Given a weighted graph G, source u and sink v, find minimally weighted u~v path in G
	 
	 Algorithm: Iterates over all vertices and edges and relaxes edges
	 
	 Complexity:
	 	* Time - O(|V|*|E|) since main loop iterates over all vertices and edges
	 	* Space - O(|V| + |E|) to store edges and vertices
	 	
	 Functions Defined:
	 	* bellman_ford() - Main algorithm to return shortest path or Integer.MIN_VALUE if negative cycle exists
	 	* Vertex() - Custom class to store weighted graphs
	 	* display() - Takes adjacency matrix input and displays GUI graph visualization (*Requires JUNG Library*)
	 	
	 */

	public static void main(String[] args) {
		
		//Initializing and filling sample array
		ArrayList<Vertex>[] adjlist= new ArrayList[5];
	
		adjlist[1] = new ArrayList<Vertex>();
		adjlist[2] = new ArrayList<Vertex>();
		adjlist[3] = new ArrayList<Vertex>();
		adjlist[4] = new ArrayList<Vertex>();
		
		adjlist[1].add(new Vertex(2, -1)); adjlist[1].add(new Vertex(3, 3));
		adjlist[2].add(new Vertex(3, 1)); adjlist[2].add(new Vertex(4, 5)); adjlist[2].add(new Vertex(4, -3));
		adjlist[3].add(new Vertex(4, 2));
		adjlist[4].add(new Vertex(1, 10));
		
		//Starting node
		int source = 1;
		//Ending node
		int dest = 4;
		System.out.print(bellman_ford(adjlist, source, dest));
		
		//Uncomment next line to visualize graph (need to convert to array first
		//display(adj, "Graph");
	}
	
	//Queue based implementation (CLRS)
	//Returns value of shortest path from source to destination
	public static int bellman_ford(ArrayList<Vertex>[] adjlist, int source, int dest){
		boolean[] visited = new boolean[adjlist.length];
		int[] distance = new int[adjlist.length];
		int[] prev = new int[adjlist.length];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[1] = 0;
		for(int ver=1;ver<adjlist.length;ver++){ //iterates over all vertices
			for(int i=1;i<adjlist.length;i++){ //iterates over all edges
				for(Vertex neighbor:adjlist[i]){
					if(distance[i] + neighbor.weight < distance[neighbor.val]){
						distance[neighbor.val] = distance[i] + neighbor.weight;
						prev[neighbor.val] = i;
					}
				}
			}
		}
		
		for(int i=1;i<adjlist.length;i++){ //iterates over all edges
			for(Vertex neighbor:adjlist[i]){
				if(distance[neighbor.val] > neighbor.weight + distance[i])
					return Integer.MIN_VALUE; //Negative cycle exists
			}
		}
		
		return distance[dest];
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
		public int val;
		public int weight;
		
		public Vertex(int val){
			this.val = val;
		}
		
		public Vertex(int val, int weight){
			this.val = val;
			this.weight = weight;
		}
	
		
		public String toString(){
			return "Val: "+val+", Weight: "+weight;
		}
	}
	
	public static int[] list2arr(ArrayList<Integer> list){
		return list.stream().mapToInt(i->i).toArray();
	}
	
	public static ArrayList<Integer> list2arr(int[] arr){
		return (ArrayList<Integer>) Arrays.stream(arr).boxed().collect(Collectors.toList());
	}
}
