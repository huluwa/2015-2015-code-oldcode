package com.microblog.dao;

import java.sql.ResultSet;

import com.microblog.dbutil.DBConn;

public class GetCountDao {
	
		//���ʹ�õ�����
		public int check(){
			//��̬����һ��SQL���
			String strSQL = "select count(*) from user";
			//�������ݿ�����
			DBConn dbconn = new DBConn();
			//����dbconn��execu������ɲ�ѯ
			ResultSet rs1 = dbconn.execQuery(strSQL, new Object[]{});
			try {
				rs1.next();
				int rs2= rs1.getInt(1);
				return rs2;
				
			} catch (Exception e) {
				// TODO: handle exception
				return 0;
			}finally{
				dbconn.closeConn();}
			
				
			
			
	}
		
	public static void main(String[] args){
			
			System.out.print("rs");
		}
}
