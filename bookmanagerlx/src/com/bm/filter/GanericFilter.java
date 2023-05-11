package com.bm.filter;

import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public abstract class GanericFilter implements Filter {
	private FilterConfig config;

	/**
	 * 这个方法一般在filter中不常使用，使用抽象基类实现一个空方法，这样在具体的filter
	 * 中就不需要再重复实现了
	 */
	@Override
	public void destroy() {

	}

	/**
	 * 这里给filter的init方法给私有化了，单独提供了获取FilterConfig以及FilterConfig对象中属性的方法，继承改类的filter就不用单独实现init
	 * 方法了，如果项目中需要使用多个自定义filter，并且某写filter中需要获取servletContext的话，在这里封装一下还是比较好的
	 * @param arg0
	 * @throws ServletException
	 */
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

	/**
	 * 获取上下文对象
	 * @return
	 */
	public ServletContext getServletContext(){
		return config.getServletContext();
	}
	public Enumeration<String> getInitParameterNames(){
		return config.getInitParameterNames();
	}
}
