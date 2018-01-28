package it.unisa.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import it.unisa.beans.ContentBean;
import it.unisa.beans.WishlistBean;

public class WishlistModelDM implements WishlistModel {

	@Override
	public void doInsert(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String query = "INSERT INTO listadesideri (email_utente) VALUES (?);";
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(query);

			preparedStatement.setString(1, email);
			preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public Collection<ContentBean> doRetriveAll(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<ContentBean> contents = new LinkedList<ContentBean>();

		String query = "SELECT contenuto.* " + "FROM contenuto, listadesideri, riferita_a "
				+ "WHERE listadesideri.email_utente =  ? and contenuto.id = riferita_a.id_contenuto and riferita_a.id_listadesideri = listadesideri.email_utente;";

		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(query);

			preparedStatement.setString(1, email);
			preparedStatement.executeQuery();
			ResultSet rs = preparedStatement.getResultSet();

			if (rs != null) {
				while (rs.next()) {
					ContentBean content = new ContentBean();

					content.setId(rs.getInt("ID"));
					content.setName(rs.getString("NOME"));
					content.setType(rs.getString("TIPO"));
					content.setSize(rs.getInt("DIMENSIONE"));
					content.setVersion(rs.getString("VERSIONE"));
					content.setPrice(rs.getFloat("PREZZO"));
					content.setIcon(rs.getString("ICONA"));
					content.setDescription(rs.getString("DESCRIZIONE"));

					contents.add(content);
				}

				return contents;
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return null;
	}

	@Override
	public void doInsertContent(String email, int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "INSERT INTO riferita_a (id_listadesideri, id_contenuto) VALUES (?,?);";

		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public boolean doRemoveContent(String email, int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM riferita_a WHERE riferita_a.id_contenuto =  ?  AND riferita_a.id_listadesideri =  ? ;";
		int result = 0;

		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, email);
			result = preparedStatement.executeUpdate();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return result != 0;
	}

	@Override
	public boolean doRemoveAllContents(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM riferita_a WHERE riferita_a.id_listadesideri =  ?  ;";
		int result = 0;

		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			result = preparedStatement.executeUpdate();

			if (result > 0) {
				return true;
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return false;
	}
	
	@Override
	public String doRetrieveEmailByWishlist(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "SELECT email_utente FROM listadesideri WHERE email_utente = ? ;";
		
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(query);

			preparedStatement.setString(1, email);
			preparedStatement.executeQuery();
			ResultSet rs = preparedStatement.getResultSet();
			WishlistBean wishlistBean = new WishlistBean();
			if (rs != null) {
				while (rs.next()) {
					wishlistBean = new WishlistBean();
					wishlistBean.setEmail(rs.getString("email_utente"));
				}

				return wishlistBean.getEmail();
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return null;
		
	}
	
	
	@Override
	public boolean doRemoveEmailByWishlist(String email) throws SQLException {
		System.out.println("la mail: "+email);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM listadesideri WHERE email_utente= ?;";
		int result = 0;

		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			result = preparedStatement.executeUpdate();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return result != 0;
	}

}
