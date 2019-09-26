package com.web.dao;

import com.web.common.BaseDao;
import com.web.po.User;

public interface IUserDao extends BaseDao<User, Integer> {
	
	public User findByUserMCAndPassword(String userName, String userPassword);//根据用户名和密码查询用户信息
	
	public User findByUserNameAndPassword(String userName, String userPassword);//根据用户名和密码查询用户信息
	
}
