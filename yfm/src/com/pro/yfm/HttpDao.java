package com.pro.yfm;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import android.app.Dialog;
import android.util.Log;

public class HttpDao {
	private static String httpurl="http://120.199.60.50:8080/UserManage/";
	//private static String httpurl="http://192.168.123.1:8080/UserManage/";
	private static final int REQUEST_TIMEOUT = 10 * 1000;// ��������ʱ10����
	private static final int SO_TIMEOUT = 10 * 1000; // ���õȴ����ݳ�ʱʱ��10����
	private static DefaultHttpClient hc;
	private static BasicHttpParams httpParams;
	static {
		httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
		
	}

	public static String GetJsonData(String url, Dialog dialog) {

		Log.i("url", url);
		try {
			hc = new DefaultHttpClient(httpParams);
			HttpGet hg = new HttpGet(httpurl+url);
			HttpResponse hr = hc.execute(hg);
			Log.i("code", hr.getStatusLine().getStatusCode() + "");
			if (hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String con = EntityUtils.toString(hr.getEntity(), "UTF-8");
				Log.i("json", con);
				return con;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (dialog != null) {
				dialog.cancel();
				dialog = null;
			}
		}

		return null;
	}

	public static String PostData(String url, List<NameValuePair> np,
			Dialog dialog) {
		Log.i("url", url);
		try {
			hc = new DefaultHttpClient(httpParams);
			HttpPost hg = new HttpPost(httpurl+url);
			if (np != null && np.size() > 0) {
				hg.setEntity(new UrlEncodedFormEntity(np, "UTF-8"));
			}

			HttpResponse hr = hc.execute(hg);
			if (hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String con = EntityUtils.toString(hr.getEntity(), "UTF-8");
				Log.i("json", con);
				return con;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (dialog != null) {
				dialog.cancel();
				dialog = null;
			}
		}

		return null;
	}


	public static String GetJsonDatas(String url, Dialog dialog) {
		Log.i("url", url);
		try {
			hc = new DefaultHttpClient(httpParams);
			HttpGet hg = new HttpGet(httpurl+url);
			HttpResponse hr = hc.execute(hg);
			if (hr.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String con = EntityUtils.toString(hr.getEntity(), "UTF-8");
				Log.i("json", con);
				return con;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (dialog != null) {
				dialog.cancel();
				dialog = null;
			}
		}

		return null;
	}
}
