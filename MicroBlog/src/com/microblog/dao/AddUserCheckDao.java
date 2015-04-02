package com.microblog.dao;

import java.sql.ResultSet;

import com.microblog.dbutil.DBConn;

public class AddUserCheckDao {
	//��֤�û����Ƿ��Ѿ�ʹ��
	public boolean check(final String userid){
		//��̬����һ��SQL���
		String strSQL = "select count(*) from user where u_account = ?";
		//�������ݿ�����
		DBConn dbconn = new DBConn();
		//����dbconn��execu������ɲ�ѯ
		ResultSet rs1 = dbconn.execQuery(strSQL, new Object[]{userid});
		//��ȡrs1��ֵ�����ж����ݿ���֤�Ƿ�ɹ�
		try {
			rs1.next();
			return rs1.getInt(1)>0? true:false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally{
			dbconn.closeConn();
		}
	}
//	public static void main(String[] args){
//	AddUserCheckDao adminDao = new AddUserCheckDao();
//	System.out.print(adminDao.check("dfd"));
//}

}
