package it.unisa.beans;

import java.util.ArrayList;

public class ManagerBean extends ClientBean {
	private Integer id;
	private ArrayList<RoleBean> roles;
	
	public ManagerBean(String nome, String cognome, AccountBean account, ArrayList<RoleBean> roles) {
		super(nome, cognome, account);
		this.roles = roles;
	}

	public ManagerBean(String nome, String cognome, AccountBean account, RoleBean ruolo) {
		super(nome, cognome, account);
		roles = new ArrayList<>();
		roles.add(ruolo);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArrayList<RoleBean> getRules() {
		return roles;
	}

	public void setRoles(ArrayList<RoleBean> roles) {
		this.roles = roles;
	}
	
	public void addRole(RoleBean role) {
		roles.add(role);
	}
}
