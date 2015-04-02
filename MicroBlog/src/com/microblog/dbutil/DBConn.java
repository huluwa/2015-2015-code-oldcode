package com.microblog.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConn {
	//�����ԡ��ķ���
	
	//������Ľӿ�
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//�ĸ�����
	//method1: �������ݿ������
	public void getConntion(){		
		try {
			//1: ��������������Java����ԭ��
			Class.forName(Config.CLASS_NAME);
			//2:����Connection�ӿڶ������ڻ�ȡMySQL���ݿ�����Ӷ�������������url�����ַ���    �˺�  ����
			String url = Config.DATABASE_URL+"://"+Config.SERVER_IP+":"+Config.SERVER_PORT+"/"+Config.DATABASE_SID;
			conn = DriverManager.getConnection(url,Config.USERNAME,Config.PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	//method2���ر����ݿ�ķ���
	public void closeConn(){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

    
	//method3: ר�����ڷ�����ɾ�����ķ���
	public int execOther(final String strSQL,final Object[] params ){
		//1����ȡ���ݿ�����
		getConntion();
		//2��Ԥ�ȴ�ӡ������ִ�е�SQL���(������Ŀ���ԣ���Hibernate���)
		System.out.println("SQL:> "+strSQL);		
		try {
			//3������Statement�ӿڶ���
			pstmt = conn.prepareStatement(strSQL);
			//4����̬Ϊpstmt����ֵ
			for(int i=0; i< params.length ;i++){
				pstmt.setObject(i+1, params[i]);
			}
			//5��ʹ��Statement������SQL���
			int affectedRows = pstmt.executeUpdate();
			//6�����ؽ��
			return affectedRows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
    
	 public int getNumWeibo(int u_id,int u__id ){
	    	int nu=0;
	    	//String sql = "select * from weibo where w_id= any (select g_id from relation where (r_id=? and state=0) or (r_id=? and state=1)) or w_id=1 order by w_date desc";
	    	String sql="select count(*) from weibo where w_id= any (select g_id from relation where (r_id=? and state=0) or (r_id=? and state=1)) or w_id=1 order by w_date desc";
	    	
	    	getConntion();
	    	try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setObject(1, u_id);
				pstmt.setObject(2, u__id);
				rs = pstmt.executeQuery();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
				
				try {
					if(rs.next()){
						nu=rs.getInt(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(nu%5==0){
				nu=nu/5;
			}else{
				nu=nu/5+1;
			}
	    	return nu;
	    }

	//method4: ר�����ڷ��Ͳ�ѯ���
	public ResultSet execQuery(final String strSQL,final Object[] params){
		//1����ȡ���ݿ�����
		getConntion();
		//2��Ԥ�ȴ�ӡ������ִ�е�SQL���(������Ŀ���ԣ���Hibernate���)
		System.out.println("SQL:> "+strSQL);
		try {
			//3������PreparedStatement�ӿڶ���
			pstmt = conn.prepareStatement(strSQL);
			//4����̬Ϊpstmt����ֵ
			for(int i=0; i< params.length ;i++){
				pstmt.setObject(i+1, params[i]);
			}		
			//5��ʹ��Statement������SQL���
			rs = pstmt.executeQuery();
			//6�����ؽ��
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
