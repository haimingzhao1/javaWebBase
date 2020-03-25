package com.bm.beans;

import java.util.Date;

public class LendList {
	private Integer sernum;
	private Integer bookid;
	private Integer readerid;
	private Date lenddate;
	private Date backdate;
	public Integer getSernum() {
		return sernum;
	}
	public void setSernum(Integer sernum) {
		this.sernum = sernum;
	}
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	public Integer getReaderid() {
		return readerid;
	}
	public void setReaderid(Integer readerid) {
		this.readerid = readerid;
	}
	public Date getLenddate() {
		return lenddate;
	}
	public void setLenddate(Date lenddate) {
		this.lenddate = lenddate;
	}
	public Date getBackdate() {
		return backdate;
	}
	public void setBackdate(Date backdate) {
		this.backdate = backdate;
	}
	public LendList(Integer sernum, Integer bookid, Integer readerid,
			Date lenddate, Date backdate) {
		this.sernum = sernum;
		this.bookid = bookid;
		this.readerid = readerid;
		this.lenddate = lenddate;
		this.backdate = backdate;
	}
	public LendList() {
	}
	@Override
	public String toString() {
		return "LendList [sernum=" + sernum + ", bookid=" + bookid
				+ ", readerid=" + readerid + ", lenddate=" + lenddate
				+ ", backdate=" + backdate + "]";
	}
	
}
