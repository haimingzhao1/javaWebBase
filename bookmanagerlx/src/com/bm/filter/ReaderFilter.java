package com.bm.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bm.beans.ReaderCard;

public class ReaderFilter extends GanericFilter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		ReaderCard reader = (ReaderCard) request.getSession().getAttribute("readercard");
		if (reader==null) {
			request.setAttribute("error", "ÇëÄúÏÈµÇÂ¼");
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		}else{
			chain.doFilter(request, response);
		}
	}

}
