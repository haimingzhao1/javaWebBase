package com.bm.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("methodName");
		if (methodName==null || methodName.equals("")) {
			throw new RuntimeException("请求参数methodName不能为空");
		}
		Method method = null;
		try {
			method = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("请求的方法" + methodName + "不存在");
		} 
		try {
		     String result = (String)method.invoke(this, request,response);
		     if (result.contains("f")) {
				request.getRequestDispatcher("/"+result.substring(result.indexOf(":")+1)).forward(request, response);
			}else if(result.contains("s")){
				response.sendRedirect(request.getContextPath() + "/" + result.substring(result.indexOf(":")+1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	

}
