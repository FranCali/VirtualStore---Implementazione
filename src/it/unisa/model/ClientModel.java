package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.beans.ClientBean;

public interface ClientModel {

	public void doInsert(ClientBean client) throws SQLException;

	public void doInsertDeleteAccountRequest(ClientBean client, String date) throws SQLException;
	
	public boolean hadRequestedDeletion(ClientBean client) throws SQLException;
	
	public boolean doDelete(ClientBean client) throws SQLException;

	public ClientBean doRetriveByEmail(String email) throws SQLException;

	public Collection<ClientBean> doRetriveAll(String order) throws SQLException;
	
	public boolean checkIfAdmin(ClientBean client) throws SQLException;

	public boolean checkIfManager(ClientBean client) throws SQLException;
}
