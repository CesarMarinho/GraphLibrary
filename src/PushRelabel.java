import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PushRelabel {
	
	private int[] height;
	private int[] excess;
	private ArrayList<Vertex> returnFlow;
	private int vertexNumber;
	private int[][] edgesFlow;
	private Vertex source;
	private Vertex sink;
	//private ArrayList<Vertex> edges;
	private List<Vertex> vertices;
	private ArrayList<Vertex> queue;
	private Vertex auxVertex;
	private Graph graph;
	
	public PushRelabel(Graph graph, Vertex source, Vertex sink){
		this.graph = graph;
		vertexNumber = graph.getVertexNumber();
		height = new int[vertexNumber];
		excess = new int[vertexNumber];
		edgesFlow = new int[vertexNumber][vertexNumber];
		this.source = source;
		this.sink = sink;
		//edges = graph.getNeigbors();
		vertices = Arrays.asList(graph.getVertices());
		queue = new ArrayList<Vertex>();
		returnFlow = new ArrayList<Vertex>();
	}	
	
	public void run(){
		
		preflow();			
		
		while(!queue.isEmpty()){			
			auxVertex = queue.get(0);
			if(auxVertex.getNeighbors().isEmpty()){
				System.out.println("Vertice sem arestas de saída");
				queue.remove(0);
				break;
			}
			
			for(int i=0;i<auxVertex.getNeighbors().size();i++){
				Vertex v = auxVertex.getNeighbors().get(i);
				System.out.println("Analisando a situação do vértice: " + auxVertex.getNumberVertex());					
				System.out.println("Para com o vertice: " + v.getNumberVertex());
				if(pushConditions(auxVertex, vertices.get(v.getNumberVertex()))){
					System.out.println("deve fzr push entre "+auxVertex.getNumberVertex()+" e "+v.getNumberVertex());
					push(auxVertex, v);
					if(excess[auxVertex.getNumberVertex()] > 0){
						System.out.println("Removendo vértice: "+queue.get(0));
						queue.remove(0);
					}
				}else{
					System.out.println("Deve fazer relabel entre " + auxVertex.getNumberVertex() + " e "+v.getNumberVertex());
					relabel(auxVertex, v);
					i--;
				}
			}
			if(auxVertex.getNumberVertex() != sink.getNumberVertex()){
				//discharge(auxVertex);
			}			
			printQueue();			
		}
		printMe();	
		int sum = 0;
		for(int i=0;i<vertexNumber;i++){
			sum += edgesFlow[source.getNumberVertex()][i];
		}
		System.out.println("Fluxo Máximo: "+sum);
	}
	
	private void printQueue(){
		System.out.println("Queue: ");
		for(int i=0; i< queue.size();i++){
			System.out.println("----> "+queue.get(i));
		}
	}
	
	private boolean pushConditions(Vertex u, Vertex v){
		if(height[u.getNumberVertex()] == height[v.getNumberVertex()] + 1) return true;
		return false;
	}	
	
	public void push(Vertex u, Vertex v){		
		int uV, vV;
		uV = u.getNumberVertex();
		vV = v.getNumberVertex();
		//System.out.println(vV + "vV >>>>>>>>");
		int dif = Math.min(excess[uV], u.getNeighbors(vV).getEdgeValue() );
		//System.out.println("dif ---------"+dif);
		edgesFlow[uV][vV] += dif;
		edgesFlow[vV][uV] -= dif;
		excess[uV] -= dif;
		excess[vV] += dif;
		if(!queue.contains(vertices.get(vV)) && !(vV == sink.getNumberVertex())){
			queue.add(vertices.get(vV));
		}else{
			System.out.println("Não é possível adicionar o vétice "+ vV);
		}
		System.out.print("Push realizado de "+u.getNumberVertex() + " para " + v.getNumberVertex());
		System.out.println(", enviando "+dif+" de fluxo");
		System.out.println("Excesso do vértice: " + excess[v.getNumberVertex()]);
	}	
	
	public boolean contains(int[] vec){
		for(int i:vec) {
			if(vec[i] == 1) return true;
		}
		return false;
	}
	
	public void discharge(Vertex v){
		if(excess[v.getNumberVertex()] == 0) return;
		ArrayList<Vertex> vPredecessors = this.graph.getPredecessors(v.getNumberVertex());		
		
		while(!returnFlow.isEmpty()){
			Vertex auxVertex = returnFlow.get(0);
			for(Vertex u: vPredecessors){
				relabel(v, u);
				push(u,v);
				queue.remove(queue.size()-1);
			}
			returnFlow.remove(0);			
		}
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
			queue.add(vertices.get(u.getNumberVertex()));
		}
	}
	
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
