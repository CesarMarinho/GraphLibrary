import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PushRelabel {
	
	private int[] height;
	private int[] excess;
	private int vertexNumber;
	private int[][] edgesFlow;
	private Vertex source;
	private Vertex sink;
	private ArrayList<Vertex> edges;
	private List<Vertex> vertices;
	
	public PushRelabel(Graph graph, Vertex source, Vertex sink){
		vertexNumber = graph.getVertexNumber();
		height = new int[vertexNumber];
		excess = new int[vertexNumber];
		edgesFlow = new int[vertexNumber][vertexNumber];
		this.source = source;
		this.sink = sink;
		edges = graph.getNeigbors();
		vertices = Arrays.asList(graph.getVertices());
		
	}
	
	public void runA(){
		preflow();
		for(Vertex v: vertices) System.out.println(v.getNameVertex());
		printMe();
		push(vertices.get(1), vertices.get(2));
		printMe();
		push(vertices.get(2), vertices.get(4));
		printMe();
		push(vertices.get(4), vertices.get(5));
	}
	
	public void push(Vertex u, Vertex v){
		int uV, vV;
		uV = u.getNumberVertex();
		vV = v.getNumberVertex();
		//System.out.println(vV + "vV >>>>>>>>");
		int dif = Math.min(excess[uV], u.getNeighbors(vV).getEdgeValue() );
		System.out.println("dif ---------"+dif);
		edgesFlow[uV][vV] += dif;
		edgesFlow[vV][uV] -= dif;
		excess[uV] -= dif;
		excess[vV] += dif;
	}
	
	
	public void run(){
		preflow();
		Vertex v;
		int uV, vV;
		int maxFlow = 0;
		while(excess[source.getNumberVertex()] > excess[sink.getNumberVertex()]) { //verificar condição deste while	
		//while(contains){
			for(Vertex u: vertices) {
				//System.out.println("Entrou no for");
				if(!(u.equals(source)) || !(u.equals(sink))) 
				{
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
		}
		
		for(int i = 0; i<vertexNumber;i++) {
			maxFlow += edgesFlow[source.getNumberVertex()][i];
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
		System.out.println("Entrou no preflow");
		for(int i=0; i<vertexNumber;i++){
			for(int j=0; j<vertexNumber;j++){
				edgesFlow[i][j] = 0;
			}
			excess[i] = 0;
			height[i] = 0;
		}
		height[source.getNumberVertex()] = vertexNumber;
		excess[source.getNumberVertex()] = 1000;//o valor deveria ser infinito
		for(Vertex u : source.getNeighbors()){
			excess[u.getNumberVertex()] = u.getEdgeValue();
			edgesFlow[source.getNumberVertex()][u.getNumberVertex()] = u.getEdgeValue();			
		}
//		for(int i=0;i<source.getNeighbors().size();i++){
//			excess[source.getNeighbors().get(i).getNumberVertex()] = source;
//		}
	}
	
//	public void push(Vertex u, Vertex v){
//		System.out.println("Entrou no push");
//		int uV = u.getNumberVertex(); //uVertex
//		int vV = v.getNumberVertex(); //vVertex
//		int difFlow = Math.min(excess[uV], edgesFlow[uV][vV]);
//		edgesFlow[uV][vV] += difFlow;
//		edgesFlow[vV][uV] -= difFlow;
//		excess[uV] -= difFlow;
//		excess[vV] += difFlow;
//	}
	
	public void relabel(Vertex u, Vertex v){
		height[u.getNumberVertex()] = 1 + height[v.getNumberVertex()];
		System.out.println("Entrou no relabel");
	}
	
	public void printMe(){
		for(int i=0;i<vertexNumber;i++){
			System.out.println("Vertice: "+i);
			System.out.println("Altura: "+height[i]);
			System.out.println("Excess: "+excess[i]);
			System.out.println();
		}
	}

}
