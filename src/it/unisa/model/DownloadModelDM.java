package it.unisa.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import it.unisa.beans.ContentBean;
import it.unisa.util.InfoDownload;

public class DownloadModelDM implements DownloadModel {
	
	private Connection connection;
	private PreparedStatement statement;

	@Override
	public void doSave(InfoDownload info) throws SQLException {
		connection= null;
		statement= null;
		String query="INSERT INTO storedb.scarica (email_utente,id_contenuto,data_download) VALUES (?,?,?);";
		try {
			
			connection = DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(query);
			statement.setString(1, info.getEmail());
			statement.setInt(2, info.getContent().getId());
			statement.setString(3, info.getDate());
			statement.executeUpdate();
		} finally {
			try{
				if(statement != null)
					statement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
	
	}

	@Override
	public void doDelete(String email) throws SQLException {
		
		String query="DELETE FROM storedb.scarica WHERE email_utente='"+email+"';";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(query);
		} finally {
			try{
				if(statement != null)
					statement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	
	@Override
	public List<InfoDownload> doRetriveAllByEmail(String email) throws SQLException {
		connection= null;
		statement= null;
		ContentModelDM contentDM = new ContentModelDM();
		List<InfoDownload> contents= new LinkedList<>();
		InfoDownload contentInfo= null;
		
		String selectSQL="SELECT id_contenuto, data_download FROM storedb.scarica WHERE email_utente='"+email+"';";
		
		try{
			connection = DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(selectSQL);
			ResultSet rs = statement.executeQuery();		
			while(rs.next()) {
				contentInfo= new InfoDownload();
				contentInfo.setEmail(email);
				contentInfo.setContent(contentDM.doRetrieveByKey(rs.getInt("id_contenuto")));
				contentInfo.setDate(rs.getString("data_download"));
				contents.add(contentInfo);
			}
			
		}finally {
			try{
				if(statement != null)
					statement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return contents;
		
	}

	@Override
	public ContentBean doRetriveByIdandEmail(String email, int id) throws SQLException {
		connection= null;
		statement= null;
		ContentModelDM contentDM = new ContentModelDM();
		ContentBean content= new ContentBean();
		String query= "SELECT * FROM storedb.scarica WHERE email_utente= ? AND id_contenuto= ?;";
		try{
			connection = DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setInt(2, id);
			ResultSet rs = statement.executeQuery();		
			
			if(rs.next()){
				content= contentDM.doRetrieveByKey(rs.getInt("id_contenuto"));
				return content;
			}else 
				return null;
		}finally {
			try{
				if(statement != null)
					statement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	
	}

	@Override
	public Object[] doRetriveByMostSold() throws SQLException {
		connection= null;
		statement= null;
		List<ContentBean> mostSold= new LinkedList<>();
		List<String> numDownload = new LinkedList<>();
		ContentModelDM contentModel= new ContentModelDM();
		
		String query="SELECT id_contenuto, COUNT(*) AS num_occurrences FROM storedb.scarica GROUP BY id_contenuto ORDER BY num_occurrences DESC;";
		try{
			connection = DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				mostSold.add(contentModel.doRetrieveByKey(rs.getInt("id_contenuto")));
				numDownload.add(rs.getString("num_occurrences"));
			}
			
		}finally {
			try{
				if(statement != null)
					statement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return new Object[]{mostSold, numDownload};	
	}
	
	
	
}
