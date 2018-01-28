package it.unisa.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import it.unisa.beans.PaymentBean;

public class PaymentModelDM implements PaymentModel{

	@Override
	public  void doInsert(PaymentBean method) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String paySQL = "INSERT INTO metodo_di_pagamento (email_utente,identificativo,codice_sicurezza,data_scadenza) "
						+ "values('"+method.getEmail()+"','"+method.getIdentifier()+"','"+method.getSecurityCode()+"','"+method.getExpireDate()+"');";

		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(paySQL);
			
			preparedStatement.executeUpdate();
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public boolean doDelete(String email,String id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String delSQL = "DELETE FROM metodo_di_pagamento WHERE email_utente = '"+email+"' AND identificativo ='"+id+"';";
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(delSQL);
			
			if(preparedStatement.executeUpdate() >0 )
				return true;
			else
				return false;
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public PaymentBean doRetriveByEmail(String email) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PaymentBean pb = null;
		
		String SelEmailSQL = "SELECT email_utente, identificativo, codice_sicurezza, data_scadenza FROM metodo_di_pagamento WHERE email_utente ='"+email+"';";
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(SelEmailSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			if(rs.next()) {
			
				pb = new PaymentBean();
				
				pb.setEmail(rs.getString("email_utente"));
				pb.setIdentifier(rs.getString("identificativo"));
				pb.setSecurityCode(rs.getString("codice_sicurezza"));
				pb.setExpireDate(rs.getDate("data_scadenza").toString());
			}	
			
		}finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}finally{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return pb;

	}

	@Override
	public Collection<PaymentBean> doRetriveAll(String order) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<PaymentBean> paymentmethods = new LinkedList<>();
		String SelAllSQL = "SELECT * FROM metodo_di_pagamento;";
		
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(SelAllSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				PaymentBean pb = new PaymentBean();
				pb.setEmail(rs.getString("email_utente"));
				pb.setIdentifier(rs.getString("identificativo"));
				pb.setSecurityCode(rs.getString("codice_sicurezza"));
				pb.setExpireDate(rs.getDate("data_scadenza").toString());
				
				paymentmethods.add(pb);
			}
			
			
		}finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}finally{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return paymentmethods;
	}
	
}
