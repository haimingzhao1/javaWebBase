package com.bm.beans;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookInfo {
	private Integer bookid;
	private String name;
	private String author;
	private String publish;
	private String isbn;
	private String introduction;
	private String language;
	private BigDecimal price;
	private Date pubdate;
	private Integer classid;
	private Integer number;
	public BookInfo(Integer bookid, String name, String author, String publish,
			String isbn, String introduction, String language,
			BigDecimal price, Date pubdate, Integer classid, Integer number) {
		this.bookid = bookid;
		this.name = name;
		this.author = author;
		this.publish = publish;
		this.isbn = isbn;
		this.introduction = introduction;
		this.language = language;
		this.price = price;
		this.pubdate = pubdate;
		this.classid = classid;
		this.number = number;
	}
	public BookInfo() {
	}
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		try {
			this.pubdate = new SimpleDateFormat("yyyy-MM-dd").parse(pubdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public Integer getClassid() {
		return classid;
	}
	public void setClassid(Integer classid) {
		this.classid = classid;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "BookInfo [bookid=" + bookid + ", name=" + name + ", author="
				+ author + ", publish=" + publish + ", isbn=" + isbn
				+ ", introduction=" + introduction + ", language=" + language
				+ ", price=" + price + ", pubdate=" + pubdate + ", classid="
				+ classid + ", number=" + number + "]";
	}
	
}
