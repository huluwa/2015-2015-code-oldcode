package com.microblog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.microblog.dbutil.DBConn;
import com.microblog.po.Comment;

public class CommentDao {

	public boolean sendComment(Comment comment) { // ������

		int c_id = comment.getC_id();// ΢����
		int c_uid = comment.getC_uid();//  �����۵��û���
		String c_content = comment.getC_content();
		String c_unick = comment.getC_unick();//�����ߵ��ǳ�
		String c_upic = comment.getC_upic();//�����ߵ�ͷ��
		

		String sql = "insert into comment (c_id,c_content,c_uid,c_date,c_unick,c_upic) values(?,?,?,now(),?,?)";

		DBConn dbConn = new DBConn();

		int rs = dbConn.execOther(sql, new Object[] { c_id, c_content,c_uid, c_unick, c_upic  });

		dbConn.closeConn();
		return rs > 0 ? true : false;

	}
	
	public boolean deleteComment(Comment comment) { // ɾ������
		
		int id = comment.getId();
		
		String sql = "delete from comment where id=?";

		DBConn dbConn = new DBConn();

		int rs = dbConn.execOther(sql, new Object[] {id});

		dbConn.closeConn();
		return rs > 0 ? true : false;

	}

	public ArrayList<Comment> getCommentbyId(int id) throws SQLException { // ����΢������ʾ���� �����id��΢�����е�id����ʾ΢�����

		String sql = "select * from comment where c_id= ? order by c_date desc ";

		DBConn dbConn = new DBConn();

		ResultSet rs = dbConn.execQuery(sql, new Object[] {id});

		ArrayList<Comment> result = new ArrayList<Comment>();

		if (!rs.next()) // ������
			return null;
		else{
		do {
			Comment comment = new Comment();
			
			comment.setId(rs.getInt(1)); // ���۵�id
			comment.setC_id(rs.getInt(2)); // �����ڵ�΢����id
			comment.setC_content(rs.getString(3)); // ��������
			comment.setC_uid(rs.getInt(4)); // �û���id
			comment.setC_date(rs.getString(5)); // ��������
			comment.setC_unick(rs.getString(6));
			comment.setC_upic(rs.getString(7));
			
			result.add(comment);
		} while (rs.next());
		rs.close();
		dbConn.closeConn();
		return result;

	}
	}
	
	public int showCommentNum (int c_id){
		
		
		DBConn dbConn = new DBConn();
		String sql = "select count(*) from comment where c_id = ?";
		
		ResultSet rs = dbConn.execQuery(sql, new Object[] { c_id });
		
		try {
			if (rs.next()) {

				int rs1 = rs.getInt(1);
				dbConn.closeConn();
				return rs1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
		
		
		
	}
}
