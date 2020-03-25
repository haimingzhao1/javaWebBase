package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.bm.utils.JdbcUtil;

public class Demo1 {
	@Test
	public void testA(){
		Connection con = JdbcUtil.getConnection();
		System.out.println(con);
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Test
	public void test2(){
		String dateString = "2005-01-04";
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(date);
	}
}
