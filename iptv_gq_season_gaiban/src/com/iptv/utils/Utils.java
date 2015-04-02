package com.iptv.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

public class Utils {

	private static SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
	public static String getLocalMacAddressFromIp(Context context) {
		String uniqueId="b3e18e619ffc8689";
		try {
			uniqueId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
			Log.d("debug", "uuid=" + uniqueId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uniqueId;
	}

	

	public static String md5(String str, String charset) {
		try {
			byte[] tmpInput = null;
			if (null != str) {
				if (null != charset) {
					tmpInput = str.getBytes(charset);
				} else {
					tmpInput = str.getBytes();
				}
			} else {
				return null;
			}
			MessageDigest alg = MessageDigest.getInstance("MD5"); // or "SHA-1"
			alg.update(tmpInput);
			return byte1hex(alg.digest());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static String byte1hex(byte[] inputByte) {
		if (null == inputByte) {
			return null;
		}
		String resultStr = "";
		String tmpStr = "";
		for (int n = 0; n < inputByte.length; n++) {
			tmpStr = (Integer.toHexString(inputByte[n] & 0XFF));
			if (tmpStr.length() == 1) {
				resultStr = resultStr + "0" + tmpStr;
			} else {
				resultStr = resultStr + tmpStr;
			}
		}
		return resultStr.toUpperCase();
	}


	public static int pageleft(int start, int end, int count) {
		int vcount = end - start;
		if ((start-vcount)>0) {
				return (start-vcount-1);
		}else{
			return 0;
		}
	}
	public static int pageright(int start, int end, int count) {
		if (end+1==count) {
				return end;
		}else{
			return end+1;
		}
	}
	public static int getversioncode(Context context){
		PackageManager manager = context.getPackageManager();
		try {
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			return info.versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	public static String longToString(long time){
		
		return sdf.format(time);
	}
	public static void setConfig(Context context,String key,String value){
		SharedPreferences sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		Editor  editor=sp.edit();
		editor.putString(key, value);
		editor.commit();
	}
	public static String getConfig(Context context,String key,String def){
		SharedPreferences sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getString(key, def);
	}
	public static void setConfig(Context context,String key,int value){
		SharedPreferences sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		Editor  editor=sp.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	public static int getConfig(Context context,String key,int def){
		SharedPreferences sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getInt(key, def);
	}
	public static void setConfig(Context context,String key,boolean value){
		SharedPreferences sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		Editor  editor=sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	public static boolean getConfig(Context context,String key,boolean def){
		SharedPreferences sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getBoolean(key, def);
	}
	public static final String[] CaBanner_chinese =
		{
		/* 00 */"E00 ��ǰ��Ŀû�м���",
		/* 01 */"E01 �����CAģ��",
		/* 02 */"E02 CAģ��EEPROMʧ��",
		/* 03 */"E03 CAģ��ʧ��",
		/* 04 */"E04 ��������ܿ�",
		/* 05 */"E05 ϵͳ����ʶ�˿�",
		/* 06 */"E06 ���ܿ�ʧ��",
		/* 07 */"E07 ���ڼ�����ܿ�",
		/* 08 */"E08 CAģ��ʧ��",
		/* 09 */"E09 ���ܿ�EEPROMʧ��",
		/* 10 */"E10 ",
		/* 11 */"E11 ������ƥ��",
		/* 12 */"E12 ����ĸ��ͬ��",
		/* 13 */"E13 û����Ч�Ľ�Ŀ",
		/* 14 */"E14 ��Ŀû����Ȩ",
		/* 15 */"E15 ��Ȩ���յ�",
		/* 16 */"E16 ��ǰ��Ŀ�Ѽ���",
		/* 17 */"E17 �ѹ����¼�",
		/* 18 */"E18 Ԥ���׶�",
		/* 19 */"E19 �¼��Ѿ�����",
		/* 20 */"E20 ����Ӫ����Ȩ",
		/* 21 */"E21 �������ܹ���",
		/* 22 */"E22 ������������",
		/* 23 */"E23 ��ǰ��Ŀ���ڽ���",
		/* 24 */"E24 ��ǰ��Ŀ�Ѽ���",
		/* 25 */"E25 ���ܿ�������",
		/* 26 */"E26 ϵͳ����ʶ��ǰ��Ŀ",
		/* 27 */"E27 ��ǰ��Ŀ��������",
		/* 28 */"E28 ������ǰ��Ŀ",
		/* 29 */"E29 �������ڴ���",
		/* 30 */"E30 ��ǰ��Ŀ��Ч",
		/* 31 */"E31 ��ĸ��������",
		/* 32 */"E32 ���Ҵ���û����Ȩ",
		/* 33 */"E33 û���¼���Ϣ",
		/* 34 */"E34 ��Ŀû����Ȩ",
		/* 35 */"E35 û���ź�",
	/* 36 */"E36 ", };
}
