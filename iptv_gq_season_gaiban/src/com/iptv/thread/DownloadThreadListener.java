package com.iptv.thread;
/**
 * 
 */


import java.io.File;

/**
 * �����߳��¼�
 * @author ���
 */
public interface DownloadThreadListener {

	/**
	 * ÿ��������һ���ֽ�����󴥷� 
	 * @param count �ܴ�С��rcount �Ѷ�ȡ����
	 */
	public void afterPerDown(String uri,long count,long rcount);
	
	/**
	 * �������ʱ����
	 * @param count �ܴ�С��rcount �Ѷ�ȡ���ݣ�isdown�Ƿ�ֹͣ���� false ֹͣ����  true�������
	 */
	public void downCompleted(String uri,long count,long rcount,boolean isdown,File file);
	/**
	 * �������ʱ����
	 * @param ״̬
	 */
	public void returncode(int statecode);
}
