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
public class Karger {
	
	/*Karger's Min-Cut Algorithm

	 Problem (informal): Given weighted graph, find cut with minimally weighted edges spanning cut
	 
	 Algorithm: Contract edges randomly until no more possible; remaining cut is probably minimal
	 
	 Complexity:
	 	* Time - O(|V|^2) = O(|E|)
	 	* Space - O(|V|+|E|) to store lists
	 	
	 Functions Defined:
	 	* edgeContraction() - Contracts a random edge and outputs resulting edge list
	 	* newVertices() - Adjusts list of vertices, collapsing to account for contracted edge
	 	* convertGraph() - converts edge list and vertex list to JUNG Graph type
	 	* display() - Takes adjacency matrix input and displays GUI graph visualization (*Requires JUNG Library*)
	 
	 Notes:
	 	* Min Cut = Max Flow :)
	 	
	 */

	public static void main(String[] args) {
		
		int num_Vertices;
		int[] vertices= {1, 2, 3, 4, 5, 6, 7};
		int[][] edges={{5, 1, 2}, {1, 2, 3}, {2, 3, 1}, {3, 1, 4}, {9, 2, 4}, {20, 3, 4}, {11, 1, 7}, {8, 6, 3}, {10, 5, 6}, {13, 1, 5}, {6, 4, 6}, {7, 7, 2}, {12, 5, 3}, {16, 6, 1}, {14, 5, 2}, {19, 4, 7}, {15, 7, 3}};
		int mincut=0;;
		
		display(vertices, edges, "red", "Undirected Sparse Graph");
		
		while(vertices.length>2){
			edges=edgeContraction(edges);
			vertices=newVertices(vertices, edges);
		}
		
		display(vertices, edges, "green", "Min-Cut");
		
		for(int[] edge:edges){
			//System.out.println(Arrays.toString(edge));
			mincut+=edge[0];
		}
		//System.out.println(Arrays.toString(vertices));
		
		System.out.println(mincut);
		
	}
	
	public static int[][] edgeContraction(int[][] edges){
		
		//choose random edge # to collapse betwen 0 and edges.length-1
		//store vertex 1, vertex 2, delete the edge
		//all edges from vertex 2 change to vertex 1;
		
		//int[][] newEdges=new int[edges.length-1][3];
		ArrayList<int[]> newEdges=new ArrayList<int[]>();
		
		int randEdge=(int)(Math.random()*edges.length);
		int v1=edges[randEdge][1], v2=edges[randEdge][2];
	
		//Populate list of vertices + weights that are connected to edge
		for(int i=0;i<edges.length;i++){
			if(i==randEdge)
				continue;
			if(edges[i][1]==v2)
				edges[i][1]=v1;
			if(edges[i][2]==v2)
				edges[i][2]=v1;
			if(edges[i][1]!=edges[i][2])
				newEdges.add(edges[i]);
		}
		int[][] edgeprime=new int[newEdges.size()][3];
		for(int i=0;i<newEdges.size();i++){
			edgeprime[i]=newEdges.get(i);
		}
		
		return edgeprime;
	}
	
	public static int[] newVertices(int[] vertices, int[][] edges){
		int[] newVertices=new int[vertices.length-1];
		TreeSet<Integer> set=new TreeSet<Integer>();
		for(int[] edge:edges){
			set.add(edge[1]); set.add(edge[2]);
		}
		
		int count=0;
		while(!set.isEmpty()){
			int num=set.pollFirst();
			set.remove(num);
			newVertices[count]=num;
			count++;
		}
		
		return newVertices;
		
		
	}
	
	public static Graph<Integer, Integer> convertGraph(int[] vertices, int[][] edges){
		Graph<Integer,Integer> graph=new SparseMultigraph<Integer,Integer>();
		for(int v:vertices){
			graph.addVertex(v);
		}
		
		for(int[] e:edges){
			graph.addEdge(e[0], e[1], e[2], EdgeType.UNDIRECTED);
			
		}
		return graph;
	}
	
	public static void display(int[] vertices, int[][] edges, String color, String name){
		
		Graph<Integer,Integer> g=convertGraph(vertices, edges);
		 
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
