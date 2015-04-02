package com.iptv.thread;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.RandomAccessFile;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;
/**
 * �����߳�
 * @author ���
 */
public class DownLoadThread extends Thread {



	private boolean isdown=false;
	private HttpClient hc=new DefaultHttpClient();
	private String uri;
	private File file;
	private Long filesize;
	private DownloadThreadListener listener;
	public void setListener(DownloadThreadListener listener) {
		this.listener = listener;
	}
	public DownLoadThread(String uri,File file){
		this.uri=uri;
		this.file=file;
	}
	public DownLoadThread(String uri,File file,DownloadThreadListener listener){
		this.uri=uri;
		this.file=file;
		this.listener=listener;
	}
	@Override
	public void run() {
		super.run();
		try {
			isdown=true;
			if(file.exists()){
				file.createNewFile();
			}
			filesize=file.length();
			HttpGet hg=new HttpGet(uri);
			hg.addHeader("Range", "bytes="+filesize+"-");
			Log.i("tvinfo", "��ʼ����");
			HttpResponse  res=hc.execute(hg);
			int statecode=res.getStatusLine().getStatusCode();
			Log.i("tvinfo", "���ؿ�ʼ"+statecode);
			if(statecode==206||statecode==416){
				BufferedInputStream in=new BufferedInputStream(res.getEntity().getContent());
				Long count=res.getEntity().getContentLength()+file.length();
				RandomAccessFile raf=new RandomAccessFile(file, "rw");
				raf.seek(filesize);
				int rcount = 0;byte[] buffer=new byte[1024];
				while((rcount=in.read(buffer,0,buffer.length))>=0&&isdown){
					raf.write(buffer, 0, rcount);
					if(listener!=null){
						listener.afterPerDown(uri, count, file.length());
					}
					
				}
				raf.close();
				if(listener!=null){
					Log.i("tvinfo", "�������");
					listener.downCompleted(uri, count, file.length(), isdown,file);
				}
			}else{
				listener.returncode(statecode);
			}
			hg.abort();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			listener.returncode(504);
		} finally{
			isdown=false;
			hc.getConnectionManager().shutdown();
		}
	}

	public void stopdownload(){
		isdown=false;
	}
}
