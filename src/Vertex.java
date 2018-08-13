import java.util.ArrayList;

public class Vertex {
	
	private String nameVertex;
	private int numberVertex;
	private int edgeValue;
	private ArrayList<Vertex> neighbors;
	private int fluxoParcial;
	
	public Vertex(int numberVertex){
		this.numberVertex = numberVertex;
		this.neighbors = new ArrayList<Vertex>();
		this.fluxoParcial = 0;
	}
	
	public Vertex(String nameVertex, int numberVertex){
		this.nameVertex = nameVertex;
		this.numberVertex = numberVertex;
		this.neighbors = new ArrayList<Vertex>();
	}
	
	private Vertex(int numberVertex, int edgeValue){
		this.numberVertex = numberVertex;
		this.edgeValue = edgeValue;
		this.neighbors = new ArrayList<Vertex>();
	}
	
	public void addArch(int numberVertex, int edgeValue){
		this.neighbors.add(new Vertex(numberVertex, edgeValue));
	}
	
	public void addArch(int numberVertex){
		this.neighbors.add(new Vertex(numberVertex));
	}
	
	public boolean existsArch(int numberVertexB){
		for(Vertex v : this.neighbors){
			if(v.numberVertex == numberVertexB)
				return true;
		}
		return false;
	}	
	
	//getters and setters
	public String getNameVertex() {
		return nameVertex;
	}

	public void setNameVertex(String nameVertex) {
		this.nameVertex = nameVertex;
	}

	public int getNumberVertex() {
		return numberVertex;
	}

	public void setNumberVertex(int numberVertex) {
		this.numberVertex = numberVertex;
	}

	public int getEdgeValue() {
		return edgeValue;
	}

	public void setEdgeValue(int edgeValue) {
		this.edgeValue = edgeValue;
	}

	public ArrayList<Vertex> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(ArrayList<Vertex> neighbors) {
		this.neighbors = neighbors;
	}

	
	public int getFluxoParcial() {
		return fluxoParcial;
	}

	public void setFluxoParcial(int fluxoParcial) {
		this.fluxoParcial = fluxoParcial;
	}

	@Override
	public String toString() {
		return "Vertex [nameVertex=" + nameVertex + ", numberVertex=" + numberVertex + "]";
	}	
	
	public void printNeighbors(){
		System.out.println("Neighbors from vertex: " + this.nameVertex + ", numberVertex: " + this.numberVertex);
		for(Vertex v: neighbors){
			System.out.println("Vertex: "+ v.numberVertex +", Name vertex: "+ v.nameVertex +", Edge value: "+v.edgeValue);
		}
	}

}
