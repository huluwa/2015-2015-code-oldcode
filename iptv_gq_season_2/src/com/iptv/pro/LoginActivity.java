package com.iptv.pro;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.iptv.season.R;
import com.iptv.thread.LoginThread;
import com.iptv.utils.HttpUtils;
import com.iptv.utils.Utils;

public class LoginActivity extends Activity {

	private ProgressDialog pd;
	private SharedPreferences sp;
	private LoginThread loginthread;
	private EditText name;
	private EditText pass;
	private Button loginbut;
	private AlertDialog alert;
	private TextView mac;
	private HttpUtils hu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sp = this.getSharedPreferences("key", Context.MODE_PRIVATE);
		String active = sp.getString("name", "");
		String password = sp.getString("password", "");
		setContentView(R.layout.activity_login);
		hu=new HttpUtils(this);
		name = (EditText) super.findViewById(R.id.name);
		pass = (EditText) super.findViewById(R.id.pass);
		loginbut = (Button) super.findViewById(R.id.loginbut);
		name.setText(active);
		pass.setText(password);
		loginbut.setOnClickListener(new OnClickListenerImpl());
		mac=(TextView)super.findViewById(R.id.mac);
		mac.setText(Utils.getLocalMacAddressFromIp(this));
		autologin();
	}

	class OnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View but) {
			// TODO Auto-generated method stub
			if (but.getId() == R.id.loginbut) {
				String active = name.getText().toString();
				String password = pass.getText().toString();
				if("".equals(active)||"".equals(password)){
					showalert("��Ϣ","�û��������벻��Ϊ��");
				}else{
					autologin();
				}
			}
			

		}

	}
	private void autologin(){
		String active = name.getText().toString();
		String password = pass.getText().toString();
		if (!"".equals(active) && !"".equals(password)) {
			String url = "live.jsp?active="
					+ active
					+ "&pass="
					+ Utils.md5(password,null)
					+ "&mac="
					+ Utils.getLocalMacAddressFromIp(LoginActivity.this);
			show();
			loginthread = new LoginThread(this,handler, url);
			loginthread.start();
		}
	}
	private void showalert(String title, String msg) {
		Builder builder = new Builder(this);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton("ȷ��", null);
		alert = builder.create();
		alert.show();
	}

	private void show() {
		pd = new ProgressDialog(LoginActivity.this,
				ProgressDialog.THEME_HOLO_DARK);
		pd.setTitle("���ڽ�����֤");
		pd.setMessage("���ڽ�����֤,���Ժ�...");
		pd.setCancelable(false);
		pd.show();
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			String active = name.getText().toString();
			String password = pass.getText().toString();
			pd.dismiss();
			if (msg.what == 1) {
				//��¼�ɹ�
				savedate(active,password);
				tohome();
			} else if (msg.what == -1) {
				if(msg.arg1==1){
					showalert("��Ϣ", "���ӷ�����ʧ��");
				}else if(msg.arg1==2){
					pass.setText("");
					savedate(active,"");
					showalert("��Ϣ", "��֤ʧ��,�û������������");
				}
			}
		}

	};
	public void savedate(String active,String password){
		Editor editor = sp.edit();
		editor.putString("name", active);
		editor.putString("password", password);
		editor.commit();
	}
	public void tohome(){
		Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		this.finish();
	}

}
