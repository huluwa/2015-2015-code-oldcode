package com.microblog.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.microblog.po.User;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExportServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		doPost(request, response);
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		//��ǰ̨ҳ���ȡ�û���Ϣ
		String u_account = user.getU_account();
		String u_pwd = user.getU_pwd();
		String u_nickname = user.getU_nickname();
		String u_sex = user.getU_sex();
		String w_content = request.getParameter("w_content");
		
		//�������������������Ľ����ǿͻ��������
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment;filename = temp.xls");
		response.setContentType("application/msexcel");
		
		//������д���excel����������д�뵽����������ͨ��������������ͻ��������
		WritableWorkbook wk = Workbook.createWorkbook(output);
		WritableSheet sheet = wk.createSheet("�û���Ϣ��", 0);
		//�С���
		try {
			//�ѵ�Ԫ���С��У��ϲ�
			sheet.mergeCells(0,0, 4,0);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//��������
		WritableFont titleFont = new WritableFont(WritableFont.createFont("����"),12,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.LIGHT_BLUE);
		
	   WritableCellFormat titleFormat = new WritableCellFormat();
	   titleFormat.setFont(titleFont);
	   try {
		   //ˮƽ����
		titleFormat.setAlignment(Alignment.CENTRE);
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   
	   
	try {
		//��ֱ����
		titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		//������ɫ
		titleFormat.setBackground(Colour.GRAY_25);
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		//�Զ�����
		titleFormat.setWrap(true);
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//���label���󣬲����Դ˱�ʾ�ڵ�һ�С���һ�С����ݡ�ʹ�ø�ʽ
	Label lab_00 = new Label(0,0,"�û���Ϣ", titleFormat);
	try {
		
		//��Ӻõ�label������ӵ��������ϣ�
		sheet.addCell(lab_00);
	} catch (RowsExceededException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	WritableCellFormat columnTitleFormat = new WritableCellFormat();
	columnTitleFormat.setFont(new WritableFont(WritableFont.createFont("����"),10,WritableFont.BOLD,false));
	
	try {
		columnTitleFormat.setAlignment(Alignment.CENTRE);
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	Label lab_01 = new Label(0,1,"�û���",columnTitleFormat);
	Label lab_11 = new Label(1,1,"����",columnTitleFormat);
	Label lab_21 = new Label(2,1,"�ǳ�",columnTitleFormat);
	Label lab_31 = new Label(3,1,"�Ա�",columnTitleFormat);
	Label lab_41 = new Label(4,1,"΢��",columnTitleFormat);
	
	try {
		sheet.addCell(lab_01);
		sheet.addCell(lab_11);
		sheet.addCell(lab_21);
		sheet.addCell(lab_31);
		sheet.addCell(lab_41);
	} catch (RowsExceededException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	try {
		sheet.addCell(new Label(0,2,u_account));
		sheet.addCell(new Label(1,2,u_pwd));
		sheet.addCell(new Label(2,2,u_nickname));
		sheet.addCell(new Label(3,2,u_sex));
		sheet.addCell(new Label(4,2,w_content));
		
	} catch (RowsExceededException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//����������Ĺ�����������ͻ��������
	wk.write();
	try {
		//�رն���
		wk.close();
	} catch (WriteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	}
	
}
