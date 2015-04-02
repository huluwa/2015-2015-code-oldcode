package com.iptv.thread;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.iptv.utils.HttpUtils;


public class LoginThread extends Thread{

	private Handler handler;
	private String url;
	private HttpUtils hu;
	private Context context;
	public LoginThread(Context context,Handler handler,String url){
		this.context=context;
		this.handler=handler;
		this.url=url;
		this.hu=new HttpUtils(context);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			Message msg=handler.obtainMessage();
			String xml=hu.doget(HttpUtils.baseurl+url);
			if(xml!=null){
				boolean isok=hu.parsexml(xml);
				if(isok){
					msg.what=1;//��֤�ɹ�
				}else{
					msg.what=-1;
					msg.arg1=2;//��֤ʧ��;
				}
			}else{
				msg.what=-1;
				msg.arg1=1;//���粻ͨ
			}
			handler.sendMessage(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
