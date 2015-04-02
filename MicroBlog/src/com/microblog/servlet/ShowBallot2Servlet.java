package com.microblog.servlet;

import java.awt.Font;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import com.microblog.dao.BallotDao;

public class ShowBallot2Servlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BallotDao ballotDao = new BallotDao();

		double sum = ballotDao.showSelectSum();
		double rs6 = ballotDao.showSelect6();
		double rs7 = ballotDao.showSelect7();
		double rs8 = ballotDao.showSelect8();
		double rs9 = ballotDao.showSelect9();
		double rs10 = ballotDao.showSelect10();

		double part1 = rs6 / sum;
		double part2 = rs7 / sum;
		double part3 = rs8 / sum;
		double part4 = rs9 / sum;
		double part5 = rs10 / sum;

		response.setContentType("text/html;charset=gbk");

		// �������ݼ�
		DefaultPieDataset dataset = new DefaultPieDataset();

		dataset.setValue("���ձȺ���", part1);
		dataset.setValue("������è2", part2);
		dataset.setValue("��������7", part3);
		dataset.setValue("���ν��3", part4);
		dataset.setValue("��������Ӱ�����������ʱ��", part5);

		// ͨ������������JFreeChart����
		JFreeChart chart = ChartFactory.createPieChart3D("��ϲ�������ڵ�Ӱ����Ʒ",
				dataset, true, true, false);

		chart.getTitle().setFont(new Font("΢���ź�", Font.BOLD, 20));

		chart.getLegend().setItemFont(
				new Font("΢���ź�", Font.LAYOUT_LEFT_TO_RIGHT, 10));

		// ���3D��ˮ����ͼ����
		PiePlot3D pieplot3d = (PiePlot3D) chart.getPlot();

		chart.getTitle().setFont(new Font("΢���ź�", Font.BOLD, 20));
		pieplot3d.setLabelFont(new Font("΢���ź�", 0, 12));

		chart.getLegend().setItemFont(
				new Font("΢���ź�", Font.LAYOUT_LEFT_TO_RIGHT, 10));

		pieplot3d.setLabelFont(new Font("΢���ź�", 0, 12));

		// ���ÿ�ʼ�Ƕ�
		pieplot3d.setStartAngle(150D);
		// ���÷���Ϊ��˳ʱ�뷽��
		pieplot3d.setDirection(Rotation.CLOCKWISE);
		// ����͸���ȣ�0.5FΪ��͸����1Ϊ��͸����0Ϊȫ͸��
		pieplot3d.setForegroundAlpha(0.5F);
		pieplot3d.setNoDataMessage("��������ʾ");

		String filename = ServletUtilities.saveChartAsPNG(chart, 550, 350,
				null, request.getSession());

		String graphURL = request.getContextPath() + "/DisplayChart?filename="
				+ filename;

		request.setAttribute("graphURL", graphURL);
		request.setAttribute("filename", filename);

		request.getRequestDispatcher("/sample.jsp").forward(request, response);

	}

}
