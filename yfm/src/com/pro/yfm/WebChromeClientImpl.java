package com.pro.yfm;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class WebChromeClientImpl extends WebChromeClient {

	private Handler handler;
	public WebChromeClientImpl(Handler handler){
		this.handler=handler;
	}
	@Override
	public boolean onJsConfirm(WebView view, String url, String message,
			final JsResult result) {

		Builder builder = new Builder(view.getContext());
		builder.setTitle("��ѡ��ĶԻ���");
		builder.setMessage(message);
		builder.setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener(){

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				result.confirm();
			}
			
		});
		builder.setNeutralButton(android.R.string.cancel, new AlertDialog.OnClickListener(){

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				result.cancel();
			}
			
		});
		builder.setCancelable(false);
		builder.create();
		builder.show();
		return true;

	}

	@Override
	public boolean onJsAlert(WebView view, String url, String message,
			final JsResult result) {
		//����һ��Builder����ʾ��ҳ�е�alert�Ի���
		Builder builder = new Builder(view.getContext());
		builder.setTitle("��ʾ�Ի���");
		builder.setMessage(message);
		builder.setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener(){

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				result.confirm();
			}
			
		});
		builder.setCancelable(false);
		builder.create();
		builder.show();
		return true;
	}

	@Override
	public void onProgressChanged(WebView view, int newProgress) {
		// TODO Auto-generated method stub
		Message msg = handler.obtainMessage();
		msg.what = WebviewActivity.pg;
		msg.arg1 = newProgress;
		handler.sendMessage(msg);
		super.onProgressChanged(view, newProgress);
	}
}
