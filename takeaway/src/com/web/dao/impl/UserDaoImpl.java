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

	private String findByUserMCAndPassword = "select u.*,s.staffName from pw_user u join pw_user_group g on u.userId=g.userId join pw_staff s "
			+ "on g.staffId = s.staffId where u.userName=? and u.userPassword=?";
	private String updata = "UPDATE pw_user SET userPassword=? WHERE userId=?";

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
	public int insert(User t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(User user) {
		int flag = 0;
		flag = DaoHelper.insertUpdate(updata, user);
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
			con = DBUtil.getConnection();
			ps = con.prepareStatement(findByUserMCAndPassword);
			ps.setString(1, userMC);
			ps.setString(2, password);
			rs = ps.executeQuery();
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
