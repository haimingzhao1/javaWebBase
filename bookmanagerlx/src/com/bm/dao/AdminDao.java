package com.bm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bm.beans.BookInfo;
import com.bm.beans.ClassInfo;
import com.bm.beans.LendList;
import com.bm.beans.ReaderCard;
import com.bm.beans.ReaderInfo;
import com.bm.utils.JdbcUtil;

public class AdminDao {
	public int addReaderInfo(ReaderInfo info, Connection con){
		String sql = "insert into reader_info(name,sex,birth,address,phone) values(?,?,?,?,?)";
		PreparedStatement pt = null;
		int result = 0;
		try {
			pt = con.prepareStatement(sql);
			pt.setString(1, info.getName());
			pt.setString(2, info.getSex());
			pt.setDate(3, new Date(info.getBirth().getTime()));
			pt.setString(4, info.getAddress());
			pt.setString(5, info.getPhone());
			result = pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(null, pt, null);
		}
		return result;
	}
	public int addReaderCard(ReaderCard card,Connection con){
		String sql = "insert into reader_card(reader_id,username,password) values(?,?,?)";
		Object[] params = {card.getReaderid(),card.getUsername(),card.getPassword()};
		PreparedStatement pt = null;
		int result = 0;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, card.getReaderid());
			pt.setString(2, card.getUsername());
			pt.setString(3, card.getPassword());
			result = pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(null, pt, null);
		}
		return result;
	}
	public Integer getReaderIdByName(String name,Connection con) {
		String sql = "select reader_id from reader_info where name=?";
		ResultSet rs = null; 
		PreparedStatement pt = null;
		try {
			 pt = con.prepareStatement(sql);
			 pt.setString(1, name);
			 rs = pt.executeQuery();
			 if (rs!=null && rs.next()) {
				return rs.getInt("reader_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public List<ReaderInfo> getAllReaders() {
		String sql = "select reader_id,name,sex,birth,address,phone from reader_info";
		Connection con = JdbcUtil.getConnection();
		List<ReaderInfo> list = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			rs = pt.executeQuery();
			list = new ArrayList<>();
			if (rs!=null) {
				while(rs.next()){
				ReaderInfo reader = new ReaderInfo();
				reader.setReaderid(rs.getInt(1));
				reader.setName(rs.getString(2));
				reader.setSex(rs.getString(3));
				reader.setBirth(rs.getDate(4).toString());
				reader.setAddress(rs.getString(5));
				reader.setPhone(rs.getString(6));
				list.add(reader);
			   }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(con, pt, rs);
		}
		return list;
	}
	public ReaderInfo getReaderById(String readerId) {
		String sql = "select reader_id,name,sex,birth,address,phone from reader_info where reader_id=?";
		Connection con = JdbcUtil.getConnection();
		ReaderInfo reader = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		try {
			reader = new ReaderInfo();
			pt = con.prepareStatement(sql);
			pt.setInt(1, Integer.parseInt(readerId));
			rs = pt.executeQuery();
			if (rs!=null && rs.next()) {
				reader.setReaderid(rs.getInt(1));
				reader.setName(rs.getString(2));
				reader.setSex(rs.getString(3));
				reader.setBirth(rs.getDate(4).toString());
				reader.setAddress(rs.getString(5));
				reader.setPhone(rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(con, pt, rs);
		}
		return reader;
	}
	public void editReaderById(ReaderInfo reader,Connection con) {
		String sql = "update reader_info set name=?,sex=?,birth=?,address=?,phone=? where reader_id=?";
		PreparedStatement pt = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setString(1, reader.getName());
			pt.setString(2, reader.getSex());
			pt.setDate(3, new Date(reader.getBirth().getTime()));
			pt.setString(4, reader.getAddress());
			pt.setString(5, reader.getPhone());
			pt.setInt(6, reader.getReaderid());
			pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(null, pt, null);
		}
	}
	public void editReaderCardById(ReaderCard card,Connection con) {
		String sql = "update reader_card set username=? where reader_id=?";
		PreparedStatement pt = null;
		try {
			 pt = con.prepareStatement(sql);
			 pt.setString(1, card.getUsername());
			 pt.setInt(2, card.getReaderid());
			 pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(null, pt, null);
		}
	}
	public List<BookInfo> getAllBooks() {
		String sql = "select book_id,name,author,publish,ISBN,introduction,language,price,pub_date,class_id,number from book_info";
		Connection con = JdbcUtil.getConnection();
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
			JdbcUtil.close(con, pt, rs);
		}
		return books;
	}
	public int addBook(BookInfo book) {
		String sql = "insert into book_info(name,author,publish,ISBN,introduction,language,price,pub_date,class_id,number) values("
				+ "?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {book.getName(),book.getAuthor(),book.getPublish(),book.getIsbn(),book.getIntroduction(),
				book.getLanguage(),book.getPrice(),new Date(book.getPubdate().getTime()),book.getClassid(),book.getNumber()
				};
		int result = 0;
		try {
			result = new QueryRunner(JdbcUtil.getDataSource()).update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int deleteReaderById(Integer readerId,Connection con) {
		String sql = "delete from reader_info where reader_id=?";
		PreparedStatement pt = null;
		int result = 0;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, readerId);
			result = pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int deleteReaderCardById(Integer readerId,Connection con) {
		String sql = "delete from reader_card where reader_id=?";
		PreparedStatement pt = null;
		int result = 0;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, readerId);
			result = pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public BookInfo getBookById(Integer bookid,Connection con) {
		String sql = "select book_id,name,author,publish,ISBN,introduction,language,price,pub_date,class_id,number from book_info where book_id = ?";
		PreparedStatement pt = null;
		BookInfo book = null;
		ResultSet rs = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, bookid);
			rs = pt.executeQuery();
			if (rs!=null && rs.next()) {
				book = new BookInfo();
				book.setBookid(rs.getInt("book_id"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setPublish(rs.getString("publish"));
				book.setIsbn(rs.getString("ISBN"));
				book.setIntroduction(rs.getString("introduction"));
				book.setLanguage(rs.getString("language"));
				book.setPrice(rs.getBigDecimal("price"));
				book.setPubdate(rs.getDate("pub_date").toString());
				book.setClassid(rs.getInt("class_id"));
				book.setNumber(rs.getInt("number"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			 JdbcUtil.close(null, pt, rs);
		}
		return book;
	}
	public ClassInfo getClassById(Integer classid,Connection con){
		String sql = "select class_id,class_name from class_info where class_id=?";
		PreparedStatement pt = null;
		ResultSet rs = null;
		ClassInfo classinfo = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, classid);
			rs = pt.executeQuery();
			if (rs!=null&&rs.next()) {
				classinfo = new ClassInfo();
				classinfo.setClassid(rs.getInt(1));
				classinfo.setClassname(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(null, pt, rs);
		}
		return classinfo;
	}
	public void deleteBookById(Integer bookid) {
		String sql = "delete from book_info where book_id=?";
		try {
			new QueryRunner(JdbcUtil.getDataSource()).update(sql, bookid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateBookById(BookInfo book) {
		String sql = "update book_info set name=?,author=?,publish=?,ISBN=?,introduction=?,language=?,"
				+ "price=?,pub_date=?,class_id=?,number=? where book_id=?";
		Object[] params={book.getName(),book.getAuthor(),book.getPublish(),book.getIsbn(),
				book.getIntroduction(),book.getLanguage(),book.getPrice(),new Date(book.getPubdate().getTime()),
				book.getClassid(),book.getNumber(),book.getBookid()};
		try {
			new QueryRunner(JdbcUtil.getDataSource()).update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<LendList> getAllLendList() {
		Connection con = JdbcUtil.getConnection();
		String sql = "select ser_num,book_id,reader_id,lend_date,back_date from lend_list";
		PreparedStatement pt =null;
		ResultSet rs = null;
		List<LendList> list = null;
		try {
			pt = con.prepareStatement(sql);
			rs = pt.executeQuery();
			if (rs!=null) {
				list = new ArrayList<>();
				while(rs.next()){
					LendList lend = new LendList();
					lend.setSernum(rs.getInt(1));
					lend.setBookid(rs.getInt(2));
					lend.setReaderid(rs.getInt(3));
					lend.setLenddate(rs.getDate(4));
					lend.setBackdate(rs.getDate(5));
					list.add(lend);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(con, pt, rs);
		}

		return list;
	}
	public void delLendById(Integer id) {
		Connection con = JdbcUtil.getConnection();
		PreparedStatement pt =null;
		String sql = "delete from lend_list where ser_num=?";
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, id);
			pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(con, pt, null);
		}
	}
	public List<BookInfo> searchBookByName(String searchname) {
		String sql = "select book_id,name,author,publish,ISBN,introduction,language,price,pub_date,class_id,number from book_info where name like ?";
		Connection con = JdbcUtil.getConnection();
		List<BookInfo> books = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		try {
			books = new ArrayList<>();
			pt = con.prepareStatement(sql);
			pt.setString(1, ("%"+ searchname +"%"));
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
				JdbcUtil.close(con, pt, rs);
			}
		return books;
	}
}
