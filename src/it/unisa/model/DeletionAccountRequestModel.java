package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.beans.DeletionAccountRequest;

public interface DeletionAccountRequestModel {

	public Collection<DeletionAccountRequest> doRetrieveAll() throws SQLException;
	public boolean doDelete(DeletionAccountRequest request) throws SQLException;
	public void doInsert(DeletionAccountRequest request) throws SQLException;
	public DeletionAccountRequest doRetrieveByEmail(String email) throws SQLException;
}
