package it.unisa.beans;

public class UserBean {
	
	private String email;
	private String name;
	private String surname;
	private String password;
	private boolean isAdmin;
	
	//costruttore
	public UserBean() {
		super();
		email = "";
		name = "";
		surname = "";
		password = "";
		isAdmin = false;
	}

	//  METODI GET
	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	// METODI GET
	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return getClass().getName()+"[email=" + email + ", name=" + name + ", surname=" + surname + ", password=" + password
				+ ", isAdmin=" + isAdmin + "]";
	}
	
	
	
	
}
