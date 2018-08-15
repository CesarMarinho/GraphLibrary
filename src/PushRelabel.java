import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PushRelabel {
	
	private int[] height;
	private int[] excess;
	private int vertexNumber;
	private int[][] edgesFlux;
	private Vertex source;
	private Vertex sink;
	private ArrayList<Vertex> edges;
	private List<Vertex> vertices;
	
	public PushRelabel(Graph graph, Vertex source, Vertex sink){
		vertexNumber = graph.getVertexNumber();
		height = new int[vertexNumber];
		excess = new int[vertexNumber];
		edgesFlux = new int[vertexNumber][vertexNumber];
		this.source = source;
		this.sink = sink;
		edges = graph.getNeigbors();
		vertices = Arrays.asList(graph.getVertices());
		
	}
	
	public void run(){
		
	}
	
	public void preflow(){
		for(int i=0; i<vertexNumber;i++){
			for(int j=0; j<vertexNumber;j++){
				edgesFlux[i][j] = 0;
			}
			excess[i] = 0;
			height[i] = 0;
		}
		height[source.getNumberVertex()] = vertexNumber;
		
	}
	
	public void push(Vertex u, Vertex v){
		int uV = u.getNumberVertex(); //uVertex
		int vV = v.getNumberVertex(); //vVertex
		int difFlow = Math.min(excess[uV], edgesFlux[uV][vV]);
		edgesFlux[uV][vV] += difFlow;
		edgesFlux[vV][uV] -= difFlow;
		excess[uV] -= difFlow;
		excess[vV] += difFlow;
	}
	
	public void relabel(Vertex u){
		
	}

}
