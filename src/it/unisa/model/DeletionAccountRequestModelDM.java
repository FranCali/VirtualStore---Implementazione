package it.unisa.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import it.unisa.beans.DeletionAccountRequest;

public class DeletionAccountRequestModelDM implements DeletionAccountRequestModel {

	@Override
	public Collection<DeletionAccountRequest> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<DeletionAccountRequest> requests = new LinkedList<>();

		String selectAllSQL = "SELECT email_cliente, data_richiesta FROM rimozione_account";

		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(selectAllSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				DeletionAccountRequest request = new DeletionAccountRequest();
				request.setClientEmail(rs.getString("email_cliente"));
				request.setRequestDate(rs.getString("data_richiesta"));

				requests.add(request);
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return requests;
	}

	@Override
	public boolean doDelete(DeletionAccountRequest request) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectAllSQL = "DELETE FROM rimozione_account WHERE email_cliente = '" + request.getClientEmail() + "';";

		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(selectAllSQL);
		
			if(preparedStatement.executeUpdate() == 1)
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
	public void doInsert(DeletionAccountRequest request) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO rimozione_account (email_cliente, data_richiesta) values('"
				+ request.getClientEmail() + "', '" + request.getRequestDate() + "');";
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			connection.createStatement().execute(insertSQL);
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
	public DeletionAccountRequest doRetrieveByEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM rimozione_account WHERE email_cliente = '" + email + "';" + "";
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery(selectSQL);
			DeletionAccountRequest accountRequest = new DeletionAccountRequest();
			if(rs.next()) {
				accountRequest.setClientEmail(rs.getString("email_cliente"));
				accountRequest.setRequestDate(rs.getString("data_richiesta"));
				return accountRequest;
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

}
