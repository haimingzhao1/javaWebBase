package com.bm.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.bm.beans.Admin;
import com.bm.beans.ReaderCard;
import com.bm.service.LoginService;

public class LogonServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin admin = new Admin();
		ReaderCard reader = new ReaderCard();
		LoginService longinService = new LoginService();
		try {
			String id = request.getParameter("id");
			String pass = request.getParameter("passwd");
			admin.setAdminid(Integer.parseInt(id));
			admin.setPassword(pass);
			reader.setReaderid(Integer.parseInt(id));
			reader.setPassword(pass);
			List<String> resultlist = longinService.checkLogin(admin, reader);
			String result = resultlist.get(0);
			if (result.equals("1")) {
				admin.setUsername(resultlist.get(1));
				request.getSession().setAttribute("admin", admin);
			}
			if (result.equals("2")) {
				reader.setUsername(resultlist.get(1));
				request.getSession().setAttribute("readercard", reader);
			}
			response.getWriter().write("{\"stateCode\":\""+result+"\"}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
