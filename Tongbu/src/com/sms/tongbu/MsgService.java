package com.sms.tongbu;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MsgService extends Service {

	private MsgReceiver recevier;
	private boolean isregiset = false;
	private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";  
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("--", "��������");
		regiset();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	public void regiset() {  
		Log.i("--", "ע��㲥");
        IntentFilter filter = new IntentFilter(ACTION);  
        filter.setPriority(1000);// �������ȼ����  
        recevier = new MsgReceiver();  
        this.registerReceiver(recevier, filter);  
        isregiset = true;  
        Toast.makeText(this, "��ʼ����", 0).show();  
    }  
    public void unregiset() {  
        if (recevier != null && isregiset) {  
        	this.unregisterReceiver(recevier);  
            isregiset = false;  
            Toast.makeText(this, "ֹͣ����,�رճ���", 0).show();  
        } else  
            Toast.makeText(this, "��δ���ã��رճ���", 0).show();  
    }
    @Override
    public void onDestroy() {  
        super.onDestroy();  
        if (recevier != null && isregiset) {  
            unregisterReceiver(recevier);  
            isregiset = false;  
            Toast.makeText(this, "ֹͣ����,�رճ���", 0).show();  
        }  
    }  

}
