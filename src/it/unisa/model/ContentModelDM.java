package it.unisa.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import com.mysql.jdbc.PreparedStatement;

import it.unisa.beans.ContentBean;

public class ContentModelDM implements ContentModel {

	private static final String TABLE_NAME = "contenuto";

	@Override
	public Collection<ContentBean> doRetrieveAll(String order) throws SQLException {

		String selectSQL = "SELECT * FROM " + TABLE_NAME;
		Collection<ContentBean> contents = new LinkedList<ContentBean>();
		Connection connection = null;
		PreparedStatement statement = null;

		if (order != null && !order.equals(""))
			selectSQL += " ORDER BY " + order;

		try {

			connection = DriverManagerConnectionPool.getConnection();

			statement = (PreparedStatement) connection.prepareStatement(selectSQL);

			ResultSet rs = statement.executeQuery();

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

		} finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return contents;
	}

	@Override
	public ContentBean doRetrieveByKey(int id) throws SQLException {

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE contenuto.id = " + id + ";";
		Connection connection = null;
		PreparedStatement statement = null;
		ContentBean content = new ContentBean();
		;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(selectSQL);
			// Devo controllare l'id
			ResultSet rs = statement.executeQuery();
			rs.next();
			if (rs != null) {
				content.setId(rs.getInt("ID"));
				content.setName(rs.getString("NOME"));
				content.setType(rs.getString("TIPO"));
				content.setSize(rs.getInt("DIMENSIONE"));
				content.setVersion(rs.getString("VERSIONE"));
				content.setPrice(rs.getFloat("PREZZO"));
				content.setIcon(rs.getString("ICONA"));
				content.setDescription(rs.getString("DESCRIZIONE"));

			}

		} finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return content;
	}

	@Override
	public Collection<ContentBean> doRetrieveByType(String type, String order) throws SQLException {

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE contenuto.tipo = '" + type + "'";
		Collection<ContentBean> contents = new LinkedList<ContentBean>();
		Connection connection = null;
		PreparedStatement statement = null;

		if (order != null)
			selectSQL += " ORDER BY " + order + ";";
		else
			selectSQL += ";";

		try {

			connection = DriverManagerConnectionPool.getConnection();

			statement = (PreparedStatement) connection.prepareStatement(selectSQL);

			ResultSet rs = statement.executeQuery();

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

		} finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return contents;
	}

	@Override
	public Collection<ContentBean> doSearch(String substring, String order) throws SQLException {
		String str = substring.replace("'", "\\'");
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE nome LIKE '%" + str + "%'";

		Collection<ContentBean> contents = new LinkedList<ContentBean>();
		Connection connection = null;
		PreparedStatement statement = null;

		if (order != null && !order.equals(""))
			selectSQL += " ORDER BY " + order + ";";
		else {
			selectSQL += ";";
		}

		try {

			connection = DriverManagerConnectionPool.getConnection();

			statement = (PreparedStatement) connection.prepareStatement(selectSQL);

			ResultSet rs = statement.executeQuery();

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

		} finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return contents;
	}

	@Override
	public boolean doSave(ContentBean content) throws SQLException {
		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (tipo, nome, dimensione, piva_azienda_fornitrice, icona, descrizione, prezzo)" + " values('"
				+ content.getType() + "','" + content.getName() + "'," + content.getSize() + ",'" + "','"
				+ content.getIcon() + "','" + content.getDescription() + "'," + content.getPrice() + ");";

		Connection connection = null;
		PreparedStatement statement = null;

		try {

			connection = DriverManagerConnectionPool.getConnection();

			statement = (PreparedStatement) connection.prepareStatement(insertSQL);

			int result = statement.executeUpdate();

			if (result > 0)
				return true;

		} finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return false;
	}

	@Override
	public boolean doDelete(ContentBean content) throws SQLException {
		int id = content.getId();
		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE id = " + id + ";";
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(deleteSQL);

			int result = statement.executeUpdate();

			if (result > 0)
				return true;

		} finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return false;

	}

	@Override
	public Collection<ContentBean> doRetrieveByCovers() throws SQLException {
		Collection<ContentBean> contents = new LinkedList<ContentBean>();
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE cover <> '';";
		Connection connection = null;
		PreparedStatement statement = null;

		try {

			connection = DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(selectSQL);

			ResultSet rs = statement.executeQuery();

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
				content.setCover(rs.getString("COVER"));

				contents.add(content);
			}

		} finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return contents;
	}

	@Override
	public Collection<ContentBean> doRetrieveByPrice(float price) throws SQLException {
		Collection<ContentBean> contents = new LinkedList<ContentBean>();
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE prezzo > " + price + ";";
		Connection connection = null;
		PreparedStatement statement = null;

		try {

			connection = DriverManagerConnectionPool.getConnection();
			statement = (PreparedStatement) connection.prepareStatement(selectSQL);

			ResultSet rs = statement.executeQuery();

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
				content.setCover(rs.getString("COVER"));

				contents.add(content);
			}

		} finally {
			try {
				if (statement != null)
					statement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return contents;
	}

}
