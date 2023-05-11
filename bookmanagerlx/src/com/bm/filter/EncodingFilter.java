package com.bm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter extends GanericFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String method = req.getMethod();
		String encode = getInitParameter("encode");
		if (method.equals("post")||method.equals("POST")) {
			req.setCharacterEncoding(encode);
			res.setContentType("text/html;charset=utf-8");
			chain.doFilter(req, res);
		}
		else if(method.equals("get")||method.equals("GET")){
			MyHttpServletRequest myRequest = new MyHttpServletRequest(req, encode);
			res.setContentType("text/html;charset=utf-8");
			chain.doFilter(myRequest, res);
		}
	}

}
