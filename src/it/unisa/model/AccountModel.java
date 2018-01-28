package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.beans.AccountBean;

public interface AccountModel {

	public void doInsert(AccountBean account) throws SQLException;

	public boolean doUpdatePassword(AccountBean account) throws SQLException;

	public boolean doDelete(AccountBean account) throws SQLException;

	public AccountBean doRetrieveByEmail(String email) throws SQLException;

	public Collection<AccountBean> doRetrieveAll(String order) throws SQLException;
}
