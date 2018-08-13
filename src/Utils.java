import java.util.ArrayList;

public class Utils {
	public static void main(String[] args) {
		String path = "C:/Users/Jonathan/git/GraphLibrary/Graph";
		Graph g = new Graph();
		g.run(path);
	}
	
	public int pushRebavel(Graph g, int n){
		int maxFlow = 0;
		
		Vertex[] vert = g.getVertices();
		int source = vert[n].getNumberVertex();  //no origem não tenho certeza se entendi
		int slink = vert[n+1].getNumberVertex(); //no destino "    "      "     "   "
		ArrayList<Integer> array = new ArrayList<Integer>();
		for(int i=0;i < g.getVertexNumber();i++){
			if((i!= source) && (i!= slink)){
				array.add(i);
			}
			
		}
		
		return maxFlow;
	}
}


