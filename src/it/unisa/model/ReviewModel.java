package it.unisa.model;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.beans.ReviewBean;

public interface ReviewModel {

	public Collection<ReviewBean> doRetrieveByContentId(int content_id) throws SQLException;
		
	public ReviewBean doRetrieveByContentIdandUserEmail(int content_id, String user_email) throws SQLException;
		
	public boolean doSave(ReviewBean review) throws SQLException;
	
	public boolean doDelete(ReviewBean review) throws SQLException;
	
	public boolean doUpdate(ReviewBean review) throws SQLException;
	
	public Object[] doRetriveAllByAverage() throws SQLException;
}
