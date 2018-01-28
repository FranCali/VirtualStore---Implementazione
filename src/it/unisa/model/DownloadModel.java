package it.unisa.model;

import java.sql.SQLException;
import java.util.List;

import it.unisa.beans.ContentBean;
import it.unisa.util.InfoDownload;

public interface DownloadModel {

	
	public void doSave(InfoDownload info) throws SQLException;
	
	public void doDelete(String email) throws SQLException;
	
	public ContentBean doRetriveByIdandEmail(String email, int id) throws SQLException;
	
	public List<InfoDownload> doRetriveAllByEmail(String email) throws SQLException;
	
	public Object[]  doRetriveByMostSold() throws SQLException;

}
