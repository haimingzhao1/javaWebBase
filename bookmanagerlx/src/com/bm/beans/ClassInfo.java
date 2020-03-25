package com.bm.beans;

public class ClassInfo {
	private Integer classid;
	private String classname;
	public Integer getClassid() {
		return classid;
	}
	public void setClassid(Integer classid) {
		this.classid = classid;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public ClassInfo(Integer classid, String classname) {
		this.classid = classid;
		this.classname = classname;
	}
	public ClassInfo() {
	}
	@Override
	public String toString() {
		return "ClassInfo [classid=" + classid + ", classname=" + classname
				+ "]";
	}
	
}
