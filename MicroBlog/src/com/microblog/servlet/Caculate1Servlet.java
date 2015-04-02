package com.microblog.servlet;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.TextAnchor;

public class Caculate1Servlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

         response.setContentType("text/html;charset=utf-8");
		
         double[][] data = new double[][] { { 2 }, { 4 }, { 7 }, { 4 }, { 3 } , { 3 }  };
		
		String[] rowKeys = { "��ʿ", "�о���", "����", "����","����","Сѧ" };
		
		String[] columnKeys = { "" };
		
		
		
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
				rowKeys, columnKeys, data);
		
		Font font = new Font("����", Font.BOLD, 16);
		
		JFreeChart chart = ChartFactory.createBarChart3D("�û�ͶƱ", "ͶƱ",
				"ѧ��", dataset, PlotOrientation.VERTICAL, true, false, false);
		
		CategoryPlot plot = chart.getCategoryPlot();
		TextTitle title = new TextTitle("�û�ͶƱ", font);
		

		TextTitle subtitle = new TextTitle("������", new Font("����", Font.BOLD,
				12));
		
		chart.addSubtitle(subtitle);

		chart.setTitle(title); //����
		
		NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();

		CategoryAxis domainAxis = plot.getDomainAxis();

		/*------����X�������ϵ�����-----------*/

		domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));

		/*------����X��ı�������------------*/

		domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));
		

		/*------����Y�������ϵ�����-----------*/

		numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));

		/*------����Y��ı�������------------*/

		numberaxis.setLabelFont(new Font("����", Font.PLAIN, 12));

		/*------���������˵ײ��������������-----------*/

		chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));
		
		
		//�������񱳾���ɫ

		plot.setBackgroundPaint(Color.white);

		//��������������ɫ

		plot.setDomainGridlinePaint(Color.pink);

		//�������������ɫ

		plot.setRangeGridlinePaint(Color.pink);
		
		
		//��ʾÿ��������ֵ�����޸ĸ���ֵ����������

		BarRenderer3D renderer = new BarRenderer3D();

		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());

		renderer.setBaseItemLabelsVisible(true);
		
		//Ĭ�ϵ�������ʾ�������У�ͨ����������ɵ������ֵ���ʾ

		//ע�⣺�˾�ܹؼ������޴˾䣬�����ֵ���ʾ�ᱻ���ǣ���������û����ʾ����������

		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));

		renderer.setItemLabelAnchorOffset(10D);

		renderer.setItemLabelFont(new Font("����", Font.PLAIN, 12));

		renderer.setItemLabelsVisible(true);
		
		//����ÿ��������������ƽ������֮�����

		//renderer.setItemMargin(0.2);

		plot.setRenderer(renderer);
		
		
		
		String filename = ServletUtilities.saveChartAsPNG(chart, 550, 350,
				null, request.getSession());
		
		String graphURL = request.getContextPath() + "/DisplayChart?filename="
				+ filename;
		
		request.setAttribute("graphURL",graphURL);
		request.setAttribute("filename", filename);

		request.getRequestDispatcher("/sample.jsp").forward(request, response);
	

	}

		
	}


