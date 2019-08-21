package com.web.service.impl;

import java.util.List;

import com.web.dao.IUserDao;
import com.web.dao.impl.UserDaoImpl;
import com.web.po.User;
import com.web.service.IUserService;

public class UserServiceImpl implements IUserService {

	private IUserDao userDao = new UserDaoImpl();

	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insert(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(User user) {
		String str = "";
		if (userDao.update(user) > 0) {
			str = "修改成功";
		} else {
			str = "修改失败";
		}
		// TODO Auto-generated method stub
		return str;
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUserMCAndPassword(String userMC, String password) {
		// TODO Auto-generated method stub
		return userDao.findByUserMCAndPassword(userMC, password);
	}

}
