package com.yfm.control;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import android.content.Context;
import android.content.SharedPreferences;

public class Instruction {
	private static byte[] temp = new byte[32];

	static {
		temp[0] = itob(0x01);
		temp[1] = itob(0x00);
		temp[2] = itob(0x01);
		temp[3] = itob(0x02);
		temp[4] = itob(0x00);
		temp[5] = itob(0x00);
		temp[6] = itob(0x00);
		temp[7] = itob(0x00);
		temp[8] = itob(0x00);
		temp[9] = itob(0x00);
		temp[10] = itob(0x00);
		temp[11] = itob(0x00);
		temp[12] = itob(0x00);
		temp[13] = itob(0x00);
		temp[14] = itob(0x00);
		temp[15] = itob(0x00);
		temp[16] = itob(0x00);
		temp[17] = itob(0xAA);
		temp[18] = itob(0x40);
		temp[20] = itob(0x00);// Ӧ����
		temp[21] = itob(0x00);// ��������
		temp[22] = itob(0x08);// ���ݳ���
		temp[23] = itob(0x00);// ���ݳ���
		temp[28] = itob(0x00);
		temp[29] = itob(0x00);
		temp[30] = itob(0x00);
		temp[31] = itob(0x00);
	}

	/**
	 * 
	 * @param b1
	 *            �������
	 * @param b2
	 *            ���Ƶļҵ�Ʒ��
	 * @param b3
	 *            �ҵ��ͨ��
	 * @param b4
	 *            ����������
	 * @param b5
	 *            ��ʾ������ѯ
	 * @param pass
	 *            ��������
	 * @return
	 */
	public static byte[] setNum(byte b1, byte b2, byte b3, byte b4, byte b5,
			String pass) {
		temp[19] = b1;// ������
		temp[24] = b2;
		temp[25] = b3;
		temp[26] = b4;
		temp[27] = b5;
		setPass(pass);
		return temp;
	}

	/**
	 * 
	 * @param b1
	 *            �������
	 * @param b2
	 *            ���Ƶļҵ�Ʒ��
	 * @param b3
	 *            �ҵ��ͨ��
	 * @param b4
	 *            ����������
	 * @param b5
	 *            ��ʾ������ѯ
	 * @param pass
	 *            ��������
	 * @return
	 */
	public static byte[] setNum(int b1, int b2, int b3, int b4, int b5,
			String pass) {
		temp[19] = itob(b1);// ������
		temp[24] = itob(b2);
		temp[25] = itob(b3);
		temp[26] = itob(b4);
		temp[27] = itob(b5);
		setPass(pass);
		return temp;
	}

	public static byte itob(int n) {
		return (byte) (n & 0xff);
	}

	private static int zh(String st) {
		int temp = 0;
		String t = st.substring(0, 1);
		String y = st.substring(1, 2);
		temp = Integer.parseInt(t) * 16 + Integer.parseInt(y);
		return temp;
	}

	private static void setPass(String pass) {
		if (pass.length() < 6) {
			return;
		}
		pass=pass+"00";
		byte[] ch = new byte[4];
		for (int j = 0, i = 0; i <= 6; i = i + 2, j++) {
			String t = pass.substring(i, i + 2);
			ch[j] = itob(zh(t));
		}
		temp[28] = ch[0];
		temp[29] = ch[1];
		temp[30] = ch[2];
		temp[31] = ch[3];
	}
	/**
	 * 
	 * @param context
	 * @param type 1:�ҵ����� 2����������
	 * @return
	 */
	public static String getpass(Context context,int type) {
		SharedPreferences sp=context.getSharedPreferences("set", Context.MODE_PRIVATE);
		if(1==type){
			return sp.getString("jdpass", "");
		}
		if(2==type){
			return sp.getString("afpass", "");
		}
		return null;
	}

}
