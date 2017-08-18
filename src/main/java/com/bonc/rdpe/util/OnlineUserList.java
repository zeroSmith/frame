package com.bonc.rdpe.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.bonc.rdpe.entity.user.User;

/**
 * @author 作者: 吕一凡 
 * @date 2017年2月7日 上午11:35:40 
 * @version 版本: 1.0
 * 统计系统在线人数类
 */
public class OnlineUserList implements HttpSessionBindingListener{
	
	private static OnlineUserList userList;
	
	private Vector<User> v;
	
	private Map<String,User> onlineUserMap;
	
	private User user;
	
	private OnlineUserList(){
		v=new Vector<User>();
		onlineUserMap = new HashMap<String,User>();
	}
	
	public static OnlineUserList getInstance(){
		if(userList == null){
			userList = new OnlineUserList();
		}
		return userList;
	}
	
	public void initMap(){
		onlineUserMap.clear();
		Enumeration<User> enums=v.elements();
		while(enums.hasMoreElements())
		{
			User user = enums.nextElement();
			onlineUserMap.put(user.getUserId(), user);
		}
	}
	
	public Map<String,User> getUserMap(){
		initMap();
		return onlineUserMap;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		 addUser(user);
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		removeUser(user);
	}
	
	public void addUser(User user){
		if(user!=null)v.addElement(user);
	}
	
	public void removeUser(User user){
		if(user!=null)v.remove(user);
	}
}
