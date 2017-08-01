import java.awt.*;
import java.util.*;
import javax.swing.*;

import org.apache.commons.collections15.Transformer;

//Uses Java Universal Network/Graph (JUNG) Framework for conveniently displaying graphs
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.graph.util.*;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.control.*;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;


//Implementation of Prim's Algorithm to Determine Minimum Spanning Tree of Undirected Sparse Multigraph

public class Prim extends JPanel{
	
	/*Prim's Algorithm

	 Problem (informal): Given graph, finding spanning tree with minimally weighted edges (MST)
	 
	 Algorithm: Add least weighted edge incident to vertices in current MST at each iteration until all vertices added
	 
	 Complexity:
	 	* Time - O(|V|^2) searching adjacency matrix (effectively)
	 	* Space - O(|V|+|E|) to store arrays of vertices and edges
	 	
	 Functions Defined:
	 	* adjacentEdges() - Returns all edges adjacent to subgraph
	 	* contains() - Returns whether or not input vertex exists in input array
	 	* minEdge() - Given edge array, returns minimally weighted edge
	 	* addVertex() - Adds input vertex to input graph
	 	* addEdge() - Returns input Graph with added edge
	 	* convertVertices() - Given array of values, returns array of Vertex type values
	 	* convertEdge() - Given array of values with weight and vertices, returns array of Edge type values
	 	* display() - Takes adjacency matrix input and displays GUI graph visualization (*Requires JUNG Library*)
	 
	 Notes:
	 	* Greedy!
	 	* Should have used AdjacencyList for better performance; Fibonacci heap is optimal DS
	 	
	 */

	public static void main(String[] args) {
		int[] values= {1, 2, 3, 4, 5, 6, 7};
		int[][] lengths= {{5, 1, 2}, {1, 2, 3}, {2, 3, 1}, {3, 1, 4}, {9, 2, 4}, {20, 3, 4}, {11, 1, 7}, {8, 6, 3}, {10, 5, 6}, {13, 1, 5}, {6, 4, 6}, {7, 7, 2}, {12, 5, 3}, {16, 6, 1}, {14, 5, 2}, {19, 4, 7}, {15, 7, 3}};
		Vertex[] vertices=convertVertices(values);
		Edge[] edges=convertEdges(lengths);
		int numVertices=vertices.length;
		int numEdges=edges.length;
		Graphs g=new Graphs(vertices, edges);
		display(g, "red", "Undirected Sparse Graph");
		
		//Initializes Minimum Spanning Tree with single vertex
		Graphs mst=new Graphs(convertVertices(new int[0]), convertEdges(new int[0][0]));
		mst=addVertex(mst, g.getVertices()[0]);
		Graphs old_mst=mst;
		
		int iteration=0;
		//Prim's Algorithm: repeatedly identifies and dynamically adds minimally weighted adjacent edge to minimum spanning tree until all vertices are included  
		while(convertGraph(mst).getVertexCount()<g.getVertices().length){
			//Find minimally weighted adjacent edge
			Edge[] adjacentEdges=adjacentEdges(mst, g);
			Edge minEdge=minEdge(adjacentEdges);
			
			Vertex v1=minEdge.getVertex1();
			Vertex v2=minEdge.getVertex2();
			
			//Add vertex then edge to Minimum Spanning Tree
			if(mst.hasVertex(v1)){
				mst=addVertex(mst, v2);
			}
			else{
				mst=addVertex(mst, v1);
				minEdge.swap();
			}
			minEdge.makeDirected();
			mst=addEdge(mst, minEdge);
			
			iteration++;
			System.out.println("Iteration: "+iteration+"\n------------");
			System.out.println("Adjacent Edges: "+Arrays.toString(adjacentEdges));
			System.out.println("Minimally Weighted Adjacent Edge: "+minEdge.toString()+"\n");
			System.out.println(convertGraph(mst).toString()+"\n\n");
			display(mst, "green", "Minimum Spanning Tree Iteration "+iteration);
		}
				
		System.out.println("\n\nMST Cost: "+mst.getCost());
		display(mst, "green", "Minimum Spanning Tree");
	}
	
	//Returns all edges in Graph adjacent to (but not included in) vertices of subgraph
	public static Edge[] adjacentEdges(Graphs mst, Graphs g){
		ArrayList<Edge> adjacentEdges=new ArrayList<Edge>();
		Edge[] superEdges=g.getEdges();
		Vertex[] subVertices=mst.getVertices();
		for(Edge e:superEdges){
			if(contains(subVertices, e.getVertex1()) != contains(subVertices, e.getVertex2())){
				adjacentEdges.add(e);
			}
		}
		return adjacentEdges.toArray(new Edge[adjacentEdges.size()]);
		
	}
	
	//Returns whether or not input Vertex is included in array of vertices
	public static boolean contains(Vertex[] vertices, Vertex v){
		for(Vertex vertex:vertices){
			if(vertex.getValue()==v.getValue()){
				return true;
			}
		}
		return false;
	}
	
	//Returns minmally weighted edge in array
	public static Edge minEdge(Edge[] edges){
		Edge minEdge=edges[0];
		for(Edge e:edges){
			if(e.getWeight()<minEdge.getWeight()){
				minEdge=e;
			}	
		}
		return minEdge;
	}
	
	//Returns input Graphs with added vertex 
	public static Graphs addVertex(Graphs g, Vertex vertex){
		Vertex[] vertices=g.getVertices();
		Vertex[] newvertices=new Vertex[g.getVertices().length+1];
		for(int i=0;i<vertices.length;i++){
			newvertices[i]=vertices[i];
		}
		newvertices[newvertices.length-1]=vertex;
		return new Graphs(newvertices, g.getEdges());
	}
	
	//Returns input Graphs with added edge
	public static Graphs addEdge(Graphs g, Edge edge){
		Edge[] edges=g.getEdges();
		Edge[] newedges=new Edge[g.getEdges().length+1];
		for(int i=0;i<edges.length;i++){
			newedges[i]=edges[i];
		}
		newedges[newedges.length-1]=edge;
		return new Graphs(g.getVertices(), newedges);
	}
	
	//Converts array of integer values to array of Vertex values
	public static Vertex[] convertVertices(int[] values){
		Vertex[] vertices=new Vertex[values.length];
		for(int i=0;i<values.length;i++){
			vertices[i]=new Vertex(values[i]);
		}
		return vertices;
	}
	
	//Converts 2D array of values specifying weight and vertices into array of Edge values
	public static Edge[] convertEdges(int[][] lengths){
		Edge[] edges=new Edge[lengths.length];
		for(int i=0;i<lengths.length;i++){
			edges[i]=new Edge(lengths[i][0], lengths[i][1], lengths[i][2]);
		}
		return edges;
		
	}
	
	//Converts Graphs into JUNG compatible form Graph<Integer, Integer> for visualization
	public static Graph<Integer, Integer> convertGraph(Graphs g){
		Graph<Integer,Integer> graph=new SparseMultigraph<Integer,Integer>();
		for(Vertex v:g.getVertices()){
			graph.addVertex(v.getValue());
		}
		
		for(Edge e:g.getEdges()){
			if(e.getDirected()){
				graph.addEdge(e.getWeight(), e.getVertex1().getValue(), e.getVertex2().getValue(), EdgeType.DIRECTED);
			}
			else{
				graph.addEdge(e.getWeight(), e.getVertex1().getValue(), e.getVertex2().getValue());
			}
		}
		return graph;
	}
	
	//Uses JUNG to display 2D graph 
	public static void display(Graphs graph, String color, String name){
		
		Graph<Integer,Integer> g=convertGraph(graph);
		 
		//VisualizationImageServer vs = new VisualizationImageServer(new CircleLayout(g), new Dimension(650, 650));
		
		//Initialize visualization
        Layout<Integer, String> layout = new CircleLayout(g);
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
        	   if(color=="green"){
        		   return Color.GREEN;
        	   }
        	   else{
        		   return Color.RED;
        	   }
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
        vs.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());

	}
	

}
