package it.unisa.beans;


public class DeletionAccountRequest {
	private String clientEmail;
	private String requestDate;
	
	public DeletionAccountRequest() {
		
	}
	
	public DeletionAccountRequest(String clientEmail, String requestDate) {
		this.clientEmail = clientEmail;
		this.requestDate = requestDate;
	}
	
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	
	
	
	
}
