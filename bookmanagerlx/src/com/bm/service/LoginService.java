package com.bm.service;

import java.util.ArrayList;
import java.util.List;

import com.bm.beans.Admin;
import com.bm.beans.ReaderCard;
import com.bm.dao.LoginDao;

public class LoginService {
	private LoginDao loginDao = new LoginDao();
	public List<String> checkLogin(Admin admin,ReaderCard reader){
		Admin admin1 = loginDao.checkAdmin(admin);
		ReaderCard reader1 = loginDao.checkReader(reader);
		List<String> list = new ArrayList<>(); 
		if (admin1!=null) {
			if (admin1.getPassword().equals(admin.getPassword())) {
				list.add("1");
				list.add(admin1.getUsername());
				return list;
			}else{
				list.add("0");
				return list;
			}
		}else if(reader1!=null){
			if (reader1.getPassword().equals(reader.getPassword())) {
				list.add("2");
				list.add(reader1.getUsername());
				return list;
			}else{
				list.add("0");
				return list;
			}
		}
		list.add("0");
		return list;
	}
}
