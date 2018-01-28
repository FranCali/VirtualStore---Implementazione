package it.unisa.beans;

public class ClientBean {

	private Integer id;
	private String name;
	private String surname;
	private AccountBean account;
		

	public ClientBean(String name, String surname, AccountBean account) {
		this.name = name;
		this.surname = surname;
		this.account = account;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ClientBean() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public AccountBean getAccount() {
		return account;
	}
	public void setAccount(AccountBean account) {
		this.account = account;
	}
	
	

	@Override
	public String toString() {
		return "ClientBean [name=" + name + ", surname=" + surname + ", account=" + account + "]";
	}
	
	
}
