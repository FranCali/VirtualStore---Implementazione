package it.unisa.beans;

public class AccountBean {

	private String email;
	private String password;
	private Integer clientId;
	
	
	
	public AccountBean(String email, String password, Integer clientId) {
		this.email = email;
		this.password = password;
		this.clientId = clientId;
	}
	
	public AccountBean() {
	}

	public AccountBean(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	@Override
	public String toString() {
		return "AccountBean [email=" + email + ", password=" + password + ", clientId=" + clientId + "]";
	}
	
	

}
