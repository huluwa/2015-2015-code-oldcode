package com.yfm.ydbg;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class DbsxService extends Service {

	private NotificationManager nm;
	private Notification nf;
	private PendingIntent contentIntent;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		nm=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
		Intent intent=new Intent(this,MainActivity.class);
		contentIntent=PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		create();
	}
	public void create(){
		nf=new Notification(R.drawable.logo,"�ƶ��칫ϵͳ��̨����",System.currentTimeMillis());
		nf.flags=Notification.FLAG_ONGOING_EVENT;
		nf.setLatestEventInfo(this, "��������", "�ƶ��칫ϵͳ��������", contentIntent);
		nm.notify(0, nf);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		nm.cancel(0);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
