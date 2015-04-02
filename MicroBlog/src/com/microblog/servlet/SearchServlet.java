package com.microblog.servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.microblog.dao.WeiboDao;

import com.microblog.po.Weibo;

public class SearchServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//�ӿͻ��˻������

		String w_content = request.getParameter("searchtxt");
		String type =request.getParameter("searchtype");
		String a =  "content";
		if(type.equals(a)){
		//�������ݣ�����DAO
		 WeiboDao weiboDao = new WeiboDao();
	
			List<Weibo> searchcontentlist;
			try {
				searchcontentlist = weiboDao.getSearchByContent(w_content);
				//��������
				if(searchcontentlist!=null) {
					HttpSession session = request.getSession();
					session.setAttribute("searchcontentlist", searchcontentlist);
					
					request.getRequestDispatcher("../showsearch.jsp").forward(request, response);
			} else {
				response.sendRedirect("../error.jsp");
			}

		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	else {//�������ݣ�����DAO
	 WeiboDao weiboDao = new WeiboDao();
		
		List<Weibo> searchcontentlist;
		try {
			searchcontentlist = weiboDao.getSearchByUser(w_content);
			//��������
			if(searchcontentlist!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("searchcontentlist", searchcontentlist);
				
				request.getRequestDispatcher("../showsearch.jsp").forward(request, response);
		} else {
			response.sendRedirect("../error.jsp");
		}

	} catch (Exception e) {
		
		e.printStackTrace();
	}

		
}
}
}
		