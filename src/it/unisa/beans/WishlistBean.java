package it.unisa.beans;

import java.util.LinkedList;

public class WishlistBean {

	private int id;
	private String email;
	private LinkedList<ContentBean> contents;




	public void setId(int id) {
		this.id = id;
	}


	public WishlistBean() {
		email="";
		contents= new LinkedList<ContentBean>();
	}
	
	
	public ContentBean getElementById(int id){
		for(ContentBean content: contents){
			if(content.getId()==id){
				return content;
			}
		}
		return null;
	}
	
	public void insertContent(ContentBean content){
		contents.add(content);
	}
	
	
	public boolean deleteContent(ContentBean content) {
		for(ContentBean bean: contents) {
			if(bean.getId() == content.getId()) {
				contents.remove(bean);
				return true;
			}
		}
		
		return false;	
	}
	
	public  boolean deleteAllContents() {
		 return  contents.removeAll(contents);
	}
	
	public void setContents(LinkedList<ContentBean> contents) {
		this.contents = contents;
	}
	
	public LinkedList<ContentBean> getContents(){
		return contents;
	}
	
	public boolean isEmpty(){
		return contents.isEmpty();
	}
	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName()+"[id=" + id + ", email=" + email + "]";
	}
	
}
