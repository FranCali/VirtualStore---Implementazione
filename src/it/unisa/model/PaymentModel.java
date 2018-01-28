package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.beans.PaymentBean;

public interface PaymentModel {
	
public void doInsert(PaymentBean method) throws SQLException;
	
	public boolean doDelete(String email,String id) throws SQLException;
	
	public PaymentBean doRetriveByEmail(String email) throws SQLException; 
	
	public Collection<PaymentBean> doRetriveAll(String order) throws SQLException;
}
