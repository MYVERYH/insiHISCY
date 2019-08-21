package com.web.service;

import com.web.common.BaseService;
import com.web.po.User;

public interface IUserService extends BaseService<User, Integer> {

	public User findByUserMCAndPassword(String userName, String userPassword);
	
}
