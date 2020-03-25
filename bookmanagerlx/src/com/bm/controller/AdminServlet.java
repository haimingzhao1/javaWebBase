package com.bm.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.bm.beans.BookInfo;
import com.bm.beans.LendList;
import com.bm.beans.ReaderCard;
import com.bm.beans.ReaderInfo;
import com.bm.dao.AdminDao;
import com.bm.service.AdminService;

public class AdminServlet extends BaseServlet {
	private AdminService adminService = new AdminService();;
	public String toAdminMain(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "s:jsp/admin_main.jsp";
	}
	//����û��ķ���
	public String toAddReader(HttpServletRequest request,HttpServletResponse response) throws ServletException,
	IOException{
		return "s:jsp/admin_reader_add.jsp";
	}
	public String readerAdd(HttpServletRequest request,HttpServletResponse response) throws ServletException,
	IOException{
		ReaderInfo reader = new ReaderInfo();
		Map<String, String[]> params = request.getParameterMap();
		Set<String> set = params.keySet();
		try {
			BeanUtils.populate(reader, params);
			reader.setBirth(request.getParameter("birth"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			adminService.addReader(reader, new ReaderCard(reader.getName(),request.getParameter("password")));
			return "s:admin?methodName=allReaders";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "s:admin?methodName=allReaders";
	}
	//��ȡ�����û� �ķ���
	public String allReaders(HttpServletRequest request,HttpServletResponse response) throws ServletException,
	IOException{
		List<ReaderInfo> readersList = adminService.getAllReaders();
		request.setAttribute("readers", readersList);
		return "f:jsp/admin_readers.jsp";
		
	}
	//�޸��û��ķ���
	public String toEdit(HttpServletRequest request,HttpServletResponse response) throws ServletException,
	IOException{
		ReaderInfo reader = adminService.getReaderById(request.getParameter("readerId"));
		request.setAttribute("reader", reader);
		return "f:jsp/admin_reader_edit.jsp";
		
	}
	
	public String editReader(HttpServletRequest request,HttpServletResponse response) throws ServletException,
	IOException{
		ReaderInfo reader = new ReaderInfo();
		try {
			BeanUtils.populate(reader, request.getParameterMap());
			reader.setBirth(request.getParameter("birth"));
			reader.setReaderid(Integer.parseInt(request.getParameter("readerId")));
			adminService.editReader(reader);
		} catch (Exception e) {
			e.printStackTrace();
			return "s:admin?methodName=allReaders";
		}
		return "s:admin?methodName=allReaders";
		
	}
	//ɾ������
	public String deleteReader(HttpServletRequest request,HttpServletResponse response) throws ServletException,
	IOException{
		adminService.deleteReader(request.getParameter("readerId"));
		return "s:admin?methodName=allReaders";
		
	}
	//��ȡ����ͼ��ӿ�
	public String allBooks(HttpServletRequest request,HttpServletResponse response) throws ServletException,
	IOException{
		List<BookInfo> bookList = adminService.getAllBooks();
		request.setAttribute("books", bookList);
		return "f:jsp/admin_books.jsp";
		
	}
	//���ͼ��ӿ�
	public String toAddBook(HttpServletRequest request,HttpServletResponse response) throws ServletException,
	IOException{
		return "f:jsp/admin_book_add.jsp";		
	}
	public String addBook(HttpServletRequest request,HttpServletResponse response) throws ServletException,
	IOException{
		BookInfo book = new BookInfo();
		try {
			BeanUtils.populate(book, request.getParameterMap());
			book.setPubdate(request.getParameter("pubdate"));
//			System.out.println(book);
			adminService.addBook(book);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "s:admin?methodName=allBooks";		
	}

	public String bookDetail(HttpServletRequest request,HttpServletResponse response) throws ServletException,
	IOException{
		Integer bookid = Integer.parseInt(request.getParameter("bookId"));
		Map<String, Object> map = adminService.bookDetail(bookid);
		request.setAttribute("map", map);
		return "f:jsp/admin_book_detail.jsp";		
	}
	//ɾ��ͼ��
	public String deleteBook(HttpServletRequest request,HttpServletResponse response) throws ServletException,
	IOException{
		Integer bookid = Integer.parseInt(request.getParameter("bookId"));
		adminService.deleteBook(bookid);
		return "s:admin?methodName=allBooks";		
	}
	//�޸� ͼ��
	public String toUpdateBook(HttpServletRequest request,HttpServletResponse response) throws ServletException,
	IOException{
		Integer bookid = Integer.parseInt(request.getParameter("bookId"));
		BookInfo book = adminService.getBookById(bookid);
		request.setAttribute("detail", book);
		return "f:jsp/admin_book_edit.jsp";		
	}
	public String updateBook(HttpServletRequest request,HttpServletResponse response) throws ServletException,
	IOException{
		BookInfo book = new BookInfo();
		try {
			BeanUtils.populate(book, request.getParameterMap());
			book.setPubdate(request.getParameter("pubdate"));
			System.out.println(book);
			adminService.updateBook(book);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "s:admin?methodName=allBooks";	
	}
	//������־
	public String lendList(HttpServletRequest request,HttpServletResponse response) throws ServletException,
	IOException{
		List<LendList> list = adminService.allLendList();
		request.setAttribute("list", list);
		request.setAttribute("succ", "��ѯ�ɹ���");
		return "f:jsp/admin_lend_list.jsp";		
	}
	//ɾ����־
	public String delLend(HttpServletRequest request,HttpServletResponse response) throws ServletException,
	IOException{
		Integer id = Integer.parseInt(request.getParameter("serNum"));
		adminService.delLend(id);
		return "s:admin?methodName=lendList";		
	}
	//��ѯͼ��
	public String searchBook(HttpServletRequest request,HttpServletResponse response) throws ServletException,
	IOException{
		String searchname = request.getParameter("searchWord");
		List<BookInfo> books = adminService.searchBook(searchname);
		if ((books!=null)&&books.size()!=0) {
			request.setAttribute("books", books);
			request.setAttribute("succ", "��ѯ�ɹ�");
		}else{
			request.setAttribute("error", "��ѯʧ��");
		}
		return "f:jsp/admin_books.jsp";		
	}
}
