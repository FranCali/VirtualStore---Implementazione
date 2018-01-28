package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.beans.ContentBean;

public interface WishlistModel {

	public void doInsert(String email) throws SQLException;

	public Collection<ContentBean> doRetriveAll(String email) throws SQLException;

	public void doInsertContent(String email, int id) throws SQLException;

	public boolean doRemoveContent(String email, int id) throws SQLException;

	public boolean doRemoveAllContents(String email) throws SQLException;

	public String doRetrieveEmailByWishlist(String email) throws SQLException;
	
	public boolean doRemoveEmailByWishlist(String email) throws SQLException;


}
