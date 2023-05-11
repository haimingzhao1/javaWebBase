package com.bm.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bm.beans.BookInfo;
import com.bm.beans.ClassInfo;
import com.bm.beans.LendList;
import com.bm.beans.ReaderCard;
import com.bm.beans.ReaderInfo;
import com.bm.dao.AdminDao;
import com.bm.utils.JdbcUtil;

public class AdminService {
	private AdminDao adminDao = new AdminDao();
	public void addReader(ReaderInfo reader,ReaderCard card) throws Exception{
		Connection con = JdbcUtil.getConnection();
		try {
			con.setAutoCommit(false);
			adminDao.addReaderInfo(reader, con);
			Integer id = adminDao.getReaderIdByName(reader.getName(),con);
			if (id==0) {
				throw new RuntimeException("读者添加失败！");
			}
			card.setReaderid(id);
			adminDao.addReaderCard(card, con);
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			JdbcUtil.close(con, null, null);
		}
	}
	public List<ReaderInfo> getAllReaders() {
		return adminDao.getAllReaders();
	}
	public ReaderInfo getReaderById(String readerId) {

		return adminDao.getReaderById(readerId);
	}
	public void editReader(ReaderInfo reader) {
		Connection con = JdbcUtil.getConnection();
		try {
			con.setAutoCommit(false);
			adminDao.editReaderById(reader,con);
			adminDao.editReaderCardById(new ReaderCard(reader.getReaderid(),reader.getName(),null), con);
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			JdbcUtil.close(con, null, null);
		}
	}
	public List<BookInfo> getAllBooks() {
		return adminDao.getAllBooks();
	}
	public void addBook(BookInfo book) {
		int result = adminDao.addBook(book);
		if (result == 0) {
			throw new RuntimeException("添加图书失败");
		}
	}
	public void deleteReader(String parameter) {
		Connection con = JdbcUtil.getConnection();
		try {
			con.setAutoCommit(false);
			adminDao.deleteReaderById(Integer.parseInt(parameter),con);
			adminDao.deleteReaderCardById(Integer.parseInt(parameter), con);
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			JdbcUtil.close(con, null, null);
		}

	}
	public Map<String,Object> bookDetail(Integer bookid) {
		Connection con = JdbcUtil.getConnection();
		Map<String,Object> map = new HashMap<>();
		try {
			con.setAutoCommit(false);
			BookInfo bookinfo = adminDao.getBookById(bookid,con);
			ClassInfo classinfo = adminDao.getClassById(bookinfo.getClassid(), con);
			map.put("bookinfo", bookinfo);
			map.put("classinfo", classinfo);
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			JdbcUtil.close(con, null, null);
		}
		return map;
	}
	public void deleteBook(Integer bookid) {
		adminDao.deleteBookById(bookid);
	}
	public BookInfo getBookById(Integer bookid) {
		Connection con =JdbcUtil.getConnection();
		BookInfo book = adminDao.getBookById(bookid, con);
		JdbcUtil.close(con, null, null);
		return book;
	}
	public void updateBook(BookInfo book) {
		adminDao.updateBookById(book);
	}
	public List<LendList> allLendList() {
		return adminDao.getAllLendList();
	}
	public void delLend(Integer id) {
		adminDao.delLendById(id);
	}
	public List<BookInfo> searchBook(String searchname) {
		return adminDao.searchBookByName(searchname);
	}
}
