package com.bm.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.bm.beans.BookInfo;
import com.bm.beans.LendList;
import com.bm.beans.ReaderCard;
import com.bm.beans.ReaderInfo;
import com.bm.service.ReaderService;
import com.bm.utils.RepassException;

public class ReaderServlet extends BaseServlet {
	private ReaderService readerService = new ReaderService();
	public String toReaderMain(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "s:jsp/reader/reader_main.jsp";
	}
	//查询所有图书
	public String allBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String readerId = request.getParameter("readerId");
		Map<String,Object> map = readerService.allBooks(readerId);
		request.setAttribute("map", map);
		return "f:jsp/reader/reader_books.jsp";
	}
	//搜索 
	public String searchBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String readerId = request.getParameter("readerId");
		Map<String,Object> map = readerService.searchBooks(readerId,request.getParameter("searchWord"));
		request.setAttribute("map", map);
		if (map==null||map.size()==0) {
			request.setAttribute("succ", "查询失败");
		}else{
			request.setAttribute("succ", "查询成功");
		}
		System.out.println(map);
		return "f:jsp/reader/reader_books.jsp";
	}
	//借阅
	public String lendBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		Integer number = Integer.parseInt(request.getParameter("number"));
		ReaderCard reader = (ReaderCard) request.getSession().getAttribute("readercard");
		LendList lend = new LendList();
		lend.setReaderid(reader.getReaderid());
		lend.setBookid(Integer.parseInt(bookId));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date lenddate = new Date();
		String datestr = sdf.format(lenddate);
		try {
			lenddate = sdf.parse(datestr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		lend.setLenddate(lenddate);
		try {
			readerService.lendBook(lend,number);
			request.setAttribute("succ", "借阅成功！");
			return "f:jsp/reader/reader_main.jsp";
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			return "f:jsp/reader/reader_main.jsp";
		}
	}
	//归还
	public String backBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		String number = request.getParameter("number");
		ReaderCard reader = (ReaderCard) request.getSession().getAttribute("readercard");
		LendList back = new LendList();
		back.setReaderid(reader.getReaderid());
		back.setBookid(Integer.parseInt(bookId));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date backdate = new Date();
		String datestr = sdf.format(backdate);
		try {
			backdate = sdf.parse(datestr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		back.setBackdate(backdate);
		try {
			readerService.backBook(back,Integer.parseInt(number));
			request.setAttribute("succ", "归还成功！");
			return "f:jsp/reader/reader_main.jsp";
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			return "f:jsp/reader/reader_main.jsp";
		}
	}	
	//图书详情
	public String bookDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		BookInfo detail = readerService.bookDetail(bookId);
//		System.out.println(detail);
		request.setAttribute("detail", detail);
		return "f:jsp/reader/reader_book_detail.jsp";
	}
	//个人信息
	public String readerInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String readerId = request.getParameter("readerId");
		ReaderInfo readerinfo = readerService.readerInfo(Integer.parseInt(readerId));
//		System.out.println(detail);
		request.setAttribute("readerinfo", readerinfo);
		return "f:jsp/reader/reader_info.jsp";
	}
	//修改个人信息
	public String toEditReaderInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String readerId = request.getParameter("readerId");
		ReaderInfo readerinfo = readerService.readerInfo(Integer.parseInt(readerId));
		request.setAttribute("readerinfo", readerinfo);
		return "f:jsp/reader/reader_info_edit.jsp";
	}
	public String EditReaderInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReaderInfo editInfo = new ReaderInfo();
		try {
			BeanUtils.populate(editInfo, request.getParameterMap());
			editInfo.setBirth(request.getParameter("birth"));
			ReaderInfo newreaderinfo = readerService.editReaderInfo(editInfo);
			request.setAttribute("readerinfo", newreaderinfo);
			if (newreaderinfo!=null) {
				request.setAttribute("succ", "修改成功");
			}
			else{
				request.setAttribute("error", "修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f:jsp/reader/reader_info.jsp";
	}
	
	//我的借还
	public String myLend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String readerId = request.getParameter("readerId");
			List<LendList> list = readerService.myLendList(Integer.parseInt(readerId));
			request.setAttribute("list", list);
			if (list!=null) {
				request.setAttribute("succ", "查询成功");
			}else{
				request.setAttribute("succ", "您暂时没有借书哦");
			}
		return "f:jsp/reader/reader_lend_list.jsp";
	}
	//修改密码
	public String toReaderRepass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		return "f:jsp/reader/reader_repasswd.jsp";
	}
	public String readerRepass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReaderCard readerCard = new ReaderCard();
		readerCard.setPassword(request.getParameter("newPasswd"));
		String readerId = request.getParameter("readerId");
		readerCard.setReaderid(Integer.parseInt(readerId));
		String oldPasswd = request.getParameter("oldPasswd");
		try {
			readerService.readerRepss(readerCard,oldPasswd);
			request.getSession().invalidate();
			request.setAttribute("succ", "修改密码成功请重新登录！");
			return "f:jsp/index.jsp";
		} catch (RepassException e) {
			request.setAttribute("error", e.getMessage());
			return "f:jsp/reader/reader_repasswd.jsp";
		}

		
	}
}
