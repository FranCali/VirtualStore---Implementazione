package it.unisa.util;

import java.io.Serializable;

import it.unisa.beans.ContentBean;

@SuppressWarnings("serial")
public class InfoDownload implements Serializable {

	private String email;
	private ContentBean content;
	private String date;

	public InfoDownload() {
		email = "";
		content = new ContentBean();
		date = "";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ContentBean getContent() {
		return this.content;
	}

	public void setContent(ContentBean content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
