package com.microblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.dao.RelaDao;
import com.microblog.po.User;

public class FriendSevlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		//��ȡ�ͻ�������
		 String u_id = request.getParameter("u_id");
		//�������ݣ�����DAO
		 RelaDao relaDao = new RelaDao();
		 List<User> friendlist = relaDao.getFriend(Integer.parseInt(u_id));
		//��������
			if(friendlist!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("friendlist", friendlist);
				
				request.getRequestDispatcher("/friend.jsp?msg="+u_id).forward(request, response);
	
	}

}}
