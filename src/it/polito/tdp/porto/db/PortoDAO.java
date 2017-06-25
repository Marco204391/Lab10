package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.AuthorPair;
import it.polito.tdp.porto.model.Paper;

public class PortoDAO {

	public Map<Integer,Author> getAllAuthors(){
		
		Map<Integer, Author> autori = new HashMap<Integer,Author>();
		final String sql = "SELECT * FROM author ";
		
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while(rs.next()){
				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				//autore= authorIdMap.put(autore);
				autori.put(autore.getId(), autore);		
			}
			
			rs.close();//ATTENTOOOOOOOOOOOOOOOOOOOOOO
			conn.close();//ATTENTOOOOOOOOOOOOOOOOOOOOOO
			
			return autori;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	/**public List<Paper> getAllPapers(PaperIdMap paperIdMap){
		
		List<Paper> papers = new ArrayList<Paper>();
		final String sql = "SELECT * FROM paper ";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while(rs.next()){
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				paper= paperIdMap.put(paper);
				papers.add(paper);		
			}
			
			rs.close();//ATTENTOOOOOOOOOOOOOOOOOOOOOO
			conn.close();//ATTENTOOOOOOOOOOOOOOOOOOOOOO
			return papers;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}*/
	
	/*
	 * Dato l'id ottengo l'autore.
	 */
	public Author getAutore(int id) {

		//List<Author> autori = new ArrayList<Author>();
		final String sql = "SELECT * FROM author where id=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				//autore= authorIdMap.put(autore);			
				return autore;
			}

			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Paper getArticolo(int eprintid) {

		//List<Paper> papers = new ArrayList<Paper>();
		final String sql = "SELECT * FROM paper where eprintid=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				//paper= paperIdMap.put(paper);
				return paper;
			}

			return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/**public List<Author> co(Author a1) {
		
		final String sql = "SELECT DISTINCT c2.authorid "+
							"FROM creator as c1, creator as c2 "+
							"WHERE c1.eprintid=c2.eprintid and c1.authorid=? and c2.authorid<> c1.authorid ";
		
		List<Author> coauthors = new ArrayList<Author>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, a1.getId());
						
			ResultSet rs = st.executeQuery();
			
			while (rs.next()){
				int id= rs.getInt("authorid");
				Author autor = this.getAutore(id);
				coauthors.add(autor);

			}
			conn.close();

			return coauthors;
			
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}*/
	
	public List<AuthorPair>listaCoppieAdacenti(){
		  final String sql = "SELECT DISTINCT c1.authorid as a1, c2.authorid as a2 "
		  		+ "FROM creator c1, creator c2 "
		  		+ "WHERE  c1.eprintid=c2.eprintid AND c1.authorid<> c2.authorid";

		  try {
		   Connection conn = DBConnect.getConnection();
		   PreparedStatement st = conn.prepareStatement(sql);
		   

		   
		   List<AuthorPair> coautori= new ArrayList<AuthorPair>();
		   ResultSet rs = st.executeQuery();
		   


		   while (rs.next()) {
		    AuthorPair c= new AuthorPair(rs.getInt("a1"),rs.getInt("a2"));
		    coautori.add(c);  
		    
		   }

		   return coautori;

		  } catch (SQLException e) {
		    e.printStackTrace();
		   throw new RuntimeException("Errore Db");
		  }

		 }
}