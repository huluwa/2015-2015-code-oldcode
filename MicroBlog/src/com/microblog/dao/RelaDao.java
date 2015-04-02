package com.microblog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.microblog.dbutil.DBConn;

import com.microblog.po.User;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

public class RelaDao {

	public boolean Befans(final String r_id, final String g_id)//�ӹ�ע
			throws SQLException {

		String sql_check1 = "select count(*) from relation where r_id=? and g_id=?";

		DBConn dbConn = new DBConn();
		ResultSet rs = dbConn.execQuery(sql_check1, new Object[] { r_id, g_id });

		rs.next();
		int check1 = rs.getInt(1);

		String sql_check2 = "select count(*) from  relation where g_id=? and r_id=?";
		rs = dbConn.execQuery(sql_check2, new Object[] { r_id, g_id });
		rs.next();
		int check2 = rs.getInt(1);

		if (check1 == 1) { // �ѹ�ע ���ش���

			rs.close();
			dbConn.closeConn();
			return false;

		}
		if (check1 == 0) { // δ��ע�������ж�
			String sql_insert = "insert into relation (r_id,g_id,state) values(?,?,?)";// ͨ�ã�state�б仯
			if (check2 == 0) {// ���ӹ�ע��֮ǰû�м��û���ע

				int result = dbConn.execOther(sql_insert, new Object[] { r_id,
						g_id, 0 }); // ��ӶԷ�Ϊ��ע

				rs.close();
				dbConn.closeConn();
				return result > 0 ? true : false;

			}
			if (check2 == 1) {// ��������Ѿ���Ӹ��û���ע

				dbConn.execOther(sql_insert, new Object[] { r_id, g_id, 1 });// �������

				String sql_update = "update relation set state=1 where r_id=? and g_id=?";

				int result = dbConn.execOther(sql_update, new Object[] { g_id,
						r_id }); // ���Է���state��Ϊ1���໥��ע ����

				rs.close();
				dbConn.closeConn();
				return result > 0 ? true : false;

			}

		}
		return false;
	}

	public boolean delConcer(final int r_id, final int g_id)//ȡ����ע
			throws SQLException {

		String sql_check0 = "select count(*) from relation where r_id=? and g_id=?";

		DBConn dbConn = new DBConn();
		ResultSet rs = dbConn
				.execQuery(sql_check0, new Object[] { r_id, g_id });

		rs.next();
		if (rs.getInt(1) == 0)// �ж��Ƿ��ѹ�ע,���û�й�ע���򷵻ش���
		{
			rs.close();
			dbConn.closeConn();
			return false;

		} else {
			String sql_check1 = "select state from relation where r_id=? and g_id=?";

			rs = dbConn.execQuery(sql_check1, new Object[] { r_id, g_id });// ����state��ֵ
			rs.next();
			int check = rs.getInt(1);
			if (check == 1)// ���˫���Ǻ��ѹ�ϵ�������˺���state��Ϊ0����ִ�н��û��Ĺ�ע��¼ɾȥ
			{
				String remove = "update relation set state=0 where r_id=? and g_id=?";

				dbConn.execOther(remove, new Object[] { g_id, r_id });

			}
			String delConcer = "delete from relation where r_id=? and g_id=?"; // �����ǵ����ϵ����˫���ϵ��ɾ����¼
			int result = dbConn.execOther(delConcer,
					new Object[] { r_id, g_id });
			rs.close();
			dbConn.closeConn();

			return result > 0 ? true : false;
		}

	}

	public int[] showConcersId(int u_id) throws SQLException {// �����û���ע���˵�id����������

		String sql = "select g_id from relation where r_id=?";// r_id��ע�˶���g_id

		DBConn conn = new DBConn();
		ResultSet rs = conn.execQuery(sql, new Object[] { u_id });

		IntegerArray concer = new IntegerArray();
		while (rs.next()) {
			concer.add(rs.getInt(1));
		}
		int[] result = concer.toIntArray();
		rs.close();
		conn.closeConn();
		return result;

	}

	
	public List<User> getLook(final int u_id) {
		// ��㿴��

		List<User> looklist = new ArrayList<User>();

		String sqllook = "select * from user where u_id!=? and u_id not in (select g_id from relation where state=0 and r_id=?)";

		DBConn dbConn = new DBConn();

		ResultSet rs = dbConn.execQuery(sqllook, new Object[] {u_id,u_id});
		try {
			while (rs.next()) {

				User user = new User();// ��װuser����,û�з�װ����

				user.setU_id(rs.getInt(1));
				user.setU_account(rs.getString(2));
				user.setU_pwd(rs.getString(3));
				user.setU_nickname(rs.getString(4));
				user.setU_sex(rs.getString(5));
				user.setU_city(rs.getString(6));
				user.setU_date(rs.getString(7));
				user.setU_qq(rs.getString(8));
				user.setU_msn(rs.getString(9));
				user.setU_motto(rs.getString(10));
				user.setU_edu(rs.getString(11));
				user.setU_pic(rs.getString(12));
				user.setU_ques(rs.getString(13));
				user.setU_label(rs.getString(14));

				looklist.add(user);
			}
			return looklist;// ��װ�õ�user����һ������
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			
		dbConn.closeConn();
	}
	

}
	
	
	public List<User> getConcersbyResult(final int u_id) {
		// ��ȡĳλ�û����еĹ�ע���ѵ���Ϣ

		List<User> fanslist = new ArrayList<User>();

		String sql2 = "select * from user,relation where u_id = g_id and r_id = ?";

		DBConn dbConn = new DBConn();

		ResultSet rs = dbConn.execQuery(sql2, new Object[] { u_id });
		try {
			while (rs.next()) {

				User user = new User();// ��װuser����,û�з�װ����

				user.setU_id(rs.getInt(1));
				user.setU_account(rs.getString(2));
				user.setU_pwd(rs.getString(3));
				user.setU_nickname(rs.getString(4));
				user.setU_sex(rs.getString(5));
				user.setU_city(rs.getString(6));
				user.setU_date(rs.getString(7));
				user.setU_qq(rs.getString(8));
				user.setU_msn(rs.getString(9));
				user.setU_motto(rs.getString(10));
				user.setU_edu(rs.getString(11));
				user.setU_pic(rs.getString(12));
				user.setU_ques(rs.getString(13));
				user.setU_label(rs.getString(14));

				fanslist.add(user);
			}
			return fanslist;// ��װ�õ�user����һ������
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			
		dbConn.closeConn();
	}
	

}
	
	public List<User>getFriend(final int u_id){
		//���ĳ�û��ĺ�����Ϣ
		List<User>friendlist = new ArrayList<User>();
		String sqlf = "select * from user,relation where u_id=g_id and r_id=? and state=1";
		DBConn dbConn = new DBConn();
		ResultSet rs = dbConn.execQuery(sqlf, new Object[] { u_id });
		try {
			while (rs.next()) {

				User user = new User();// ��װuser����,û�з�װ����

				user.setU_id(rs.getInt(1));
				user.setU_account(rs.getString(2));
				user.setU_pwd(rs.getString(3));
				user.setU_nickname(rs.getString(4));
				user.setU_sex(rs.getString(5));
				user.setU_city(rs.getString(6));
				user.setU_date(rs.getString(7));
				user.setU_qq(rs.getString(8));
				user.setU_msn(rs.getString(9));
				user.setU_motto(rs.getString(10));
				user.setU_edu(rs.getString(11));
				user.setU_pic(rs.getString(12));
				user.setU_ques(rs.getString(13));
				user.setU_label(rs.getString(14));
				friendlist.add(user);
			}
			return friendlist;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			
		dbConn.closeConn();
	}
		
		
	}
		
		
	

	public int[] showFans(int u_id) throws SQLException {// �����û���˿��id����������

		String sql = "select r_id from relation where g_id=?";// g_id������r_id��ע

		DBConn conn = new DBConn();
		ResultSet rs = conn.execQuery(sql, new Object[] { u_id });

		IntegerArray concer = new IntegerArray();
		while (rs.next()) {
			concer.add(rs.getInt(1));
		}
		int[] result = concer.toIntArray();
		rs.close();
		conn.closeConn();
		return result;
	}

	public List<User> getFansbyResult(int u_id){// ��ȡĳλ�û����еķ�˿����Ϣ
		List<User>fanlist = new ArrayList<User>();
		
		String sql2 = "select * from user,relation where u_id = r_id and g_id = ?";
		
		DBConn dbConn = new DBConn();
		ResultSet rs = dbConn.execQuery(sql2, new Object[] { u_id });
		try {
			while (rs.next()) {
				User user = new User();// ��װuser����,��Щ���Բ���װ

				user.setU_id(rs.getInt(1));
				user.setU_account(rs.getString(2));
				user.setU_pwd(rs.getString(3));
				user.setU_nickname(rs.getString(4));
				user.setU_sex(rs.getString(5));
				user.setU_city(rs.getString(6));
				user.setU_date(rs.getString(7));
				user.setU_qq(rs.getString(8));
				user.setU_msn(rs.getString(9));
				user.setU_motto(rs.getString(10));
				user.setU_edu(rs.getString(11));
				user.setU_pic(rs.getString(12));
				user.setU_ques(rs.getString(13));
				user.setU_label(rs.getString(14));
				
				fanlist.add(user);
		}
			return fanlist;//��װ�õ�user����һ������
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			dbConn.closeConn();
	}
}

	public int showConcersNum(int u_id) throws SQLException {// ��ʾ��ע����

		String sql = "select g_id from relation where r_id=?";// r_id��ע�˶���g_id

		DBConn conn = new DBConn();
		ResultSet rs = conn.execQuery(sql, new Object[] { u_id });

		int concerNum = 0;
		while (rs.next()) {
			concerNum++;
		}
		rs.close();
		conn.closeConn();
		return concerNum;

	}

	public int showFansNum(int u_id) throws SQLException {// ��ʾ��˿����

		String sql = "select r_id from relation where g_id=?";// g_id��ע�˶���r_id

		DBConn conn = new DBConn();
		ResultSet rs = conn.execQuery(sql, new Object[] { u_id });

		int fansNum = 0;
		while (rs.next()) {
			fansNum++;
		}
		rs.close();
		conn.closeConn();
		return fansNum;

	}
	
	//ͳ�Ƶ�ǰ�û���������ע�˵�΢������
	public int allBlogs(final int u_id){
		DBConn dbConn=new DBConn();
		String sql="SELECT count(*) from weibo where w_id= any (select g_id from relation where (r_id=? and state=0) or (r_id=? and state=1)) or w_id=1 order by w_date desc";
		ResultSet rs=dbConn.execQuery(sql, new Object[]{u_id, u_id});
		try {
			int num=0;
			while (rs.next()) {
				num=rs.getInt("count(*)");
			}
			return num;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		}finally{
			dbConn.closeConn();
		}

	}}
