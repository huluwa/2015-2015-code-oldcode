package com.iptv.thread;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.iptv.pojo.TvInfo;
import com.iptv.utils.HttpUtils;

public class PlayThread extends Thread {

	private Handler handler;
	private HttpUtils hu;
	private boolean isqh = true;
	private TvInfo tvinfo;
	private String userid;
	private String hotlink;
	private Context context;

	public PlayThread(Context context, Handler handler, String userid,
			TvInfo tvinfo) {
		this.handler = handler;
		this.tvinfo = tvinfo;
		this.userid = userid;
		this.context = context;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			sendmsg(1, "�����л��� ");
			Thread.sleep(200);
			if (!isqh) {
				return;
			}
			hu = new HttpUtils(context);
			if (tvinfo.getHttpurl().startsWith("http")) {
				hu.doget(tvinfo.stopsoplay());
				sendmsg(3, "��ʼ���� ");
			} else {
				hu = new HttpUtils(context);
				String xml = hu.doget(tvinfo.getparam(userid));
				Log.i("tvinfo", xml);
				if (xml != null && isqh) {
					sendmsg(2, "��ʼ����");
					if (isqh) {
						sendmsg(3, "��ʼ���� ");
					} else {
						sendmsg(4, "����ʧ�� ");
					}
				} else {
					sendmsg(4, "�л�ʧ�� ");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() {
		isqh = false;
	}

	public void sendmsg(int i, String message) {
		Message msg = handler.obtainMessage();
		msg.what = i;
		msg.obj = message;
		handler.sendMessage(msg);
	}

}
