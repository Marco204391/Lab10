package it.polito.tdp.porto.model;

import java.util.Map;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	private UndirectedGraph<Author, DefaultEdge> graph;
	private Map<Integer, Author> authorMap;
	
	
	public Model() {
		PortoDAO dao = new PortoDAO();
		this.authorMap= dao.getAllAuthors();
	}

	
	/**
	 * @return the authorMap
	 */
	public Map<Integer, Author> getAuthorMap() {
		return authorMap;
	}


	public void createGraph(){
		graph=new SimpleGraph<Author,DefaultEdge>(DefaultEdge.class);
		PortoDAO dao = new PortoDAO();
//		for(Author a : dao.getAllAuthors()){
//			if(!graph.containsVertex(a)){
//				graph.addVertex(a);
//			}
//		}
		Graphs.addAllVertices(graph,authorMap.values());
		for(AuthorPair ap:dao.listaCoppieAdacenti()){
			   Author a1=authorMap.get(ap.getAuthor1());
			   Author a2=authorMap.get(ap.getAuthor2());
			            graph.addEdge(a1,a2);
			  }
			  
		
//	for(Author vertex : graph.vertexSet()){
//			System.out.println("vertex :"+vertex+" \n");
//		}
	
	/**	for(Author a1: graph.vertexSet()) {
					for(Author coAuthor:  dao.co(a1)){
						if(!graph.containsEdge(a1, coAuthor))
							graph.addEdge(a1, coAuthor);
					}
		}
		*/
//		System.out.println(graph.toString());
		
		
		
		
//		for(Creator c1: dao.getCoAuthor()) {
//			for(Creator c2: dao.getCoAuthor()) {
//				if(c1.getPaper().equals(c2.getPaper())) {
//					if(!c1.getAutore().equals(c2.getAutore()))
//						graph.addEdge(c1.getAutore(), c2.getAutore()) ;
//				
//				}
//			}
//		}

	}
}
