package com.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.web.dao.IUserDao;
import com.web.po.User;
import com.web.util.DBUtil;
import com.web.util.DaoHelper;
import com.web.util.JdbcHelper;

public class UserDaoImpl implements IUserDao {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private String findByUserMCAndPassword = "select u.*,s.staffName from pw_user u join pw_user_group g "
			+ "on u.userId=g.userId join pw_staff s "
			+ "on g.staffId = s.staffId where u.userName=? and u.userPassword=?";
	private String updata = "UPDATE pw_user SET userPassword=? WHERE userId=?";
	private String insert = "INSERT INTO pw_user(userName,userPassword) VALUES(?,?)";
	private String findByUserNameAndPassword = "SELECT u.*,d.`name` FROM `pw_user` u JOIN `pw_user_detail` d "
			+ "ON u.`userId` = d.`userId` WHERE u.`userName`=? AND u.`userPassword`=?";

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
	public int insert(User user) {
		//调用DaoHelper反射类的insertUpdate方法新增用户信息
		int flag = DaoHelper.insertUpdate(insert, user);
		return flag;
	}

	@Override
	public int update(User user) {
		//调用DaoHelper反射类的insertUpdate方法修改用户信息
		int flag =  DaoHelper.insertUpdate(updata, user);
		return flag;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User findByUserMCAndPassword(String userMC, String password) {
		User user = new User();
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement(findByUserMCAndPassword);
			ps.setString(1, userMC);
			ps.setString(2, password);
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取单条数据
			user = JdbcHelper.getSingleResult(rs, User.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return user;
	}

	@Override
	public User findByUserNameAndPassword(String userName, String userPassword) {
		User user = new User();
		try {
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement(findByUserNameAndPassword);
			ps.setString(1, userName);
			ps.setString(2, userPassword);
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			user = JdbcHelper.getSingleResult(rs, User.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(con, ps, rs);
		}
		return user;
	}

}
