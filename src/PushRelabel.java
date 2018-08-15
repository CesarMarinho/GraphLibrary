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
		preflow();
		Vertex v;
		int uV, vV;
		int maxFlow = 0;
		while(excess[source.getNumberVertex()] > excess[sink.getNumberVertex()]) { //verificar condição deste while
			for(Vertex u: vertices) {				
				uV = u.getNumberVertex();
				for(int i=0; i<u.getNeighbors().size();i++) {
					v = u.getNeighbors().get(i);
					vV = v.getNumberVertex();
					if((excess[uV] > 0) && (u.getEdgeValue(v) > 0) && (height[uV] == height[vV] + 1)) {
						push(u, v);
					}
					if(height[uV] <= height[vV]) {
						relabel(u,v);
					}
				}
			}
		}
		
		for(int i = 0; i<vertexNumber;i++) {
			maxFlow += edgesFlux[source.getNumberVertex()][i];
		}
		System.out.println(maxFlow);		
	}
	
	public boolean contains(int[] vec){
		for(int i:vec) {
			if(vec[i] == 1) return true;
		}
		return false;
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
	
	public void relabel(Vertex u, Vertex v){
		height[u.getNumberVertex()] = 1 + height[v.getNumberVertex()];
	}
	

}
