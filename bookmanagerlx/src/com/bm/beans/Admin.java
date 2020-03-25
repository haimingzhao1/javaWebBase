package com.bm.beans;

public class Admin {
	private Integer adminid;
	private String password;
	private String username;
	public Integer getAdminid() {
		return adminid;
	}
	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Admin(Integer admin_id, String password, String username) {
		this.adminid = adminid;
		this.password = password;
		this.username = username;
	}
	public Admin() {
	}
	@Override
	public String toString() {
		return "Admin [adminid=" + adminid + ", password=" + password
				+ ", username=" + username + "]";
	}
	
}
