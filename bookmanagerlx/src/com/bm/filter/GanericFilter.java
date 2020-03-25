package com.bm.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class GanericFilter implements Filter {
	private FilterConfig config;
	@Override
	public void destroy() {

	}
	@Override
	public abstract void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException;

	@Override
	public final void init(FilterConfig arg0) throws ServletException {
		config = arg0;
		this.init();
	}
	public void init(){
		
	}
	public FilterConfig getFilterConfig(){
		return config;
	}
	public String getFilterName(){
		return config.getFilterName();
		
	}
	public String getInitParameter(String paramname){
		return config.getInitParameter(paramname);
	}
	public ServletContext getServletContext(){
		return config.getServletContext();
	}
	public Enumeration<String> getInitParameterNames(){
		return config.getInitParameterNames();
	}
}
