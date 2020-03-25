package com.bm.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bm.beans.BookInfo;
import com.bm.beans.LendList;
import com.bm.beans.ReaderCard;
import com.bm.beans.ReaderInfo;
import com.bm.dao.ReaderDao;
import com.bm.utils.JdbcUtil;
import com.bm.utils.RepassException;

public class ReaderService {
	private ReaderDao readerDao = new ReaderDao();
	public Map<String,Object> allBooks(String readerId) {
		Connection con = JdbcUtil.getConnection();
		Map<String,Object> map = new HashMap<>();
		try {
			con.setAutoCommit(false);
			List<BookInfo> list = readerDao.getAllBooks(con);
			List<Integer> bookids = readerDao.getBookIdByReaderId(Integer.parseInt(readerId), con);
			if (list!=null) {
				map.put("list", list);
				if (bookids!=null) {
					map.put("myLendList", bookids);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return map;
	}
	public void lendBook(LendList lend, Integer number) throws RuntimeException{
		Connection con = JdbcUtil.getConnection();
		try {
			con.setAutoCommit(false);
			readerDao.addLendList(lend,con);
			readerDao.updateBookInfoByNum(number-1,lend.getBookid(), con);
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
	public void backBook(LendList back, Integer number) throws RuntimeException{
		Connection con = JdbcUtil.getConnection();
		try {
			con.setAutoCommit(false);
			readerDao.updataLendList(back,con);
			readerDao.updateBookInfoByNum(number+1, back.getBookid(), con);
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
	public Map<String, Object> searchBooks(String readerId, String name) {
		Connection con = JdbcUtil.getConnection();
		Map<String,Object> map = new HashMap<>();
		try {
			con.setAutoCommit(false);
			List<BookInfo> list = readerDao.getBooksByName(name,con);
			List<Integer> bookids = readerDao.getBookIdByReaderId(Integer.parseInt(readerId), con);
			if (list!=null) {
				map.put("list", list);
				if (bookids!=null) {
					map.put("myLendList", bookids);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	public BookInfo bookDetail(String bookId) {
		BookInfo detail = readerDao.getBookInfoByid(Integer.parseInt(bookId));
		return detail;
	}
	public ReaderInfo readerInfo(int readerId) {
		ReaderInfo info = readerDao.getReaderInfoByid(readerId);
		return info;
	}
	public ReaderInfo editReaderInfo(ReaderInfo editInfo) {
		readerDao.updateReaderInfo(editInfo);
		ReaderInfo readerInfo = readerDao.getReaderInfoByid(editInfo.getReaderid());
		return readerInfo;
	}
	public List<LendList> myLendList(int readerId) {
		return readerDao.getLendListByReaderId(readerId);
	}
	public void readerRepss(ReaderCard readerCard, String oldPasswd) throws RepassException{
		Connection con = JdbcUtil.getConnection();
		try {
			con.setAutoCommit(false);
			ReaderCard oldCard = new ReaderCard(readerCard.getReaderid(),readerCard.getUsername(),oldPasswd);
			ReaderCard card = readerDao.getReaderCardByIdPass(oldCard,con);
			if (card!=null) {
				readerDao.updateReaderCard(readerCard,con);
			}else{
				throw new RepassException("用户名密码不正确");
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			JdbcUtil.close(con, null, null);
		}
	}

}
