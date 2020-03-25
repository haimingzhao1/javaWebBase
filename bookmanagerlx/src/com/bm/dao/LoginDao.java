package com.bm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.bm.beans.Admin;
import com.bm.beans.ReaderCard;
import com.bm.utils.JdbcUtil;

public class LoginDao {
	//检查是否是用户的dao
	public ReaderCard checkReader(ReaderCard reader){
		Connection con = JdbcUtil.getConnection();
		String sql = "select reader_id,username,password from reader_card where reader_id = ?";
		PreparedStatement pt = null;
		ResultSet rs = null;
		ReaderCard reader1 = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, reader.getReaderid());
			rs = pt.executeQuery();
			if (rs!=null&&rs.next()) {
				reader1 = new ReaderCard();
				reader1.setReaderid(rs.getInt(1));
				reader1.setUsername(rs.getString(2));
				reader1.setPassword(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(con, pt, rs);
		}
		return reader1;
	}
	//检查是否是管理员的dao
	public Admin checkAdmin(Admin admin){
		Connection con = JdbcUtil.getConnection();
		String sql = "select admin_id,username,password from admin where admin_id = ?";
		PreparedStatement pt = null;
		ResultSet rs = null;
		Admin admin1 = null;
		try {
			pt = con.prepareStatement(sql);
			pt.setInt(1, admin.getAdminid());
			rs = pt.executeQuery();
			if (rs!=null&&rs.next()) {
				admin1 = new Admin();
				admin1.setAdminid(rs.getInt(1));
				admin1.setUsername(rs.getString(2));
				admin1.setPassword(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(con, pt, rs);
		}
		return admin1;
	}
}
