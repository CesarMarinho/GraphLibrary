import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Graph {
	
	private Vertex[] vertices;
	private int vertexNumber;
	private BufferedReader lerArq;
	
	public Graph(int vertexNumber){
		this.vertexNumber = vertexNumber;
		vertices = new Vertex[vertexNumber];
	}
	
	public Graph(){
		
	}
	
	private void addVertex(int index, Vertex vertex){
		this.vertices[index] = vertex;
	}
	
	private void printGraph(){
		System.out.println("<---Printing the graph--->");
		System.out.println("Number of vertices: " + vertexNumber);
		for(Vertex v: vertices){
			//System.out.println(v);
			v.printNeighbors();
		}
	}
	
	public void readArchive(String archiveName){	//Read archive and initialize the vertices and their related values(weight) 
		try{
			FileReader arq = new FileReader(archiveName);
		    lerArq = new BufferedReader(arq);
		 
		    String linha = lerArq.readLine();
		    String[] fragmented = new String[100]; 
		    int vertexIndex = 0;
		    String vertexName;	
		    Vertex vertex;
		    int numberVertex;
		    int edgeValue;
		    String vertexSplited[];
		    
		    //initializing the vertices array
		    vertices = new Vertex[Integer.parseInt(linha)];
		    vertexNumber = Integer.parseInt(linha);
		    //if(linha == null) System.err.println("Deu erro - pré while");
		      
		    while (linha != null) {				    	
		    	linha = lerArq.readLine();
		    	if(linha == null) break;
		    	//if(linha != null) System.err.println("Deu erro");
		    	//System.out.println("->"+linha);
		    	fragmented = linha.split(" ");
		    	
		    	vertexName = fragmented[0];
		    	vertex = new Vertex(vertexName, vertexIndex);
		    	//adding the neighbors
		    	for(int i=1;i<fragmented.length;i++){
		    		vertexSplited = fragmented[i].split("-");
		    		numberVertex = Integer.parseInt(vertexSplited[0]);
		    		edgeValue = Integer.parseInt(vertexSplited[1]);
		    		vertex.addArch(numberVertex, edgeValue);
		    	}		    	
		    	addVertex(vertexIndex, vertex);
		    	vertexIndex++;
		    	
		    }		 
		} catch(IOException e){
			System.err.printf("Error", e.getMessage());
		}	
	}
	
	public void run(String archName){
		readArchive(archName);
		printGraph();
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public void setVertices(Vertex[] vertices) {
		this.vertices = vertices;
	}

	public int getVertexNumber() {
		return vertexNumber;
	}

	public void setVertexNumber(int vertexNumber) {
		this.vertexNumber = vertexNumber;
	}
		
}
