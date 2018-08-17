import java.util.ArrayList;

public class Utils {

	public static void main(String[] args) {
		String path = "C:/Users/Jonathan/git/GraphLibrary/GraphTest";
		Graph g = new Graph();
		g.run(path);
		//new PushRelabel(g,g.getVertex(0), g.getVertex(5)).runA();;
		//functionDoida(g, 0);
		
		System.out.println(functionDoida(g, 0));
	}
	public static boolean functionDoida(Graph g, int source){
		int sink = 5;
		Vertex initial = g.getVertex(source);
		//verifica se algum dos primeiros vizinhos é o destino
		for(Vertex v: initial.getNeighbors()){
			if(v.getNumberVertex() == sink){
				return true;
			}
			System.out.println(v.getNumberVertex());
		}
		//chamando recursivamente
		for(Vertex v: initial.getNeighbors()){
			if(functionDoida(g, v.getNumberVertex())){
				return true;
			}
		}

		return false;
	}
}


