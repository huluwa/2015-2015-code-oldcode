package com.zjzs.struts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.zjzs.db.SqlUtil;
import com.zjzs.pojo.User;

public class UserAction extends ActionSupport {

	private User user;
	private List<Object> param=new ArrayList<Object>();
	private Map<String, Object> map=new HashMap<String, Object>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String login(){
		String sql="select * from user where name=? and pass=?";
		param.add(user.getName());
		param.add(user.getPass());
		int i=SqlUtil.searchCount(sql, param.toArray());
		if(i>0){
			return "main";
		}
		map.put("msg", "用户名或密码错误");
		return "login";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

}
