package com.microblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microblog.dao.EdictDao;
import com.microblog.po.User;


public class GetuserServlet extends HttpServlet {

	
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String u_id = request.getParameter("u_id");
		//����id��ѯ�û�
		
		User user = new EdictDao().getUserById(Integer.parseInt(u_id));
     
		//���û�ʵ�����浽request�������¡�
		request.setAttribute("user", user );
		//��ת����ʾ����
		request.getRequestDispatcher("/userinfo.jsp").forward(request, response);
		
		
		
	}

}
