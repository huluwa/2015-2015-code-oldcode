package com.yfm.sms;


public class SendMailThread extends Thread {

	private SendMail sm;
	public SendMailThread(SendMail sm){
		this.sm=sm;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Loginfo.write("��ʱ����", "��ʱ������");
		sm.SendMail();
	}

}
