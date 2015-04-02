package com.iptv.pro;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.iptv.season.R;
import com.iptv.thread.UserInfoThread;
import com.iptv.utils.HttpUtils;
import com.iptv.utils.Utils;
import com.tr.decryption.Decryption;

public class HomeActivity extends Activity {

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		showExit();
	}

	private void showExit() {
		Builder builder = new Builder(this, AlertDialog.THEME_HOLO_DARK);
		builder.setTitle("�˳�");
		builder.setMessage("ȷ��Ҫ�˳�Ӧ��");
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				HomeActivity.this.finish();
			}
		});
		builder.setNegativeButton("ȡ��", null);
		builder.create().show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == rc && resultCode == RESULT_OK) {
			Uri uri = data.getData();
			Intent video = getVideoFileIntent(uri);
			startActivity(video);
		}
	}

	// android��ȡһ�����ڴ���Ƶ�ļ���intent
	public static Intent getVideoFileIntent(Uri uri) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("oneshot", 0);
		intent.putExtra("configchange", 0);
		intent.setDataAndType(uri, "video/*");
		return intent;
	}

	private ImageView livetv, playback, webmovie, localfile, market,
			systemsetup, info;
	private HttpUtils hu;
	private SharedPreferences sp;
	private ProgressDialog pd;
	private int rc = 100;
	private Decryption decry;
	private TextView time;
	private StringBuffer sb=new StringBuffer("");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		sp = this.getSharedPreferences("key", Context.MODE_PRIVATE);
		hu = new HttpUtils(this);
		livetv = (ImageView) super.findViewById(R.id.livetv);
		playback = (ImageView) super.findViewById(R.id.playback);
		webmovie = (ImageView) super.findViewById(R.id.webmovie);
		localfile = (ImageView) super.findViewById(R.id.localfile);
		market = (ImageView) super.findViewById(R.id.market);

		time=(TextView)super.findViewById(R.id.time);
		
		systemsetup = (ImageView) super.findViewById(R.id.systemsetup);
		info = (ImageView) super.findViewById(R.id.info);
		playback = (ImageView) super.findViewById(R.id.playback);
		playback = (ImageView) super.findViewById(R.id.playback);

		livetv.setOnClickListener(new OnClickListenerImpl());
		playback.setOnClickListener(new OnClickListenerImpl());
		webmovie.setOnClickListener(new OnClickListenerImpl());
		localfile.setOnClickListener(new OnClickListenerImpl());
		market.setOnClickListener(new OnClickListenerImpl());
		systemsetup.setOnClickListener(new OnClickListenerImpl());
		info.setOnClickListener(new OnClickListenerImpl());
		decry = new Decryption(HomeActivity.this, handler);
		decry.initTRDecryptionServer();
		
	}

	class OnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View but) {
			// TODO Auto-generated method stub

			if (but.getId() == R.id.livetv) {
				Intent intent = new Intent(HomeActivity.this,
						PlayActivity.class);
				HomeActivity.this.startActivity(intent);
			}
			if (but.getId() == R.id.playback) {
				Intent intent = new Intent(HomeActivity.this,
						BackActivity.class);
				HomeActivity.this.startActivity(intent);
			}
			if (but.getId() == R.id.webmovie) {
				openCLD("com.android.browser");
			}
			if (but.getId() == R.id.localfile) {
				// openvideofile();
			}
			if (but.getId() == R.id.market) {
				Openhome();
			}
			if (but.getId() == R.id.systemsetup) {
				openCLD("com.android.settings");
			}
			if (but.getId() == R.id.info) {
				showinfo(sb.toString());
			}
		}

	}

	private void openvideofile() {
		Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT);
		innerIntent.setType("video/*"); // String VIDEO_UNSPECIFIED = ;
		Intent wrapperIntent = Intent.createChooser(innerIntent, null);
		startActivityForResult(wrapperIntent, rc);
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 100) {
				if (msg.arg1 == 1) {
					Map<String, String> umap=(Map<String, String>)msg.obj;
					sb=new StringBuffer();
					time.setText(umap.get("user"));
					sb.append("����ʱ�䣺" + umap.get("user")+"\r\n");
					sb.append("��¼��ʶ��" + Utils.getLocalMacAddressFromIp(HomeActivity.this)+"\r\n");
					if(umap.containsKey("index")){
						sb.append("�豸�ţ�" + umap.get("index")+"\r\n");
					}
				}else{
					
				}
			}
			if(msg.what==Decryption.HANDLER_USB_DEVICES_INIT_SUCCEE){
				//��ʼ���ɹ�
				new UserInfoThread(decry, handler, "getUserDate.jsp?active=" + sp.getString("name", "")) .start();
			}else if(msg.what==Decryption.HANDLER_USB_DEVICES_INIT_FAILE){
				//��ʼ��ʧ��
				showinfo("δ��⵽�豸���߳�ʼ���豸ʧ��");
			}
			
		}

	};

	private void Openhome() {
		Intent intent = new Intent(this, AllAppActivity.class);
		this.startActivity(intent);
	}

	private void show(String title, String msg) {
		pd = new ProgressDialog(this, ProgressDialog.THEME_HOLO_DARK);
		pd.setTitle(title);
		pd.setMessage(msg);
		pd.show();
	}

	private void showinfo(String message) {
		Builder builder = new Builder(this, AlertDialog.THEME_HOLO_DARK);
		builder.setTitle("��Ϣ");
		View view = LayoutInflater.from(this).inflate(R.layout.userinfo, null);
		TextView versioncode = (TextView) view.findViewById(R.id.versioncode);
		ListView listdata=(ListView)view.findViewById(R.id.listdata);
		listdata.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, UserInfoThread.ls));
		versioncode.setText(getVersionName());
		TextView msg = (TextView) view.findViewById(R.id.message);
		CheckBox autostart = (CheckBox) view.findViewById(R.id.autostart);
		boolean isauto = sp.getBoolean("autostart", false);
		autostart.setChecked(isauto);
		autostart.setOnCheckedChangeListener(new OnCheckedChangeListenerImpl());
		msg.setText(message);
		builder.setView(view);
		builder.setPositiveButton("�ر�", null);
		builder.create().show();
	}

	private String getVersionName() {
		String version = "";
		try {
			PackageManager packageManager = getPackageManager();
			PackageInfo packInfo = packageManager.getPackageInfo(
					getPackageName(), 0);
			version = packInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return version;
	}

	class OnCheckedChangeListenerImpl implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
			// TODO Auto-generated method stub
			Editor editor = sp.edit();
			editor.putBoolean("autostart", arg1);
			editor.commit();
		}

	}

	public boolean openCLD(String packageName) {
		PackageManager packageManager = this.getPackageManager();
		PackageInfo pi = null;
		try {
			pi = packageManager.getPackageInfo(packageName, 0);
		} catch (NameNotFoundException e) {
			Log.i("tvinfo", "packageName������" + packageName);
		}
		if (pi == null) {
			return false;
		}
		Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
		resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		resolveIntent.setPackage(pi.packageName);

		List<ResolveInfo> apps = packageManager.queryIntentActivities(
				resolveIntent, 0);
		ResolveInfo ri = apps.iterator().next();
		if (ri != null) {
			String className = ri.activityInfo.name;

			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);

			ComponentName cn = new ComponentName(packageName, className);

			intent.setComponent(cn);
			this.startActivity(intent);
			return true;
		}
		return false;

	}
}
