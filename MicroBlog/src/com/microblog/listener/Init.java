package com.microblog.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.microblog.dao.GetCountDao;
import com.microblog.dao.LoginDaoImpl;

public class Init implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent context) {
		
		System.out.println("��� ����");
	}

	public void contextInitialized(ServletContextEvent context) {
		
		//List list = new StuDao().getStus();
		GetCountDao getCountDao = new GetCountDao();
		int rs = getCountDao.check();
		System.out.println("��ʼ������");
		
		context.getServletContext().setAttribute("info", rs);
		//context.getServletContext().setAttribute("info", "informations");
		
		//ͼƬ
		LoginDaoImpl dao = new LoginDaoImpl();
		
		context.getServletContext().setAttribute("students", dao.getUsers());
		
	}

}
