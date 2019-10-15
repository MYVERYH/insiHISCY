package com.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.IMemberDao;
import com.web.util.DBUtil;
import com.web.util.DaoHelper;
import com.web.util.JdbcHelper;
import com.web.vo.MemberInfo;
import com.web.vo.Page;

public class MemberDaoImpl implements IMemberDao {

	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private String selectAll = "SELECT M.`ClubCardID`,t.`MemberTypeMC`,c.`MarkClub`,m.`MemberMC`"
			+ ",p.`PapersTypeMC`,m.`PapersMarkMemb`,m.`BirthdayMember`,c.`BalanceClub`,c.`IntegralClub`,"
			+ "m.`phoneMember`,m.`UnitsMember`,m.`SiteMember`,c.`DelivertimeClub` FROM `pw_member` m "
			+ "JOIN `pw_clubcard` c ON m.`ClubCardID` = c.`ClubCardID` JOIN `sys_paperstype` p "
			+ "ON m.`PapersTypeID` = p.`PapersTypeID` JOIN `pw_membertype` t "
			+ "ON c.`MemberTypeID` = t.`MemberTypeID` WHERE c.`CardClub` = 1";
	private String getTotalRow = "SELECT COUNT(*) FROM `pw_member` m "
			+ "JOIN `pw_clubcard` c ON m.`ClubCardID` = c.`ClubCardID` JOIN `sys_paperstype` p "
			+ "ON m.`PapersTypeID` = p.`PapersTypeID` JOIN `pw_membertype` t "
			+ "ON c.`MemberTypeID` = t.`MemberTypeID` WHERE c.`CardClub` = 1";
	private String update = "UPDATE `pw_clubcard` SET CardClub=? WHERE ClubCardID=?";

	@Override
	public List<MemberInfo> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberInfo findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(MemberInfo t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(MemberInfo member) {
		int flag = DaoHelper.insertUpdate(update, member);
		return flag;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberInfo> findMember(String num, String name, Page page) {
		List<MemberInfo> members = new ArrayList<MemberInfo>();
		try {
			StringBuilder builder = new StringBuilder(selectAll);
			//查询条件拼接
			if (num != "" && !num.isEmpty()) {
				builder.append(" AND c.`MarkClub` = '" + num + "'");
			}
			if (name != "" && !name.isEmpty()) {
				builder.append(" AND m.`MemberMC` like '%" + name + "%'");
			}
			builder.append(" ORDER BY m.`ClubCardID` LIMIT ?,?");
			con = DBUtil.getConnection();//获取连接
			ps = con.prepareStatement(builder.toString());
			ps.setInt(1, page.getStartIndex());
			ps.setInt(2, page.getLimit());
			rs = ps.executeQuery();
			//调用JdbcHelper反射类的getResult方法获取list集合数据
			members = JdbcHelper.getResult(rs, MemberInfo.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return members;
	}

	@Override
	public long getTotalRows(String num, String name) {
		StringBuilder builder = new StringBuilder(getTotalRow);
		//查询条件拼接
		if (num != "" && !num.isEmpty()) {
			builder.append(" AND c.`MarkClub` = '" + num + "'");
		}
		if (name != "" && !name.isEmpty()) {
			builder.append(" AND m.`MemberMC` like '%" + name + "%'");
		}
		//调用DaoHelper反射类的getTotalRow方法获取数据总条数
		long intTotalRow = DaoHelper.getTotalRow(builder.toString());
		return intTotalRow;
	}

}
