package com.bm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.bm.beans.BookInfo;
import com.bm.beans.LendList;
import com.bm.beans.ReaderCard;
import com.bm.beans.ReaderInfo;
import com.bm.utils.JdbcUtil;

public class ReaderDao {

	public List<BookInfo> getAllBooks(Connection con) {
		String sql = "select book_id,name,author,publish,ISBN,introduction,language,price,pub_date,class_id,number from book_info";
		List<BookInfo> books = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		try {
			books = new ArrayList<>();
			pt = con.prepareStatement(sql);
			rs = pt.executeQuery();
			if (rs!=null) {
				while(rs.next()){
			    BookInfo book = new BookInfo();
				book.setBookid(rs.getInt(1));
				book.setName(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setPublish(rs.getString(4));
				book.setIsbn(rs.getString(5));
				book.setIntroduction(rs.getString(6));
				book.setLanguage(rs.getString(7));
				book.setPrice(rs.getBigDecimal(8));
				book.setPubdate(rs.getDate(9).toString());
				book.setClassid(rs.getInt(10));
				book.setNumber(rs.getInt(11));
				books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(null, pt, rs);
		}
		return books;
	}
	public List<Integer> getBookIdByReaderId(Integer readerId,Connection con){
		String sql = "SELECT book_id FROM lend_list WHERE reader_id = ? AND back_date IS NULL";
		PreparedStatement pt = null;
		ResultSet rs = null;
		List<Integer> list = new ArrayList<>();
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, readerId);
			rs = pt.executeQuery();
			if (rs!=null) {
				while(rs.next())
				list.add(rs.getInt("book_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(null, pt, rs);
		}
		return list;
	}
	public int addLendList(LendList lend, Connection con) {
		String sql = "insert into lend_list(book_id,reader_id,lend_date) values(?,?,?)";
		PreparedStatement pt = null;
		int result = 0;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, lend.getBookid());
			pt.setInt(2, lend.getReaderid());
			pt.setDate(3, new Date(lend.getLenddate().getTime()));
			result = pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(null, pt, null);
		}
		return result;
	}
	public int updataLendList(LendList back,Connection con) {
		String sql = "update lend_list set reader_id=?,back_date=? where book_id=?";
		PreparedStatement pt = null;
		int result = 0;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, back.getReaderid());
			pt.setDate(2, new Date(back.getBackdate().getTime()));
			pt.setInt(3, back.getBookid());
			result = pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(null, pt, null);
		}
		return result;
	}
	public List<BookInfo> getBooksByName(String name, Connection con) {
		String sql = "select book_id,name,author,publish,ISBN,introduction,language,price,pub_date,class_id,number from book_info where name like ?";
		List<BookInfo> books = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setString(1, ("%"+name+"%"));
			rs = pt.executeQuery();
			if (rs!=null) {
				books = new ArrayList<>();
				while(rs.next()){
			    BookInfo book = new BookInfo();
				book.setBookid(rs.getInt(1));
				book.setName(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setPublish(rs.getString(4));
				book.setIsbn(rs.getString(5));
				book.setIntroduction(rs.getString(6));
				book.setLanguage(rs.getString(7));
				book.setPrice(rs.getBigDecimal(8));
				book.setPubdate(rs.getDate(9).toString());
				book.setClassid(rs.getInt(10));
				book.setNumber(rs.getInt(11));
				books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(null, pt, rs);
		}
		return books;
	}
	public BookInfo getBookInfoByid(Integer bookId) {
		Connection con = JdbcUtil.getConnection();
		String sql = "select book_id,name,author,publish,ISBN,introduction,language,price,pub_date,class_id,number from book_info where book_id=?";
		BookInfo bookinfo = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, bookId);
			rs = pt.executeQuery();
			if (rs!=null&&rs.next()) {
				bookinfo = new BookInfo();
				bookinfo.setBookid(rs.getInt(1));
				bookinfo.setName(rs.getString(2));
				bookinfo.setAuthor(rs.getString(3));
				bookinfo.setPublish(rs.getString(4));
				bookinfo.setIsbn(rs.getString(5));
				bookinfo.setIntroduction(rs.getString(6));
				bookinfo.setLanguage(rs.getString(7));
				bookinfo.setPrice(rs.getBigDecimal(8));
				bookinfo.setPubdate(rs.getDate(9).toString());
				bookinfo.setClassid(rs.getInt(10));
				bookinfo.setNumber(rs.getInt(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(con, pt, rs);
		}
		return bookinfo;
	}
	public void updateBookInfoByNum(Integer number,Integer bookId,Connection con) {
		String sql = "update book_info set number=? where book_id=?";
		PreparedStatement pt = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, number);
			pt.setInt(2, bookId);
			pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(null, pt, null);
		}
	}
	public ReaderInfo getReaderInfoByid(int readerId) {
		String sql = "select reader_id,name,sex,birth,address,phone from reader_info where reader_id =?";
		Connection con = JdbcUtil.getConnection();
		PreparedStatement pt = null;
		ResultSet rs = null;
		ReaderInfo info = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, readerId);
			rs = pt.executeQuery();
			if (rs!=null&&rs.next()) {
				info = new ReaderInfo();
				info.setReaderid(rs.getInt(1));
				info.setName(rs.getString(2));
				info.setSex(rs.getString(3));
				info.setBirth(rs.getDate(4).toString());
				info.setAddress(rs.getString(5));
				info.setPhone(rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}
	public void updateReaderInfo(ReaderInfo editInfo) {
		String sql = "update reader_info set name=?,sex=?,birth=?,address=?,phone=? where reader_id=?";
		Object[] param ={editInfo.getName(),editInfo.getSex(),new Date(editInfo.getBirth().getTime()),
				editInfo.getAddress(),editInfo.getPhone(),editInfo.getReaderid()};
		try {
			new QueryRunner(JdbcUtil.getDataSource()).update(sql,param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<LendList> getLendListByReaderId(int readerId) {
		Connection con = JdbcUtil.getConnection();
		String sql = "select ser_num,book_id,reader_id,lend_date,back_date from lend_list where reader_id = ?";
		PreparedStatement pt = null;
		ResultSet rs = null;
		List<LendList> list = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, readerId);
			rs = pt.executeQuery();
			if (rs!=null) {
				list = new ArrayList<>();
				while (rs.next()) {
					LendList lendList = new LendList();
					lendList.setSernum(rs.getInt(1));
					lendList.setBookid(rs.getInt(2));
					lendList.setReaderid(rs.getInt(3));
					lendList.setLenddate(rs.getDate(4));
					lendList.setBackdate(rs.getDate(5));
					list.add(lendList);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public ReaderCard getReaderCardByIdPass(ReaderCard readerCard,
			Connection con) {
		String sql = "select reader_id,username,password from reader_card where reader_id=? and password=?";
		PreparedStatement pt = null;
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1,readerCard.getReaderid());
			pt.setString(2, readerCard.getPassword());
			rs = pt.executeQuery();
			if (rs!=null&&rs.next()) {
				return new ReaderCard(rs.getInt(1),rs.getString(2),rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(null, pt, rs);
		}
		return null;
	}
	public void updateReaderCard(ReaderCard readerCard, Connection con) {
		String sql = "update reader_card set password=? where reader_id=?";
		PreparedStatement pt = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setString(1, readerCard.getPassword());
			pt.setInt(2, readerCard.getReaderid());
			pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(null, pt, null);
		}
	}
}
