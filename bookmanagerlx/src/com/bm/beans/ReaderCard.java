package com.bm.beans;

public class ReaderCard {
	private Integer readerid;
	private String username;
	private String password;
	public Integer getReaderid() {
		return readerid;
	}
	public void setReaderid(Integer readerid) {
		this.readerid = readerid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ReaderCard(Integer readerid, String username, String password) {
		this.readerid = readerid;
		this.username = username;
		this.password = password;
	}
	public ReaderCard() {
	}
	
	public ReaderCard(String username, String password) {
		this.username = username;
		this.password = password;
	}
	@Override
	public String toString() {
		return "ReaderCard [readerid=" + readerid + ", username=" + username
				+ ", password=" + password + "]";
	}
	
}
