package com.web.dao;

import com.web.common.BaseDao;
import com.web.po.User;

public interface IUserDao extends BaseDao<User, Integer> {
	
	public User findByUserMCAndPassword(String userName, String userPassword);
	
	public User findByUserNameAndPassword(String userName, String userPassword);
	
}
