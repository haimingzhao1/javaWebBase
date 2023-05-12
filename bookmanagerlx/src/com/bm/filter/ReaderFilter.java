package com.bm.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bm.beans.ReaderCard;

/**
 * javaWeb三大组件（servlet程序，filter过滤器，listener监听器）
 * 过滤器（filter）：作用，既可以对请求做拦截，又可以对相应做处理
 * 常见场景：权限检查，记录操作日志，拦截请求，过滤，编码
 *   <filter-mapping>
 *   	<filter-name>readerFilter</filter-name>
 *   	<url-pattern>/jsp/reader/*</url-pattern>
 *   </filter-mapping>
 *   此过滤器对/jsp/reader/下的所有请求做了拦截，获取session中的用户，做是否登录的拦截处理
 */
public class ReaderFilter extends GanericFilter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
						 FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		ReaderCard reader = (ReaderCard) request.getSession().getAttribute("readercard");
		if (reader==null) {
			request.setAttribute("error", "请您先登录");
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		}else{
			chain.doFilter(request, response);
		}
	}

}
