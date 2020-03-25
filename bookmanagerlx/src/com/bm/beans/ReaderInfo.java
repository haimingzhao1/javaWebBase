package com.bm.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReaderInfo {
	private Integer readerid;
	private String name;
	private String sex;
	private Date birth;
	private String address;
	private String phone;
	public Integer getReaderid() {
		return readerid;
	}
	public void setReaderid(Integer readerid) {
		this.readerid = readerid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		try {
			if (birth!=null || !(birth.isEmpty())) {
				this.birth = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getAddress() {
		return address; 
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public ReaderInfo(Integer readerid, String name, String sex, String birth,
			String address, String phone) {
		this.readerid = readerid;
		this.name = name;
		this.sex = sex;
		try {
			this.birth = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.address = address;
		this.phone = phone;
	}
	public ReaderInfo() {
	}
	@Override
	public String toString() {
		return "ReaderInfo [readerid=" + readerid + ", name=" + name + ", sex="
				+ sex + ", birth=" + birth + ", address=" + address
				+ ", phone=" + phone + "]";
	}
	
}
