package com.microblog.jfc;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

public class Pie {
	public static void main(String[] args) {
		//����
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("����", 43.2);
		dataset.setValue("Category 2", 27.9);
		dataset.setValue("Category 3", 79.5);
		dataset.setValue("Category 4", null);
		dataset.setValue("Category 5", 0);
		dataset.setValue("Category 6", -50.0f);
		//���� Jfreechart����
		JFreeChart jfreechart = ChartFactory.createPieChart("Simple Pie Chart", dataset, true, true, false);
		PiePlot piePlot = (PiePlot) jfreechart.getPlot();
		//ָ�����������ɫ
		piePlot.setSectionPaint("����",Color.blue);
		piePlot.setSectionPaint("Category 2",Color.red);
		piePlot.setSectionPaint("Category 3",Color.yellow);
		
		//����Pie�ı߿��Ƿ�ɼ�
		piePlot.setSectionOutlinesVisible(true);
		// ָ��ͼƬ��͸����(0.0-1.0)
		piePlot.setForegroundAlpha(1.0f);
		//���ñ߿����ɫ
		piePlot.setBaseSectionOutlinePaint(Color.green);
		//���ñ߿�Ĵ�ϸ,new BasicStroke(2.0f)
		piePlot.setBaseSectionOutlineStroke(new BasicStroke(1));
		//���ÿ�ֵ,0ֵ,��ֵ�Ƿ���ʾ����,�����ʾ�Ļ�����false
		piePlot.setIgnoreNullValues(true);
		piePlot.setIgnoreZeroValues(true);
		
		//�����������ʽ,0��ʾKEY,1��ʾVALUE,2��ʾ�ٷ�֮��,DecimalFormat������ʾ�ٷֱȵĸ�ʽ
		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}=>{1}({2})",NumberFormat.getNumberInstance(),new DecimalFormat("0.00%")));
		
		//�������淽�����ʽ,0��ʾKEY,1��ʾVALUE,2��ʾ�ٷ�֮��,DecimalFormat������ʾ�ٷֱȵĸ�ʽ
		piePlot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}=>{1}({2})",NumberFormat.getNumberInstance(),	new DecimalFormat("0.00%")));
		
		//��ըģʽ,ʹPie��һ������ȥ,��֧��3D
		piePlot.setExplodePercent("Category 1", 0.10);
		piePlot.setExplodePercent("Category 2", 0.10);
		//piePlot.setExplodePercent("Category 3", 0.10);
		
		//���������ʽ
		Font font = new Font("΢���ź�", Font.CENTER_BASELINE, 12);
		//����ͼƬ����
		TextTitle title = new TextTitle("Pie״ͼ");
		//���ñ���ĸ�ʽ
		title.setFont(font);
		//�ѱ������õ�ͼƬ����
		jfreechart.setTitle(title);
		
		//��������,�ǳ��ؼ���Ȼ����������,�·�������
		jfreechart.getLegend().setItemFont(font);
		//Pieͼ������
		piePlot.setLabelFont(font);
		
		//���������
		FileOutputStream fos_jpg = null;
		try {			
			fos_jpg = new FileOutputStream("c:\\Pie.jpg");
			//�ù��߰�ͼ��д��Ӳ��,֧�����ָ�ʽ,JPG,PNG,��֧��MAP
			ChartUtilities.writeChartAsJPEG(fos_jpg,0.99f,jfreechart,640,480,null);
			fos_jpg.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
