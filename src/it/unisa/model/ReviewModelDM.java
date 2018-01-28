package it.unisa.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import com.mysql.jdbc.PreparedStatement;

import it.unisa.beans.ContentBean;
import it.unisa.beans.ReviewBean;

public class ReviewModelDM implements ReviewModel{
	
	private final String TABLE_NAME = "recensione";
	
	@Override
	public Collection<ReviewBean> doRetrieveByContentId(int content_id) throws SQLException {
		
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE recensione.id_contenuto = " + content_id + ";";

		Collection<ReviewBean> reviews = new LinkedList<ReviewBean>();
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			
			statement = (PreparedStatement) connection.prepareStatement(selectSQL);
			

			ResultSet rs = statement.executeQuery();
			
			while(rs.next()){
				ReviewBean review = new ReviewBean();
				
				review.setContent_id(rs.getInt("id_contenuto"));
				review.setDescription(rs.getString("descrizione"));
				review.setReview_date(rs.getDate("data_recensione").toString());
				review.setTitle(rs.getString("titolo"));
				review.setUser_email(rs.getString("email_utente"));
				review.setRating(rs.getInt("valutazione"));
				
				reviews.add(review);
			}
			
		} finally {
			try{
				if(statement != null)
					statement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return reviews;
	}

	@Override
	public boolean doSave(ReviewBean review) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		String insertSQL = "INSERT INTO "+ TABLE_NAME + " VALUES('" + review.getUser_email() + "'," + review.getContent_id() + ",'" + review.getTitle() + 
				
																	"','" + review.getDescription() + "','" + review.getReview_date() + "'," + review.getRating() + ");";
		
		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			
			statement = (PreparedStatement) connection.prepareStatement(insertSQL);
			
			if(statement.executeUpdate() == 1)
				return true;
			
		} finally {
			try{
				if(statement != null)
					statement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return false;
	}

	@Override
	public boolean doDelete(ReviewBean review) throws SQLException {
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		String deleteSQL= "DELETE FROM recensione WHERE email_utente= ? AND id_contenuto= ?;";
		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			
			statement = (PreparedStatement) connection.prepareStatement(deleteSQL);
			statement.setString(1, review.getUser_email());
			statement.setInt(2, review.getContent_id());
			if(statement.executeUpdate() == 1)
				return true;
			
		} finally {
			try{
				if(statement != null)
					statement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return false;
	}

	
	
	//Controllo se L'utente ha già fatto una recensione per quel contenuto
	@Override
	public ReviewBean doRetrieveByContentIdandUserEmail(int content_id, String user_email) throws SQLException {
		
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE recensione.id_contenuto = " + content_id + " AND recensione.email_utente = '" + user_email + "';";

		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			
			statement = (PreparedStatement) connection.prepareStatement(selectSQL);
			

			ResultSet rs = statement.executeQuery();
			
			if(rs.next()){
				ReviewBean review = new ReviewBean(); 
				
				review.setContent_id(rs.getInt("id_contenuto"));
				review.setDescription(rs.getString("descrizione"));
				review.setReview_date(rs.getDate("data_recensione").toString());
				review.setTitle(rs.getString("titolo"));
				review.setUser_email(rs.getString("email_utente"));
				review.setRating(rs.getInt("valutazione"));
				
				return review;
			}
			
		} finally {
			try{
				if(statement != null)
					statement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return null;
	}

	@Override
	public Object[] doRetriveAllByAverage() throws SQLException {
		ContentModelDM contentModel= new ContentModelDM();
		Connection connection = null;
		PreparedStatement statement = null;
		LinkedList<ContentBean> mostReviewed = new LinkedList<>();
		LinkedList<Float> average = new LinkedList<>();
		String selectSQL = "SELECT id_contenuto, AVG(valutazione) AS average FROM recensione group by id_contenuto ORDER BY average DESC;";

		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(selectSQL);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()){
				mostReviewed.add(contentModel.doRetrieveByKey(rs.getInt("id_contenuto")));
				average.add(rs.getFloat("average"));
			}
			
		} finally {
			try{
				if(statement != null)
					statement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return new Object[]{mostReviewed, average};
	}

	@Override
	public boolean doUpdate(ReviewBean review) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		String insertSQL = "UPDATE "+ TABLE_NAME+" SET titolo= ?, descrizione= ?, data_recensione= ?, valutazione= ? WHERE email_utente= ? AND id_contenuto= ?";
		
		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			
			statement = (PreparedStatement) connection.prepareStatement(insertSQL);
			statement.setString(1, review.getTitle());
			statement.setString(2, review.getDescription());
			statement.setString(3, review.getReview_date());
			statement.setInt(4, review.getRating());
			statement.setString(5, review.getUser_email());
			statement.setInt(6, review.getContent_id());
			if(statement.executeUpdate() == 1)
				return true;
			
		} finally {
			try{
				if(statement != null)
					statement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return false;
	}

}
