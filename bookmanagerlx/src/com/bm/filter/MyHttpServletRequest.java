package com.bm.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyHttpServletRequest extends HttpServletRequestWrapper {
	private String encode;
	private HttpServletRequest request;
	public MyHttpServletRequest(HttpServletRequest request,String encode) {
		super(request);
		this.encode = encode;
		this.request = request;
	}
	@Override
	public String getParameter(String name) {
		String name1 = request.getParameter(name);
		if (name1 !=null) {
			try {
				byte[] btys = name1.getBytes("iso-8859-1");
				name1 = new String(btys,encode);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}	
		}
		return name1;
	}
	
}
