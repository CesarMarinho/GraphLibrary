
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String path = "C:/Users/Cesar/git/GraphLibrary/GraphTest";		
		String path = "C:/Users/cesar/Documents/projetos/sDizimo/Push-Relabel/Graph2";
		Graph g = new Graph();
		g.run(path);
		new PushRelabel(g,g.getVertex(0), g.getVertex(6)).run();;
	}

}
