package com.bm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	private static ComboPooledDataSource dataSource;
	static{
		Properties pts = new Properties();
		InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("dbconfig.properties");
		try {
			pts.load(in);
			dataSource = new ComboPooledDataSource();
			dataSource.setDriverClass(pts.getProperty("driverclass"));
			dataSource.setJdbcUrl(pts.getProperty("url"));
			dataSource.setUser(pts.getProperty("user"));
			dataSource.setPassword(pts.getProperty("pass"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		Connection con = null;
		try {
			con = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void close(Connection con,Statement st,ResultSet rs){
		try {
			if(con!=null)
				con.close();
			if(st!=null)
				st.close();
			if(rs!=null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static ComboPooledDataSource getDataSource(){
		return dataSource;
	}
}
