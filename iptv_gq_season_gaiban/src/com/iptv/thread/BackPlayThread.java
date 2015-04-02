package com.iptv.thread;

import com.iptv.utils.HttpUtils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


public class BackPlayThread extends Thread{

	private Handler handler;
	private String url;
	private HttpUtils hu;
	private boolean isqh=true;
	public BackPlayThread(Handler handler,String url){
		this.handler=handler;
		this.url=url;
		hu=new HttpUtils();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			sendmsg(1,"�����л��� ");
			Log.i("tvinfo", url);
			String xml=hu.doget(url);
			Log.i("tvinfo", xml);
			if(xml!=null&&isqh){
				sendmsg(2,"��ʼ����");
				if(isqh){
					sendmsg(3,"��ʼ���� ");
				}else{
					sendmsg(4,"����ʧ�� ");
				}
			}else{
				sendmsg(4,"�л�ʧ�� ");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void close(){
		isqh=false;
	}
	public void sendmsg(int i,String message){
		Message msg=handler.obtainMessage();
		msg.what=i;
		msg.obj=message;
		handler.sendMessage(msg);
	}
	
}
