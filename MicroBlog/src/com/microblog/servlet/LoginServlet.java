package com.microblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.dao.AdminDao;
import com.microblog.dao.RelaDao;
import com.microblog.dao.WeiboDao;
import com.microblog.po.User;
import com.microblog.po.Weibo;

public class LoginServlet extends HttpServlet {

	private static final int IntegratedPara = 0;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// ��ȡ�ͻ��˴��������
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		// �������ݣ���̨֧��
		AdminDao adminDao = new AdminDao();
		User user = adminDao.check(userid, password);
		//���cookie
		String autologin= request.getParameter("autologin");
		 
		if (autologin!=null&&autologin.equals("on")) {
		Cookie cookie = new Cookie("autologinname", userid); // �����û�����Cookie  
		cookie.setPath("/");    
		cookie.setMaxAge(24*60*60);//һ��  
		response.addCookie(cookie);   
		 // �������뵽Cookie
 		cookie = new Cookie("autologinpwd",password);  
 		cookie.setPath("/");   
 		cookie.setMaxAge(24*60*60);  
 		response.addCookie(cookie);
		}
		//......


		// ��������

		try {

			if (user != null) {

				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				int u_id = user.getU_id();// ͨ��u_account�õ�u_id

				WeiboDao weiboDao = new WeiboDao();
				List<Weibo> lstPro = new ArrayList<Weibo>();
				lstPro = weiboDao.getAllWeibobyU_id(u_id, u_id,1);
				session.setAttribute("lstPro", lstPro);
			 
				//��ȡ������Ϣ
				RelaDao relaDao = new RelaDao();
				List<User> looklist = relaDao.getLook(u_id);				
				
				HttpSession session1 = request.getSession();
				session1.setAttribute("looklist", looklist);	
				
 
				session.setAttribute("ConcersNum", relaDao.showConcersNum(u_id));
				session.setAttribute("FansNum", relaDao.showFansNum(u_id));
				session.setAttribute("WeiboNum",weiboDao.showWeiboNum(u_id));

				request.getRequestDispatcher("/home.jsp").forward(request,
						response);

			} else {
				response.sendRedirect("../login.jsp?msg=5");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}

}
