package it.unisa.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import it.unisa.beans.AccountBean;
import it.unisa.beans.ClientBean;

public class ClientModelDM implements ClientModel {

	@Override
	public void doInsert(ClientBean client) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		AccountModelDM accountModelDM = new AccountModelDM();
		String insertSQL = "INSERT INTO cliente (nome, cognome) " + "values(?,?);";

		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, client.getName());
			preparedStatement.setString(2, client.getSurname());
			preparedStatement.executeUpdate();
			ResultSet rs = connection.createStatement()
					.executeQuery("SELECT id FROM storedb.cliente ORDER BY id DESC LIMIT 1;");
			Integer idLastClientInDB = null;
			if (rs.next())
				idLastClientInDB = rs.getInt(1);
			accountModelDM.doInsert(new AccountBean(client.getAccount().getEmail(), client.getAccount().getPassword(),
					idLastClientInDB));

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
	public boolean doDelete(ClientBean client) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM storedb.cliente WHERE cliente.id='"+client.getId()+"';";

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
	public ClientBean doRetriveByEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		AccountModelDM accountModelDM = new AccountModelDM();

		AccountBean account = accountModelDM.doRetrieveByEmail(email);
		if (account != null) {
			String selectForEmailSQL = "SELECT nome,cognome,id FROM cliente, account WHERE id ='" + account.getClientId()
					+ "'" + ";";
			ClientBean client = new ClientBean();
			try {
				connection = (Connection) DriverManagerConnectionPool.getConnection();
				preparedStatement = (PreparedStatement) connection.prepareStatement(selectForEmailSQL);

				ResultSet rs = preparedStatement.executeQuery();

				if (rs.next()) {
					client.setName(rs.getString("nome"));
					client.setSurname(rs.getString("cognome"));
					client.setId(rs.getInt("id"));
					client.setAccount(account);

					return client;
				}
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		}
		return null;
	}

	@Override
	public Collection<ClientBean> doRetriveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<ClientBean> users = new LinkedList<>();
		
		String selectAllSQL = "SELECT id,nome,cognome,email,password FROM cliente, account WHERE id = idcliente ";
		
		if(order != null && !order.equals("")) {
			selectAllSQL += "ORDER BY " + order + ";";
		}else
			selectAllSQL += ";";
		
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(selectAllSQL);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				AccountBean account = new AccountBean();
				account.setEmail(rs.getString("email"));
				account.setPassword(rs.getString("password"));
				account.setClientId(rs.getInt("id"));
				ClientBean client = new ClientBean();
				client.setName(rs.getString("nome"));
				client.setSurname(rs.getString("cognome"));
				client.setAccount(account);
				users.add(client);
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
	public boolean checkIfAdmin(ClientBean client) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "SELECT * FROM admin WHERE admin.idcliente=" + client.getAccount().getClientId() + ";";

		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();

			ResultSet rs = connection.createStatement().executeQuery(insertSQL);
			if (rs.next())
				return true;
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
	public boolean checkIfManager(ClientBean client) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "SELECT * FROM admin,manager WHERE manager.idcliente=" + client.getAccount().getClientId() + ";";

		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();

			ResultSet rs = connection.createStatement().executeQuery(insertSQL);
			if (rs.next())
				return true;
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
	public void doInsertDeleteAccountRequest(ClientBean client) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO rimozione_account (email_cliente) " + client.getAccount().getEmail() +";";
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			connection.createStatement().executeQuery(insertSQL);
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
	}

}
