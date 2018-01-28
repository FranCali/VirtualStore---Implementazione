package it.unisa.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import it.unisa.beans.AccountBean;
import it.unisa.util.Encryptor;

public class AccountModelDM implements AccountModel {

	@Override
	public void doInsert(AccountBean account) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO account (email, password, idcliente) " + "values(?,?,?);";
		
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(insertSQL);
			
			
			preparedStatement.setString(1, account.getEmail());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setInt(3, account.getClientId());
			
			
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
	public boolean doUpdatePassword(AccountBean account) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE account SET password = ? WHERE email = ?"; 
				
		
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(updateSQL);
			
			
			preparedStatement.setString(1, Encryptor.encryptPassword(account.getPassword()));
			preparedStatement.setString(2, account.getEmail());
			
			preparedStatement.executeUpdate();
		
		}finally {
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
	public AccountBean doRetrieveByEmail(String email) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		AccountBean account = new AccountBean();
		
		String selectForEmailSQL = "SELECT * FROM account WHERE account.email ='"+email+"'"+";";

		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(selectForEmailSQL);
			
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				
				account.setEmail(rs.getString("email"));
				account.setPassword(rs.getString("password"));
				account.setClientId(rs.getInt("idcliente"));
				return account;
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
	public Collection<AccountBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<AccountBean> accounts = new LinkedList<>();
		
		String selectAllSQL = "SELECT * FROM storedb.account ";
		
		if(order != null && !order.equals("")) {
			selectAllSQL += "ORDER BY " + order;
		}
		
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(selectAllSQL);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				AccountBean account = new AccountBean();
				account.setEmail(rs.getString("email"));
				account.setPassword(rs.getString("password"));
				accounts.add(account);
			}
		}finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);

			}
		}
		return accounts;
	}

	@Override
	public boolean doDelete(AccountBean account) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}



}
