package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;
import it.unisa.beans.ContentBean;

public interface ContentModel {

	public Collection<ContentBean> doRetrieveAll(String order) throws SQLException;

	public Collection<ContentBean> doSearch(String substring, String order) throws SQLException;

	public ContentBean doRetrieveByKey(int id) throws SQLException;

	public Collection<ContentBean> doRetrieveByType(String type, String order) throws SQLException;

	public Collection<ContentBean> doRetrieveByPrice(float price) throws SQLException;

	public Collection<ContentBean> doRetrieveByCovers() throws SQLException;

	public boolean doSave(ContentBean content) throws SQLException;

	public boolean doDelete(ContentBean content) throws SQLException;

}
