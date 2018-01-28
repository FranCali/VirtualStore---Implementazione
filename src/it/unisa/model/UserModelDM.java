package it.unisa.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import it.unisa.beans.UserBean;
import it.unisa.util.Encryptor;

public class UserModelDM implements UserModel {

	@Override
	public void doInsert(UserBean user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO utente (email, nome, cognome, password, è_amministratore) " + "values(?,?,?,?,?);";
		
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(insertSQL);
			
			
			preparedStatement.setString(1,user.getEmail());
			preparedStatement.setString(2,user.getName());
			preparedStatement.setString(3,user.getSurname());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setBoolean(5, user.isAdmin());
			preparedStatement.executeUpdate();
		
		}finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public boolean doDelete(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM storedb.utente WHERE utente.email='"+email+"';";

		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(deleteSQL);
			
			if(preparedStatement.executeUpdate() == 1) 
				return true;
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return false;
	}

	@Override
	public UserBean doRetriveByEmail(String email) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UserBean us = new UserBean();
		
		String selectForEmailSQL = "SELECT email,nome,cognome,password,è_amministratore FROM storedb.utente WHERE utente.email ='"+email+"'"+";";

		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(selectForEmailSQL);
			
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				
				us.setEmail(rs.getString("email"));
				us.setName(rs.getString("nome"));
				us.setSurname(rs.getString("cognome"));
				us.setPassword(rs.getString("password"));
				us.setAdmin(rs.getBoolean("è_amministratore"));
				
				return us;
			}
		}finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return null;
	}

	@Override
	public Collection<UserBean> doRetriveAll(String order) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<UserBean> users = new LinkedList<>();
		
		String selectAllSQL = "SELECT * FROM storedb.utente ";
		
		if(order != null && !order.equals("")) {
			selectAllSQL += "ORDER BY " + order;
		}
		
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(selectAllSQL);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				UserBean us = new UserBean();
				us.setEmail(rs.getString("email"));
				us.setName(rs.getString("nome"));
				us.setSurname(rs.getString("cognome"));
				us.setAdmin(rs.getBoolean("è_amministratore"));
				users.add(us);
			}
		}finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);

			}
		}
		return users;
	}

	
	@Override
	public boolean doUpdatePassword(String email, String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String query="UPDATE utente SET password= ? WHERE email= ?;";

		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setString(1,Encryptor.encryptPassword(password));
			preparedStatement.setString(2, email);
			if(preparedStatement.executeUpdate()>0)
				return true;
		}finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
		return false;
	}


}
