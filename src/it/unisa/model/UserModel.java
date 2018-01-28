package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.beans.UserBean;

public interface UserModel {
	
	public void doInsert(UserBean user) throws SQLException;
	
	public boolean doUpdatePassword(String email, String password) throws SQLException;
	
	public boolean doDelete(String email) throws SQLException;
	
	public UserBean doRetriveByEmail(String email) throws SQLException; 
	
	public Collection<UserBean> doRetriveAll(String order) throws SQLException;
}
