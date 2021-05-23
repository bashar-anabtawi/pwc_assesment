package com.pwc.assesment.model.entities;

public class User implements EntityModel {
	
	private int id;
	private String userName;
	private boolean isAdmin;
	private String password;
	
	
	
	public User(int id, String userName, String password, boolean isAdmin) {
		this.setId(id);
		this.setUserName(userName);
		this.setAdmin(isAdmin);
		this.setPassword(password);
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
	
}
